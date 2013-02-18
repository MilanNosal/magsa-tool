package sk.tuke.magsa.maketool.task;

import sk.tuke.magsa.maketool.PrintProvider;
import sk.tuke.magsa.maketool.State;
import sk.tuke.magsa.maketool.action.ApplicationGenerator;
import sk.tuke.magsa.maketool.action.DaoImplementationGenerator;
import sk.tuke.magsa.maketool.action.DaoInterfaceGenerator;
import sk.tuke.magsa.maketool.action.DatabaseScriptGenerator;
import sk.tuke.magsa.maketool.action.EntityClassGenerator;
import sk.tuke.magsa.maketool.action.ExternalParser;
import sk.tuke.magsa.maketool.action.MagsaContext;
import sk.tuke.magsa.maketool.action.UIFormGenerator;
import sk.tuke.magsa.maketool.action.UIProcessor;
import sk.tuke.magsa.maketool.action.UITableGenerator;
import sk.tuke.magsa.maketool.action.Yajco;
import sk.tuke.magsa.maketool.ui.ActionWrapper;

public class Task8 extends AbstractTaskPanel {
    public Task8() {
        initComponents();
    }

    @Override
    public void configure(PrintProvider printProvider) {
        MagsaContext context = new MagsaContext();

        yajco.addInputs(metamodel);
        yajco.setOutput(externalParser);
        yajco.setAction(new ActionWrapper(new Yajco(), context, printProvider));

        externalParser.addInputs(modelFile, yajco);
        externalParser.setOutput(model);
        externalParser.setAction(new ActionWrapper(new ExternalParser(), context, printProvider));

        uiProcessor.addInputs(model, uiFile);
        uiProcessor.setOutput(model2);
        uiProcessor.setAction(new ActionWrapper(new UIProcessor(), context, printProvider));

        databaseScriptGenerator.addInputs(model2);
        databaseScriptGenerator.setOutput(databaseScript);
        databaseScriptGenerator.setAction(new ActionWrapper(new DatabaseScriptGenerator(), context, printProvider));

        entityClassGenerator.addInputs(model2);
        entityClassGenerator.setOutput(entityClass);
        entityClassGenerator.setAction(new ActionWrapper(new EntityClassGenerator(), context, printProvider));

        daoInterfaceGenerator.addInputs(model2);
        daoInterfaceGenerator.setOutput(daoInterface);
        daoInterfaceGenerator.setAction(new ActionWrapper(new DaoInterfaceGenerator(), context, printProvider));

        daoImplementationGenerator.addInputs(model2);
        daoImplementationGenerator.setOutput(daoImplementation);
        daoImplementationGenerator.setAction(new ActionWrapper(new DaoImplementationGenerator(), context, printProvider));

        uiFormGenerator.addInputs(model2);
        uiFormGenerator.setOutput(uiForm);
        uiFormGenerator.setAction(new ActionWrapper(new UIFormGenerator(), context, printProvider));

        uiTableGenerator.addInputs(model2);
        uiTableGenerator.setOutput(uiTable);
        uiTableGenerator.setAction(new ActionWrapper(new UITableGenerator(), context, printProvider));

        applicationGenerator.addInputs(model2);
        applicationGenerator.setOutput(application);
        applicationGenerator.setAction(new ActionWrapper(new ApplicationGenerator(), context, printProvider));
    }

    @Override
    public void init() {
        metamodel.setState(State.COMPLETED);
        modelFile.setState(State.COMPLETED);
        uiFile.setState(State.COMPLETED);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        metamodel = new sk.tuke.magsa.maketool.component.ResourceComponent();
        externalParser = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        arrow2 = new sk.tuke.magsa.maketool.component.Arrow();
        databaseScript = new sk.tuke.magsa.maketool.component.ResourceComponent();
        yajco = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        modelFile = new sk.tuke.magsa.maketool.component.ResourceComponent();
        uiFile = new sk.tuke.magsa.maketool.component.ResourceComponent();
        databaseScriptGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        model = new sk.tuke.magsa.maketool.component.ResourceComponent();
        uiProcessor = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        model2 = new sk.tuke.magsa.maketool.component.ResourceComponent();
        entityClassGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        entityClass = new sk.tuke.magsa.maketool.component.ResourceComponent();
        daoInterfaceGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        daoInterface = new sk.tuke.magsa.maketool.component.ResourceComponent();
        daoImplementationGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        daoImplementation = new sk.tuke.magsa.maketool.component.ResourceComponent();
        uiFormGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        uiForm = new sk.tuke.magsa.maketool.component.ResourceComponent();
        uiTableGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        uiTable = new sk.tuke.magsa.maketool.component.ResourceComponent();
        applicationGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        application = new sk.tuke.magsa.maketool.component.ResourceComponent();
        arrow3 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow4 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow5 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow6 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow7 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow8 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow9 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow10 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow11 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow12 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow13 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow14 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow15 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow16 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow17 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow18 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow19 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow20 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow21 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow22 = new sk.tuke.magsa.maketool.component.Arrow();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        metamodel.setLabel("Syntax jazyka entít<br> definovaná anotovanými<br>konštuktormi modelu");
        add(metamodel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 130, 80));

