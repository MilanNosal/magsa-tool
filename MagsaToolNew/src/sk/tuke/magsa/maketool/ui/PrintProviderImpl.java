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
    private Style propertiesStyle;
    private Style entitiesStyle;
    private Style typesStyle;
    private Style constraintsStyle;
    private Style blackMono;
    private Style tablesStyle;
    private Style formsStyle;

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

        propertiesStyle = consolePane.getStyledDocument().addStyle("prop_style", null);
        StyleConstants.setFontFamily(propertiesStyle, "mono");
        StyleConstants.setBold(propertiesStyle, true);
        StyleConstants.setForeground(propertiesStyle, new Color(40, 150, 40));

        entitiesStyle = consolePane.getStyledDocument().addStyle("ent_style", null);
        StyleConstants.setFontFamily(entitiesStyle, "mono");
        StyleConstants.setBold(entitiesStyle, true);
        StyleConstants.setForeground(entitiesStyle, Color.BLUE);

        typesStyle = consolePane.getStyledDocument().addStyle("type_style", null);
        StyleConstants.setFontFamily(typesStyle, "mono");
        StyleConstants.setForeground(typesStyle, new Color(40, 150, 40));

        constraintsStyle = consolePane.getStyledDocument().addStyle("const_style", null);
        StyleConstants.setFontFamily(constraintsStyle, "mono");
        StyleConstants.setForeground(constraintsStyle, Color.RED);

        blackMono = consolePane.getStyledDocument().addStyle("black_mono", null);
        StyleConstants.setFontFamily(blackMono, "mono");
        StyleConstants.setBold(blackMono, true);
        StyleConstants.setForeground(blackMono, Color.BLACK);
        
        formsStyle = consolePane.getStyledDocument().addStyle("forms_style", null);
        StyleConstants.setFontFamily(formsStyle, "mono");
        StyleConstants.setBold(formsStyle, true);
        StyleConstants.setForeground(formsStyle, Color.BLUE);

        tablesStyle = consolePane.getStyledDocument().addStyle("tables_mono", null);
        StyleConstants.setFontFamily(tablesStyle, "mono");
        StyleConstants.setBold(tablesStyle, true);
        StyleConstants.setForeground(tablesStyle, new Color(40, 150, 40));
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
                printTextToModel("------------------- Zoznam entít -------------------", blackMono);
                Class modelClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
                Class entityClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Entity");
                Class propertyClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Property");


                Object entities[] = (Object[]) modelClass.getMethod("getEntities").invoke(model);
                for (Object entity : entities) {
                    printTextToModel("\n" + entityClass.getMethod("getName").invoke(entity), entitiesStyle);
                    printTextToModel(" {", blackMono);
                    Object properties[] = (Object[]) entityClass.getMethod("getProperties").invoke(entity);
                    for (Object property : properties) {
                        printTextToModel("\n   " + propertyClass.getMethod("getName").invoke(property), propertiesStyle);
                        printTextToModel(" : ", blackMono);
                        printTextToModel(propertyClass.getMethod("getType").invoke(property).toString(), typesStyle);

                        try {
                            Method method = propertyClass.getMethod("getConstraints");
                            Object constraints[] = (Object[]) method.invoke(property);
                            if (constraints != null) {
                                printTextToModel(" [", constraintsStyle);
                                boolean first = true;
                                for (Object constraint : constraints) {
                                    if (first != true) {
                                        printTextToModel(", ", constraintsStyle);
                                    } else {
                                        first = false;
                                    }
                                    printTextToModel(constraint.toString(), constraintsStyle);
                                }
                                printTextToModel("]", constraintsStyle);
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
                                printTextToModel("\n", blackMono);
                            }
                            for (Object reference : references) {
                                Object from = entityClass.getMethod("getName").invoke(methodFrom.invoke(reference));
                                Object to = entityClass.getMethod("getName").invoke(methodTo.invoke(reference));
                                printTextToModel("\n   referencia z ", blackMono);
                                printTextToModel(from.toString(), entitiesStyle);
                                printTextToModel(" do ", blackMono);
                                printTextToModel(to.toString(), entitiesStyle);
                            }
                        }
                    } catch (NoSuchMethodException ex) {
                        //Ked nie je metoda nic sa nevypise
                    }
                    printTextToModel("\n   }", blackMono);
                }
                // ------------------
                printTextToModel("\n--------------------------------------------------------------\n", blackMono);
                try {
                    Class uiClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.ui.UI");
                    Object ui = modelClass.getMethod("getUi").invoke(model);
                    if (ui != null) {
                        printTextToModel("\n\n------------------ Používateľské rozhranie ------------------", blackMono);
                        Object tables[] = (Object[]) uiClass.getMethod("getTables").invoke(ui);
                        if (tables != null) {
                            for (Object table : tables) {
                                printTextToModel("\n" + table.toString(), tablesStyle);
                            }
                        }
                        Object forms[] = (Object[]) uiClass.getMethod("getForms").invoke(ui);
                        if (tables != null) {
                            for (Object form : forms) {
                                printTextToModel("\n" + form.toString(), formsStyle);
                            }
                        }
                        printTextToModel("\n---------------------------------------------------------------------\n", blackMono);
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

    private void printTextToModel(String text, Style style) {
        try {
            modelPane.getStyledDocument().insertString(modelPane.getStyledDocument().getLength(), text, style);
        } catch (BadLocationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
