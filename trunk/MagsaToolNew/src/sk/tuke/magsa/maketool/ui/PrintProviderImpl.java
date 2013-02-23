package sk.tuke.magsa.maketool.ui;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import sk.tuke.magsa.maketool.core.MagsaConfig;
import sk.tuke.magsa.maketool.PrintProvider;

public class PrintProviderImpl implements PrintProvider {
    private final JTextPane modelPane;

    private final JTextPane consolePane;

    private Style textStyle;

    private Style infoStyle;

    private Style codeStyle;

    private Style errorStyle;

    private Style propertyStyle;

    private Style entityStyle;

    private Style typeStyle;

    private Style constraintStyle;

    private Style tableStyle;

    private Style formStyle;

    private Style keywordStyle;

    private Style commentStyle;

    public PrintProviderImpl(JTextPane modelPane, JTextPane consolePane) {
        this.modelPane = modelPane;
        this.consolePane = consolePane;

        //Register styles - console pane
        textStyle = registerStyle(consolePane, "text", Color.BLACK, false);
        infoStyle = registerStyle(consolePane, "info", new Color(0, 0, 200), false);
        codeStyle = registerStyle(consolePane, "code", new Color(40, 150, 40), false);
        errorStyle = registerStyle(consolePane, "error", Color.RED, false);

        //Register styles - model pane
        keywordStyle = registerStyle(modelPane, "entity", Color.BLUE, true);
        commentStyle = registerStyle(modelPane, "constraint", Color.RED, false);

        entityStyle = registerStyle(modelPane, "entity", Color.BLUE, true);
        propertyStyle = registerStyle(modelPane, "property", new Color(40, 150, 40), true);
        typeStyle = registerStyle(modelPane, "type", new Color(40, 150, 40), false);
        constraintStyle = registerStyle(modelPane, "constraint", Color.RED, false);
        formStyle = registerStyle(modelPane, "form", Color.BLUE, true);
        tableStyle = registerStyle(modelPane, "table", new Color(40, 150, 40), true);

        redirect();
    }

    private Style registerStyle(JTextPane pane, String name, Color color, boolean bold) {
        Style style = pane.getStyledDocument().addStyle(name, null);
        StyleConstants.setFontFamily(style, "DialogInput");
        StyleConstants.setBold(style, bold);
        StyleConstants.setForeground(style, color);
        return style;
    }

    @Override
    public void reset() {
        modelPane.setText("");
        consolePane.setText("");
    }