        externalParser.setActionName("Parse");
        externalParser.setLabel("Vygenerovaný<br>jazykový procesor");
        add(externalParser, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 120, 60));

        arrow2.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow2.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.NORTHEAST);
        add(arrow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 370, 60));

        databaseScript.setLabel("Vygenerovaný kód:<br>databázový skript");
        add(databaseScript, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, 120, 50));

        yajco.setActionName("Generate");
        yajco.setLabel("Yajco");
        add(yajco, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 100, 60));

        modelFile.setLabel("Zápis v jazyku entít<br>(model.el)");
        add(modelFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 105, 55));

        uiFile.setLabel("Zapis v jazyku UI<br> (ui.xml)");
        add(uiFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 100, 55));

        databaseScriptGenerator.setActionName("Generate");
        databaseScriptGenerator.setLabel("DbScriptGenerator");
        add(databaseScriptGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, 110, 50));

        model.setLabel("Model");
        add(model, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 70, 60));

        uiProcessor.setActionName("Compose");
        uiProcessor.setLabel("UI Processor");
        add(uiProcessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, 90, 50));

        model2.setLabel("Model");
        add(model2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 70, 50));

        entityClassGenerator.setActionName("Generate");
        entityClassGenerator.setLabel("Generátor tried entít");
        add(entityClassGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 100, 110, 50));

        entityClass.setLabel("Vygenerovaný kód:<br>triedy entít");
        add(entityClass, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 100, 120, 50));

        daoInterfaceGenerator.setActionName("Generate");
        daoInterfaceGenerator.setLabel("Generátor DAO<br> rozhraní");
        add(daoInterfaceGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, 110, 60));

        daoInterface.setLabel("Vygenerovaný kód:<br>DAO rozhrania");
        add(daoInterface, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 170, 120, 50));

        daoImplementationGenerator.setActionName("Generate");
        daoImplementationGenerator.setLabel("Generátor DAO");
        add(daoImplementationGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, 110, 50));

        daoImplementation.setLabel("Vygenerovaný kód:<br>DAO");
        add(daoImplementation, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 240, 120, 50));

        uiFormGenerator.setActionName("Generate");
        uiFormGenerator.setLabel("Generátor formulárov");
        add(uiFormGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 300, 110, 60));

        uiForm.setLabel("Vygenerovaný kód:<br>UI - formuláre");
        add(uiForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 310, 120, 50));

        uiTableGenerator.setActionName("Generate");
        uiTableGenerator.setLabel("Generátor tabuliek");
        add(uiTableGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 380, 110, 50));

        uiTable.setLabel("Vygenerovaný kód:<br>UI - tabuľky");
        add(uiTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 380, 120, 50));

        applicationGenerator.setActionName("Generate");
        applicationGenerator.setLabel("Generátor hlavnej <br>triedy");
        add(applicationGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 450, 110, 60));

        application.setLabel("Vygenerovaný kód:<br>hlavná trieda aplikácie");
        add(application, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 450, 120, 50));

        arrow3.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        add(arrow3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 40, 20));

        arrow4.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        add(arrow4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 40, 20));
        add(arrow5, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 40, 30, 30));
        add(arrow6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 110, 30, 30));
        add(arrow7, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 180, 30, 30));
        add(arrow8, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 250, 30, 30));
        add(arrow9, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 320, 30, 30));
        add(arrow10, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 390, 30, 30));
        add(arrow11, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 460, 30, 30));

        arrow12.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow12.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.NORTHEAST);
        add(arrow12, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 60, 70));
        add(arrow13, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 30, 20));

        arrow14.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow14.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTHEAST);
        add(arrow14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, 20, 30));
        add(arrow15, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 30, 30));

        arrow16.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow16.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.NORTHEAST);
        add(arrow16, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 280, 60, 20));

        arrow17.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow17.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.NORTHEAST);
        add(arrow17, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 70, 160));

        arrow18.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow18.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.NORTHEAST);
        add(arrow18, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 80, 230));

        arrow19.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow19.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTHEAST);
        add(arrow19, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, 60, 20));

        arrow20.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow20.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTHEAST);
        add(arrow20, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 330, 60, 80));

        arrow21.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow21.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTHEAST);
        add(arrow21, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 330, 80, 160));

        arrow22.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTH);
        add(arrow22, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 40, 50));

        jLabel4.setBackground(new java.awt.Color(255, 255, 153));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Generovanie jazykového procesora");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setOpaque(true);
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 310, 150));

        jLabel5.setBackground(new java.awt.Color(255, 255, 153));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setOpaque(true);
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 140, 260));

        jLabel1.setBackground(new java.awt.Color(175, 249, 165));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Kompozícia jazykov");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setOpaque(true);
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 260, 530));

        jLabel2.setBackground(new java.awt.Color(204, 204, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Fáza generovania");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setOpaque(true);
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 410, 530));

        jLabel3.setBackground(new java.awt.Color(241, 182, 139));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fáza rozpoznávania");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setOpaque(true);
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 530));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sk.tuke.magsa.maketool.component.ResourceComponent application;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent applicationGenerator;
    private sk.tuke.magsa.maketool.component.Arrow arrow10;
    private sk.tuke.magsa.maketool.component.Arrow arrow11;
    private sk.tuke.magsa.maketool.component.Arrow arrow12;
    private sk.tuke.magsa.maketool.component.Arrow arrow13;
    private sk.tuke.magsa.maketool.component.Arrow arrow14;
    private sk.tuke.magsa.maketool.component.Arrow arrow15;
    private sk.tuke.magsa.maketool.component.Arrow arrow16;
    private sk.tuke.magsa.maketool.component.Arrow arrow17;
    private sk.tuke.magsa.maketool.component.Arrow arrow18;
    private sk.tuke.magsa.maketool.component.Arrow arrow19;
    private sk.tuke.magsa.maketool.component.Arrow arrow2;
    private sk.tuke.magsa.maketool.component.Arrow arrow20;
    private sk.tuke.magsa.maketool.component.Arrow arrow21;
    private sk.tuke.magsa.maketool.component.Arrow arrow22;
    private sk.tuke.magsa.maketool.component.Arrow arrow3;
    private sk.tuke.magsa.maketool.component.Arrow arrow4;
    private sk.tuke.magsa.maketool.component.Arrow arrow5;
    private sk.tuke.magsa.maketool.component.Arrow arrow6;
    private sk.tuke.magsa.maketool.component.Arrow arrow7;
    private sk.tuke.magsa.maketool.component.Arrow arrow8;
    private sk.tuke.magsa.maketool.component.Arrow arrow9;
    private sk.tuke.magsa.maketool.component.ResourceComponent daoImplementation;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent daoImplementationGenerator;
    private sk.tuke.magsa.maketool.component.ResourceComponent daoInterface;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent daoInterfaceGenerator;
    private sk.tuke.magsa.maketool.component.ResourceComponent databaseScript;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent databaseScriptGenerator;
    private sk.tuke.magsa.maketool.component.ResourceComponent entityClass;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent entityClassGenerator;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent externalParser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private sk.tuke.magsa.maketool.component.ResourceComponent metamodel;
    private sk.tuke.magsa.maketool.component.ResourceComponent model;
    private sk.tuke.magsa.maketool.component.ResourceComponent model2;
    private sk.tuke.magsa.maketool.component.ResourceComponent modelFile;
    private sk.tuke.magsa.maketool.component.ResourceComponent uiFile;
    private sk.tuke.magsa.maketool.component.ResourceComponent uiForm;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent uiFormGenerator;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent uiProcessor;
    private sk.tuke.magsa.maketool.component.ResourceComponent uiTable;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent uiTableGenerator;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent yajco;
    // End of variables declaration//GEN-END:variables
}