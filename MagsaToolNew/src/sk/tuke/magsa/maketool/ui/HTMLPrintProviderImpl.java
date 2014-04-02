package sk.tuke.magsa.maketool.ui;

import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import sk.tuke.magsa.maketool.PrintProvider;
import sk.tuke.magsa.maketool.core.MagsaConfig;

public class HTMLPrintProviderImpl implements PrintProvider {

    private enum OutputStyle {

        TEXT("<span style='color:rgb(0,0,0);'>?</span>"),
        INFO("<span style='color:rgb(0,0,200);'>?</span>"),
        CODE("<span style='color:rgb(40,150,40);'>?</span>"),
        ERROR("<span style='color:rgb(255,0,0);'>?</span>"),
        KEYWORD("<span style='color:rgb(0,0,255);font-weight:bold;'>?</span>"),
        COMMENT("<span style='color:rgb(255,0,0);'>?</span>"),
        FORM("<span style='color:rgb(0,0,255);font-weight:bold;'>?</span>"),
        TABLE("<span style='color:rgb(40,150,40);font-weight:bold;'>?</span>");
        private final String styleTemplate;

        private OutputStyle(String styleTemplate) {
            this.styleTemplate = styleTemplate;
        }

        public String getStyleTemplate() {
            return styleTemplate;
        }
    }
    private final String LINEBREAK = "<br/>";
    private final String TAB = "&nbsp;&nbsp;&nbsp;&nbsp;";
    private final String SPACE = "&nbsp;";
    private final String DOCUMENT = "<html><body style='font-family:" + Font.DIALOG_INPUT + ";font-size:12pt;'>?</body></html>";
    //private final Pattern url = Pattern.compile("^http\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(/\\S*)?$");
    private final JEditorPane modelPane;
    private final JEditorPane consolePane;
    private StringBuilder model = new StringBuilder("");
    private StringBuilder console = new StringBuilder("");