    @Override
    public void printModel(Object model) {
        if (model != null) {
            try {
                //Vytlaci zoznam entit
                printTextToModel("# ---------- Zoznam entít ----------", commentStyle);
                Class modelClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
                Class entityClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Entity");
                Class propertyClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Property");

                Object entities[] = (Object[]) modelClass.getMethod("getEntities").invoke(model);
                for (Object entity : entities) {
                    printTextToModel("\nentity ", keywordStyle);
                    printTextToModel(entityClass.getMethod("getName").invoke(entity) + " {", textStyle);

                    Object properties[] = (Object[]) entityClass.getMethod("getProperties").invoke(entity);
                    for (Object property : properties) {
                        printTextToModel("\n    " + propertyClass.getMethod("getName").invoke(property) + " : ", textStyle);
                        printTextToModel(propertyClass.getMethod("getType").invoke(property).toString().toLowerCase(), keywordStyle);

                        try {
                            Method method = propertyClass.getMethod("getConstraints");
                            Object constraints[] = (Object[]) method.invoke(property);
                            if (constraints != null) {
                                printTextToModel(" ", textStyle);
                                boolean first = true;
                                for (Object constraint : constraints) {
                                    if (first != true) {
                                        printTextToModel(", ", textStyle);
                                    } else {
                                        first = false;
                                    }
                                    printTextToModel(constraint.toString(), keywordStyle);
                                }
                            }
                        } catch (NoSuchMethodException ex) {
                            //Ked nie je metoda nic sa nevypise
                        }
                    }

                    try {
                        Class referenceClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Reference");
                        Method methodFrom = referenceClass.getMethod("getFrom");
                        Method methodTo = referenceClass.getMethod("getTo");
                        Method method = entityClass.getMethod("getOutgoingReferences");
                        Object references[] = (Object[]) method.invoke(entity);
                        if (references != null) {
                            if (references.length > 0) {
                                printTextToModel("\n", textStyle);
                            }
                            for (Object reference : references) {
                                Object from = entityClass.getMethod("getName").invoke(methodFrom.invoke(reference));
                                Object to = entityClass.getMethod("getName").invoke(methodTo.invoke(reference));
                                printTextToModel("\n    reference from ", textStyle);
                                printTextToModel(from.toString(), entityStyle);
                                printTextToModel(" to ", textStyle);
                                printTextToModel(to.toString(), entityStyle);
                            }
                        }
                    } catch (NoSuchMethodException ex) {
                        //Ked nie je metoda nic sa nevypise
                    } catch (ClassNotFoundException ex) {
                        //Ked nie je metoda nic sa nevypise
                    }
                    printTextToModel("\n}\n", textStyle);
                }

                //Vytlaci pouzivatelske rozhranie
                //TODO : dorobit lepsie prejdenie tabuliek a formularov
                try {
                    Class uiClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.ui.UI");
                    Object ui = modelClass.getMethod("getUi").invoke(model);
                    if (ui != null) {
                        printTextToModel("\n# ---------- Používateľské rozhranie ----------", commentStyle);
                        Object tables[] = (Object[]) uiClass.getMethod("getTables").invoke(ui);
                        if (tables != null) {
                            for (Object table : tables) {
                                printTextToModel("\n" + table.toString(), tableStyle);
                            }
                        }
                        Object forms[] = (Object[]) uiClass.getMethod("getForms").invoke(ui);
                        if (tables != null) {
                            for (Object form : forms) {
                                printTextToModel("\n" + form.toString(), formStyle);
                            }
                        }
                    }
                } catch (ClassNotFoundException e) {
                    //ok
                }
            } catch (Exception ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void printCode(String code) {
        printTextToConsole(code, codeStyle);
    }

    @Override
    public void printInfo(String text) {
        printTextToConsole(text, infoStyle);
    }

    @Override
    public void printError(String error) {
        printTextToConsole(error, errorStyle);
    }

    private synchronized void printTextToConsole(String text, Style style) {
        printText(consolePane, text, style);
    }

    private synchronized void printTextToModel(String text, Style style) {
        printText(modelPane, text, style);
    }

    private void printText(JTextPane pane, String text, Style style) {
        try {
            pane.getStyledDocument().insertString(pane.getStyledDocument().getLength(), text, style);
        } catch (BadLocationException ex) {
            Logger.getLogger(PrintProviderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void redirect() {
        PipedOutputStream output;
        //output stream
        output = new PipedOutputStream();
        new StreamRedirector(output, textStyle).start();
        System.setOut(new PrintStream(output));

        //error stream
        output = new PipedOutputStream();
        new StreamRedirector(output, errorStyle).start();
        System.setErr(new PrintStream(output));
    }

    /***************************************************************************/
    /***************************************************************************/
    public class StreamRedirector extends Thread {
        private BufferedReader reader;

        private Style style;

        public StreamRedirector(PipedOutputStream output, Style style) {
            this.style = style;
            try {
                reader = new BufferedReader(new InputStreamReader(new PipedInputStream(output)));
            } catch (java.io.IOException ex) {
                printError("Couldn't redirect stream to the console\n" + ex.getMessage());
            }
        }

        @Override
        public synchronized void run() {
            while (true) {
                try {
                    printTextToConsole(reader.readLine() + "\n", style);
                } catch (Exception e) {
                    printError("Console reports an internal error.\nThe error is: " + e);
                }
            }
        }
    }
}
