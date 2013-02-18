package sk.tuke.magsa.maketool.task;

import sk.tuke.magsa.maketool.PrintProvider;
import sk.tuke.magsa.maketool.State;
import sk.tuke.magsa.maketool.action.ConstraintBuilder;
import sk.tuke.magsa.maketool.action.DaoImplementationGenerator;
import sk.tuke.magsa.maketool.action.DaoInterfaceGenerator;
import sk.tuke.magsa.maketool.action.DatabaseScriptGenerator;
import sk.tuke.magsa.maketool.action.EntityClassGenerator;
import sk.tuke.magsa.maketool.action.LineParser;
import sk.tuke.magsa.maketool.action.MagsaContext;
import sk.tuke.magsa.maketool.ui.ActionWrapper;

public class Task4 extends AbstractTaskPanel {
    public Task4() {
        initComponents();
    }

    @Override
    public void configure(PrintProvider printProvider) {
        MagsaContext context = new MagsaContext();

        lineParser.addInputs(modelDir);
        lineParser.setOutput(model);
        lineParser.setAction(new ActionWrapper(new LineParser(), context, printProvider));

        constraintBuilder.addInputs(model, constraintClass);
        constraintBuilder.setOutput(model2);        
        constraintBuilder.setAction(new ActionWrapper(new ConstraintBuilder(), context, printProvider));
        
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
    }

    @Override
    public void init() {
        modelDir.setState(State.COMPLETED);
        constraintClass.setState(State.COMPLETED);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        arrow2 = new sk.tuke.magsa.maketool.component.Arrow();
        databaseScript = new sk.tuke.magsa.maketool.component.ResourceComponent();
        modelDir = new sk.tuke.magsa.maketool.component.ResourceComponent();
        constraintClass = new sk.tuke.magsa.maketool.component.ResourceComponent();
        databaseScriptGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        model = new sk.tuke.magsa.maketool.component.ResourceComponent();
        constraintBuilder = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        model2 = new sk.tuke.magsa.maketool.component.ResourceComponent();
        entityClassGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        entityClass = new sk.tuke.magsa.maketool.component.ResourceComponent();
        daoInterfaceGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        daoInterface = new sk.tuke.magsa.maketool.component.ResourceComponent();
        daoImplementationGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        daoImplementation = new sk.tuke.magsa.maketool.component.ResourceComponent();
        arrow5 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow6 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow7 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow8 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow12 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow13 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow14 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow15 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow16 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow19 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow20 = new sk.tuke.magsa.maketool.component.Arrow();
        lineParser = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        arrow4 = new sk.tuke.magsa.maketool.component.Arrow();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        arrow2.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow2.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.NORTHEAST);
        add(arrow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 270, 60));

        databaseScript.setLabel("Vygenerovaný kód:<br>databázový skript");
        add(databaseScript, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, 120, 50));

        modelDir.setLabel("Zápis v jazyku entít<br>(adresár model)");
        add(modelDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 105, 55));

        constraintClass.setLabel("Zápis v jazyku obmedzení<br>(PersonalistikaObmedzenia.java)");
        add(constraintClass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 180, 55));

        databaseScriptGenerator.setActionName("Generate");
        databaseScriptGenerator.setLabel("DbScriptGenerator");
        add(databaseScriptGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 170, 110, 50));

        model.setLabel("Model");
        add(model, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 70, 60));

        constraintBuilder.setActionName("Compose");
        constraintBuilder.setLabel("ConstraintBuilder");
        add(constraintBuilder, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, 90, 60));

        model2.setLabel("Model");
        add(model2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 70, 60));

        entityClassGenerator.setActionName("Generate");
        entityClassGenerator.setLabel("Generátor tried entít");
        add(entityClassGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, 110, 50));

        entityClass.setLabel("Vygenerovaný kód:<br>triedy entít");
        add(entityClass, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 240, 120, 50));

        daoInterfaceGenerator.setActionName("Generate");
        daoInterfaceGenerator.setLabel("Generátor DAO<br> rozhraní");
        add(daoInterfaceGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 310, 110, 60));

        daoInterface.setLabel("Vygenerovaný kód:<br>DAO rozhrania");
        add(daoInterface, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 310, 120, 50));

        daoImplementationGenerator.setActionName("Generate");
        daoImplementationGenerator.setLabel("Generátor DAO");
        add(daoImplementationGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 380, 110, 50));

        daoImplementation.setLabel("Vygenerovaný kód:<br>DAO");
        add(daoImplementation, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 380, 120, 50));
        add(arrow5, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 180, 30, 30));
        add(arrow6, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 250, 30, 30));
        add(arrow7, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 320, 30, 30));
        add(arrow8, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 390, 30, 30));

        arrow12.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow12.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.NORTHEAST);
        add(arrow12, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 60, 70));
        add(arrow13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 50, 20));

        arrow14.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow14.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTHEAST);
        add(arrow14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, 20, 30));
        add(arrow15, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 30, 30));

        arrow16.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow16.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.NORTHEAST);
        add(arrow16, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 280, 60, 20));

        arrow19.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow19.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTHEAST);
        add(arrow19, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, 60, 20));

        arrow20.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow20.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTHEAST);
        add(arrow20, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 330, 60, 80));

        lineParser.setActionName("Parse");
        lineParser.setLabel("LineParser");
        add(lineParser, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 100, 60));

        arrow4.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        add(arrow4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 50, 20));

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
    private sk.tuke.magsa.maketool.component.Arrow arrow12;
    private sk.tuke.magsa.maketool.component.Arrow arrow13;
    private sk.tuke.magsa.maketool.component.Arrow arrow14;
    private sk.tuke.magsa.maketool.component.Arrow arrow15;
    private sk.tuke.magsa.maketool.component.Arrow arrow16;
    private sk.tuke.magsa.maketool.component.Arrow arrow19;
    private sk.tuke.magsa.maketool.component.Arrow arrow2;
    private sk.tuke.magsa.maketool.component.Arrow arrow20;
    private sk.tuke.magsa.maketool.component.Arrow arrow4;
    private sk.tuke.magsa.maketool.component.Arrow arrow5;
    private sk.tuke.magsa.maketool.component.Arrow arrow6;
    private sk.tuke.magsa.maketool.component.Arrow arrow7;
    private sk.tuke.magsa.maketool.component.Arrow arrow8;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent constraintBuilder;
    private sk.tuke.magsa.maketool.component.ResourceComponent constraintClass;
    private sk.tuke.magsa.maketool.component.ResourceComponent daoImplementation;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent daoImplementationGenerator;
    private sk.tuke.magsa.maketool.component.ResourceComponent daoInterface;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent daoInterfaceGenerator;
    private sk.tuke.magsa.maketool.component.ResourceComponent databaseScript;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent databaseScriptGenerator;
    private sk.tuke.magsa.maketool.component.ResourceComponent entityClass;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent entityClassGenerator;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent lineParser;
    private sk.tuke.magsa.maketool.component.ResourceComponent model;
    private sk.tuke.magsa.maketool.component.ResourceComponent model2;
    private sk.tuke.magsa.maketool.component.ResourceComponent modelDir;
    // End of variables declaration//GEN-END:variables
}