    public HTMLPrintProviderImpl(JEditorPane modelPane, JEditorPane consolePane) {
        this.modelPane = modelPane;
        this.modelPane.setEditable(false);
        this.modelPane.setContentType("text/html");

        this.consolePane = consolePane;
        this.consolePane.setEditable(false);
        this.consolePane.setContentType("text/html");

        this.consolePane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else {
                        System.out.println("Otváranie prehliadača nie je na tejto platforme podporované.");
                    }
                }
            }
        });

        redirect();
    }

    @Override
    public synchronized void reset() {
        modelPane.setText("");
        model = new StringBuilder("");
        consolePane.setText("");
        console = new StringBuilder("");
    }

    @Override
    public void printModel(Object model) {
        if (model != null) {
            try {
                //Vytlaci zoznam entit
                printTextToModel(ResourceBundle.getBundle("sk/tuke/magsa/maketool/Bundle").getString("ENTITY_LIST"), OutputStyle.COMMENT);
                Class modelClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
                Class entityClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Entity");
                Class propertyClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Property");

                Object entities[] = (Object[]) modelClass.getMethod("getEntities").invoke(model);
                for (Object entity : entities) {
                    printTextToModel("\nentity ", OutputStyle.KEYWORD);
                    printTextToModel(entityClass.getMethod("getName").invoke(entity) + " {", OutputStyle.TEXT);

                    Object properties[] = (Object[]) entityClass.getMethod("getProperties").invoke(entity);
                    for (Object property : properties) {
                        printTextToModel("\n    " + propertyClass.getMethod("getName").invoke(property) + " : ", OutputStyle.TEXT);
                        printTextToModel(propertyClass.getMethod("getType").invoke(property).toString().toLowerCase(), OutputStyle.KEYWORD);

                        try {
                            Method method = propertyClass.getMethod("getConstraints");
                            Object constraints[] = (Object[]) method.invoke(property);
                            if (constraints != null) {
                                printTextToModel(" ", OutputStyle.TEXT);
                                boolean first = true;
                                for (Object constraint : constraints) {
                                    if (first != true) {
                                        printTextToModel(", ", OutputStyle.TEXT);
                                    } else {
                                        first = false;
                                    }
                                    printTextToModel(constraint.toString(), OutputStyle.KEYWORD);
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
                        try {
                            Object references[] = (Object[]) method.invoke(entity);
                            if (references != null) {
                                if (references.length > 0) {
                                    printTextToModel("\n", OutputStyle.TEXT);
                                }
                                for (Object reference : references) {
                                    Object from = entityClass.getMethod("getName").invoke(methodFrom.invoke(reference));
                                    Object to = entityClass.getMethod("getName").invoke(methodTo.invoke(reference));
                                    printTextToModel("\n    reference from ", OutputStyle.KEYWORD);
                                    printTextToModel(from.toString(), OutputStyle.TEXT);
                                    printTextToModel(" to ", OutputStyle.KEYWORD);
                                    printTextToModel(to.toString(), OutputStyle.TEXT);
                                }
                            }
                        } catch (ClassCastException casting) { // In case that outgoing referencesare implented as 
                            List references = (List) method.invoke(entity);
                            if (references != null) {
                                if (references.size() > 0) {
                                    printTextToModel("\n", OutputStyle.TEXT);
                                }
                                for (Object reference : references) {
                                    Object from = entityClass.getMethod("getName").invoke(methodFrom.invoke(reference));
                                    Object to = entityClass.getMethod("getName").invoke(methodTo.invoke(reference));
                                    printTextToModel("\n    reference from ", OutputStyle.KEYWORD);
                                    printTextToModel(from.toString(), OutputStyle.TEXT);
                                    printTextToModel(" to ", OutputStyle.KEYWORD);
                                    printTextToModel(to.toString(), OutputStyle.TEXT);
                                }
                            }
                        }
                    } catch (NoSuchMethodException ex) {
                        //Ked nie je metoda nic sa nevypise
                    } catch (ClassNotFoundException ex) {
                        //Ked nie je metoda nic sa nevypise
                    }
                    printTextToModel("\n}\n", OutputStyle.TEXT);
                }

                //Vytlaci pouzivatelske rozhranie
                //TODO : dorobit lepsie prejdenie tabuliek a formularov
                try {
                    Class uiClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.ui.UI");
                    Object ui = modelClass.getMethod("getUi").invoke(model);
                    if (ui != null) {
                        printTextToModel("\n" + ResourceBundle.getBundle("sk/tuke/magsa/maketool/Bundle").getString("USER_INTERFACE"), OutputStyle.COMMENT);
                        Object tables[] = (Object[]) uiClass.getMethod("getTables").invoke(ui);
                        if (tables != null) {
                            for (Object table : tables) {
                                printTextToModel("\n" + table.toString(), OutputStyle.TABLE);
                            }
                        }
                        Object forms[] = (Object[]) uiClass.getMethod("getForms").invoke(ui);
                        if (tables != null) {
                            for (Object form : forms) {
                                printTextToModel("\n" + form.toString(), OutputStyle.FORM);
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
        printTextToConsole(code, OutputStyle.CODE);
    }

    @Override
    public void printInfo(String text) {
        printTextToConsole(text, OutputStyle.INFO);
    }

    @Override
    public void printError(String error) {
        printTextToConsole(error, OutputStyle.ERROR);
    }

    private synchronized void printTextToConsole(String text, OutputStyle style) {
        String parsedText = style.styleTemplate
                .replace("?", text)
                .replace("\t", TAB)
                .replace(" ", SPACE)
                .replace("\n", LINEBREAK);
        this.console.append(parsedText);
        final String output = DOCUMENT.replace("?", this.console.toString());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                consolePane.setText(output);
            }
        });
    }

    private synchronized void printTextToModel(String text, OutputStyle style) {
        String parsedText = style.styleTemplate
                .replace("?", text)
                .replace("\t", TAB)
                .replace(" ", SPACE)
                .replace("\n", LINEBREAK);
        this.model.append(parsedText);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                modelPane.setText(DOCUMENT.replace("?", model.toString()));
            }
        });
    }

    private void redirect() {
        PipedOutputStream output;
        //output stream
        output = new PipedOutputStream();
        new StreamRedirector(output, OutputStyle.TEXT).start();
        System.setOut(new PrintStream(output));

        //error stream
        output = new PipedOutputStream();
        new StreamRedirector(output, OutputStyle.ERROR).start();
        System.setErr(new PrintStream(output));
    }

    /**
     * ************************************************************************
     */
    /**
     * ************************************************************************
     */
    private class StreamRedirector extends Thread {

        private BufferedReader reader;
        private OutputStyle style;

        public StreamRedirector(PipedOutputStream output, OutputStyle style) {
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
                    String line = reader.readLine();
                    printTextToConsole(line + "\n", style);
                } catch (Exception e) {
                    printError("Console reports an internal error.\nThe error is: " + e);
                }
            }
        }
    }
}
