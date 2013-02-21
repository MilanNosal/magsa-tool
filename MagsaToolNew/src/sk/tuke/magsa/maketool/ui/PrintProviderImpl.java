package sk.tuke.magsa.maketool.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

    private Style infoStyle;

    private Style stdStyle;

    private Style codeStyle;

    private Style errorStyle;

    private Style propertyStyle;

    private Style entityStyle;

    private Style typeStyle;

    private Style constraintStyle;

    private Style tableStyle;

    private Style formStyle;

    private Style textStyle;

    private Console redirectedConsole;

    public PrintProviderImpl(JTextPane modelPane, JTextPane consolePane) {
        this.modelPane = modelPane;
        this.consolePane = consolePane;

        //Register styles - console pane
        infoStyle = registerStyle(consolePane, "info", new Color(0, 0, 200), false);
        stdStyle = registerStyle(consolePane, "std", new Color(60, 60, 60), false);
        codeStyle = registerStyle(consolePane, "code", new Color(40, 150, 40), false);
        errorStyle = registerStyle(consolePane, "error", Color.RED, false);

        //Register styles - model pane
        entityStyle = registerStyle(modelPane, "entity", Color.BLUE, true);
        propertyStyle = registerStyle(modelPane, "property", new Color(40, 150, 40), true);
        typeStyle = registerStyle(modelPane, "type", new Color(40, 150, 40), false);
        constraintStyle = registerStyle(modelPane, "constraint", Color.RED, false);
        formStyle = registerStyle(modelPane, "form", Color.BLUE, true);
        tableStyle = registerStyle(modelPane, "table", new Color(40, 150, 40), true);
        textStyle = registerStyle(modelPane, "text", Color.BLACK, true);

        redirectedConsole = new Console();
        redirectedConsole.redirect();
    }

    private Style registerStyle(JTextPane pane, String name, Color color, boolean bold) {
        Style style = pane.getStyledDocument().addStyle(name, null);
        StyleConstants.setFontFamily(style, "mono");
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
                printTextToModel("------------------- Zoznam entít -------------------", textStyle);
                Class modelClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
                Class entityClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Entity");
                Class propertyClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Property");


                Object entities[] = (Object[]) modelClass.getMethod("getEntities").invoke(model);
                for (Object entity : entities) {
                    printTextToModel("\n" + entityClass.getMethod("getName").invoke(entity), entityStyle);
                    printTextToModel(" {", textStyle);
                    Object properties[] = (Object[]) entityClass.getMethod("getProperties").invoke(entity);
                    for (Object property : properties) {
                        printTextToModel("\n   " + propertyClass.getMethod("getName").invoke(property), propertyStyle);
                        printTextToModel(" : ", textStyle);
                        printTextToModel(propertyClass.getMethod("getType").invoke(property).toString(), typeStyle);

                        try {
                            Method method = propertyClass.getMethod("getConstraints");
                            Object constraints[] = (Object[]) method.invoke(property);
                            if (constraints != null) {
                                printTextToModel(" [", constraintStyle);
                                boolean first = true;
                                for (Object constraint : constraints) {
                                    if (first != true) {
                                        printTextToModel(", ", constraintStyle);
                                    } else {
                                        first = false;
                                    }
                                    printTextToModel(constraint.toString(), constraintStyle);
                                }
                                printTextToModel("]", constraintStyle);
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
                                printTextToModel("\n   referencia z ", textStyle);
                                printTextToModel(from.toString(), entityStyle);
                                printTextToModel(" na ", textStyle);
                                printTextToModel(to.toString(), entityStyle);
                            }
                        }
                    } catch (NoSuchMethodException ex) {
                        //Ked nie je metoda nic sa nevypise
                    } catch (ClassNotFoundException ex) {
                        //Ked nie je metoda nic sa nevypise
                    }
                    printTextToModel("\n   }", textStyle);
                }

                //Vytlacit pouzivatelske rozhranie
                printTextToModel("\n--------------------------------------------------------------\n", textStyle);
                try {
                    Class uiClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.ui.UI");
                    Object ui = modelClass.getMethod("getUi").invoke(model);
                    if (ui != null) {
                        printTextToModel("\n\n------------------ Používateľské rozhranie ------------------", textStyle);
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
                        printTextToModel("\n---------------------------------------------------------------------\n", textStyle);
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
        printText(code, this.codeStyle);
    }

    @Override
    public void printInfo(String text) {
        printText(text, infoStyle);
    }

    @Override
    public void printError(String error) {
        printText(error, this.errorStyle);
    }

    protected void printText(String text, Style style) {
        try {
            consolePane.getStyledDocument().insertString(consolePane.getStyledDocument().getLength(), text + "\n", style);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void printTextToModel(String text, Style style) {
        try {
            modelPane.getStyledDocument().insertString(modelPane.getStyledDocument().getLength(), text, style);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class Console implements ActionListener, Runnable {
        private Thread readerStdout;

        private Thread readerStderr;

        private boolean quit;

        private final PipedInputStream pinStdout = new PipedInputStream();

        private final PipedInputStream pinStderr = new PipedInputStream();

        public void redirect() {
            try {
                PipedOutputStream pout = new PipedOutputStream(this.pinStdout);
                System.setOut(new PrintStream(pout, true));
            } catch (java.io.IOException ex) {
                printText("Couldn't redirect STDOUT to this console\n" + ex.getMessage(), consolePane.getStyle("Error"));
            }

            try {
                PipedOutputStream pout2 = new PipedOutputStream(this.pinStderr);
                System.setErr(new PrintStream(pout2, true));
            } catch (java.io.IOException ex) {
                printText("Couldn't redirect STDERR to this console\n" + ex.getMessage(), consolePane.getStyle("Error"));
            }

            quit = false; // signals the Threads that they should exit

            // Starting two seperate threads to read from the PipedInputStreams				
            //
            readerStdout = new Thread(this);
            readerStdout.setDaemon(true);
            readerStdout.start();
            //
            readerStderr = new Thread(this);
            readerStderr.setDaemon(true);
            readerStderr.start();

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    quit = true;
                }
            });
        }

        @Override
        public synchronized void actionPerformed(ActionEvent evt) {
            consolePane.setText("");
        }

        @Override
        public synchronized void run() {
            try {
                while (Thread.currentThread() == readerStdout && !quit) {
                    try {
                        this.wait(100);
                    } catch (InterruptedException ie) {
                    }
                    if (pinStdout.available() != 0) {
                        printText(this.readLine(pinStdout) + "\n", consolePane.getStyle("Std"));
                    }
                }

                while (Thread.currentThread() == readerStderr && !quit) {
                    try {
                        this.wait(100);
                    } catch (InterruptedException ie) {
                    }
                    if (pinStderr.available() != 0) {
                        printText(this.readLine(pinStderr) + "\n", consolePane.getStyle("Error"));
                    }
                }
            } catch (Exception e) {
                printText("Console reports an Internal error.\nThe error is: " + e, consolePane.getStyle("Error"));
            }
        }

        private void printText(String text, Style style) {
            try {
                consolePane.getStyledDocument().insertString(consolePane.getStyledDocument().getLength(), text, style);
            } catch (BadLocationException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private synchronized String readLine(PipedInputStream in) throws IOException {
            String input = "";
            do {
                int available = in.available();
                if (available == 0) {
                    break;
                }
                byte b[] = new byte[available];
                in.read(b);
                input = input + new String(b, 0, b.length);
            } while (!input.endsWith("\n") && !input.endsWith("\r\n") && !quit);
            return input;
        }
    }
}
