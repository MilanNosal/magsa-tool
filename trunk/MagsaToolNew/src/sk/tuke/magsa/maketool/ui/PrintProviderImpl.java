package sk.tuke.magsa.maketool.ui;

import java.awt.Color;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import sk.tuke.magsa.maketool.MagsaConfig;
import sk.tuke.magsa.maketool.PrintProvider;

public class PrintProviderImpl implements PrintProvider {
    private final JTextPane modelPane;

    private final JTextPane consolePane;

    private Style info;

    private Style std;

    private Style code;

    private Style error;

    public PrintProviderImpl(JTextPane modelPane, JTextPane consolePane) {
        this.modelPane = modelPane;
        this.consolePane = consolePane;

        info = consolePane.getStyledDocument().addStyle("Info", null);
        StyleConstants.setFontFamily(info, "mono");
        StyleConstants.setForeground(info, new Color(0, 0, 200));

        std = consolePane.getStyledDocument().addStyle("Std", null);
        StyleConstants.setFontFamily(std, "mono");
        StyleConstants.setForeground(std, new Color(160, 160, 160));

        code = consolePane.getStyledDocument().addStyle("Code", null);
        StyleConstants.setFontFamily(code, "mono");
        StyleConstants.setForeground(code, new Color(40, 150, 40));

        error = consolePane.getStyledDocument().addStyle("Error", null);
        StyleConstants.setFontFamily(error, "mono");
        StyleConstants.setForeground(error, Color.RED);
    }

    @Override
    public void reset() {
        modelPane.setText("");
        consolePane.setText("");
    }

    @Override
    public void printModel(Object model) {
        try {
            StringBuilder sb = new StringBuilder();
            if (model != null) {
                try {
                    sb.append("entities:");
                    Class modelClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
                    Class entityClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Entity");
                    Class propertyClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Property");


                    Object entities[] = (Object[]) modelClass.getMethod("getEntities").invoke(model);
                    for (Object entity : entities) {
                        sb.append("\n  " + entityClass.getMethod("getName").invoke(entity) + ":");
                        sb.append("\n    properties:");
                        Object properties[] = (Object[]) entityClass.getMethod("getProperties").invoke(entity);
                        for (Object property : properties) {
                            sb.append("\n      " + propertyClass.getMethod("getName").invoke(property) + ": " + propertyClass.getMethod("getType").invoke(property));


                            try {
                                Method method = propertyClass.getMethod("getConstraints");
                                Object constraints[] = (Object[]) method.invoke(property);
                                if (constraints != null) {
                                    sb.append("(");
                                    for (Object constraint : constraints) {
                                        sb.append(constraint.toString() + ", ");
                                    }
                                    sb.append(")");
                                }
                            } catch (NoSuchMethodException ex) {
                                //Ked nie je metoda nic sa nevypise
                            }
                        }

                        try {
                            Method method = entityClass.getMethod("getOutgoingReferences");
                            Object references[] = (Object[]) method.invoke(entity);
                            sb.append("\n    references:");
                            if (references != null) {
                                for (Object reference : references) {
                                    sb.append("\n      " + reference.toString());
                                }
                            }
                        } catch (NoSuchMethodException ex) {
                            //Ked nie je metoda nic sa nevypise
                        }
                    }
                    try {
                        Class uiClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.ui.UI");
                        Object ui = modelClass.getMethod("getUi").invoke(model);
                        if (ui != null) {
                            sb.append("\nui:");
                            sb.append("\n  tables:");
                            Object tables[] = (Object[]) uiClass.getMethod("getTables").invoke(ui);
                            if (tables != null) {
                                for (Object table : tables) {
                                    sb.append("\n    " + table.toString());
                                }
                            }
                            sb.append("\n  forms:");
                            Object forms[] = (Object[]) uiClass.getMethod("getForms").invoke(ui);
                            if (tables != null) {
                                for (Object form : forms) {
                                    sb.append("\n   " + form.toString());
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

            modelPane.getStyledDocument().insertString(0, sb.toString(), null);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void printCode(String code) {
        printText(code, this.code);
    }

    @Override
    public void printInfo(String text) {
        printText(text, info);
    }

    @Override
    public void printError(String error) {
        printText(error, this.error);
    }

    protected void printText(String text, Style style) {
        try {
            consolePane.getStyledDocument().insertString(consolePane.getStyledDocument().getLength(), text + "\n", style);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
