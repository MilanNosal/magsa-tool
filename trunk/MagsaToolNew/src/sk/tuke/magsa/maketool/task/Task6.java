package sk.tuke.magsa.maketool.task;

import sk.tuke.magsa.maketool.PrintProvider;
import sk.tuke.magsa.maketool.State;
import sk.tuke.magsa.maketool.action.generator.DaoImplementationGenerator;
import sk.tuke.magsa.maketool.action.generator.DaoInterfaceGenerator;
import sk.tuke.magsa.maketool.action.generator.DatabaseScriptGenerator;
import sk.tuke.magsa.maketool.action.generator.EntityClassGenerator;
import sk.tuke.magsa.maketool.action.processor.ExternalParser;
import sk.tuke.magsa.maketool.action.MagsaContext;
import sk.tuke.magsa.maketool.action.processor.Yajco;
import sk.tuke.magsa.maketool.ui.ActionWrapper;

public class Task6 extends AbstractTaskPanel {

    public Task6() {
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
        
        databaseScriptGenerator.addInputs(model);
        databaseScriptGenerator.setOutput(databaseScript);
        databaseScriptGenerator.setAction(new ActionWrapper(new DatabaseScriptGenerator(), context, printProvider));

        entityClassGenerator.addInputs(model);
        entityClassGenerator.setOutput(entityClass);
        entityClassGenerator.setAction(new ActionWrapper(new EntityClassGenerator(), context, printProvider));

        daoInterfaceGenerator.addInputs(model);
        daoInterfaceGenerator.setOutput(daoInterface);
        daoInterfaceGenerator.setAction(new ActionWrapper(new DaoInterfaceGenerator(), context, printProvider));

        daoImplementationGenerator.addInputs(model);
        daoImplementationGenerator.setOutput(daoImplementation);
        daoImplementationGenerator.setAction(new ActionWrapper(new DaoImplementationGenerator(), context, printProvider));        
    }

    @Override
    public void init() {
        metamodel.setState(State.COMPLETED);
        modelFile.setState(State.COMPLETED);
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
        databaseScript = new sk.tuke.magsa.maketool.component.ResourceComponent();
        yajco = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        modelFile = new sk.tuke.magsa.maketool.component.ResourceComponent();
        databaseScriptGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        model = new sk.tuke.magsa.maketool.component.ResourceComponent();
        entityClassGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        entityClass = new sk.tuke.magsa.maketool.component.ResourceComponent();
        daoInterfaceGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        daoInterface = new sk.tuke.magsa.maketool.component.ResourceComponent();
        daoImplementationGenerator = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        daoImplementation = new sk.tuke.magsa.maketool.component.ResourceComponent();
        arrow3 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow4 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow5 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow6 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow7 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow8 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow12 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow13 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow16 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow17 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow18 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow22 = new sk.tuke.magsa.maketool.component.Arrow();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("sk/tuke/magsa/maketool/Bundle"); // NOI18N
        metamodel.setLabel(bundle.getString("metamodel")); // NOI18N
        add(metamodel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 130, 80));

        externalParser.setActionName(bundle.getString("parse")); // NOI18N
        externalParser.setLabel(bundle.getString("externalParser")); // NOI18N
        add(externalParser, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 120, 60));

        databaseScript.setLabel(bundle.getString("databaseScript")); // NOI18N
        add(databaseScript, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 160, 120, 50));

        yajco.setActionName(bundle.getString("generate")); // NOI18N
        yajco.setLabel(bundle.getString("yajco")); // NOI18N
        add(yajco, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 100, 60));

        modelFile.setLabel(bundle.getString("modelFile")); // NOI18N
        add(modelFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 105, 55));

        databaseScriptGenerator.setActionName(bundle.getString("generate")); // NOI18N
        databaseScriptGenerator.setLabel(bundle.getString("databaseScriptGenerator")); // NOI18N
        add(databaseScriptGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, 110, 50));

        model.setLabel(bundle.getString("model")); // NOI18N
        add(model, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 70, 60));

        entityClassGenerator.setActionName(bundle.getString("generate")); // NOI18N
        entityClassGenerator.setLabel(bundle.getString("entityClassGenerator")); // NOI18N
        add(entityClassGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, 110, 50));

        entityClass.setLabel(bundle.getString("entityClass")); // NOI18N
        add(entityClass, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, 120, 50));

        daoInterfaceGenerator.setActionName(bundle.getString("generate")); // NOI18N
        daoInterfaceGenerator.setLabel(bundle.getString("daoInterfaceGenerator")); // NOI18N
        add(daoInterfaceGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 300, 110, 60));

        daoInterface.setLabel(bundle.getString("daoInterface")); // NOI18N
        add(daoInterface, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 300, 120, 50));

        daoImplementationGenerator.setActionName(bundle.getString("generate")); // NOI18N
        daoImplementationGenerator.setLabel(bundle.getString("daoImplementationGenerator")); // NOI18N
        add(daoImplementationGenerator, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 370, 110, 50));

        daoImplementation.setLabel(bundle.getString("daoImplementation")); // NOI18N
        add(daoImplementation, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 370, 120, 50));

        arrow3.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        add(arrow3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 40, 20));

        arrow4.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        add(arrow4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 40, 20));
        add(arrow5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, 30, 30));
        add(arrow6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 240, 30, 30));
        add(arrow7, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, 30, 30));
        add(arrow8, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 380, 30, 30));

        arrow12.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow12.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTHEAST);
        add(arrow12, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 60, 30));
        add(arrow13, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, 30, 20));

        arrow16.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow16.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTHEAST);
        add(arrow16, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, 70, 90));

        arrow17.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow17.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.NORTHEAST);
        add(arrow17, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 60, 20));

        arrow18.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow18.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.NORTHEAST);
        add(arrow18, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, 80, 90));

        arrow22.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTH);
        add(arrow22, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 40, 50));

        jLabel4.setBackground(new java.awt.Color(255, 255, 153));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(bundle.getString("parserGenerationPhase")); // NOI18N
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setOpaque(true);
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 310, 150));

        jLabel5.setBackground(new java.awt.Color(255, 255, 153));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setOpaque(true);
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 140, 260));

        jLabel2.setBackground(new java.awt.Color(204, 204, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(bundle.getString("generationPhase")); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setOpaque(true);
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 520, 530));

        jLabel3.setBackground(new java.awt.Color(241, 182, 139));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(bundle.getString("parsingPhase")); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setOpaque(true);
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 530));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sk.tuke.magsa.maketool.component.Arrow arrow12;
    private sk.tuke.magsa.maketool.component.Arrow arrow13;
    private sk.tuke.magsa.maketool.component.Arrow arrow16;
    private sk.tuke.magsa.maketool.component.Arrow arrow17;
    private sk.tuke.magsa.maketool.component.Arrow arrow18;
    private sk.tuke.magsa.maketool.component.Arrow arrow22;
    private sk.tuke.magsa.maketool.component.Arrow arrow3;
    private sk.tuke.magsa.maketool.component.Arrow arrow4;
    private sk.tuke.magsa.maketool.component.Arrow arrow5;
    private sk.tuke.magsa.maketool.component.Arrow arrow6;
    private sk.tuke.magsa.maketool.component.Arrow arrow7;
    private sk.tuke.magsa.maketool.component.Arrow arrow8;
    private sk.tuke.magsa.maketool.component.ResourceComponent daoImplementation;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent daoImplementationGenerator;
    private sk.tuke.magsa.maketool.component.ResourceComponent daoInterface;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent daoInterfaceGenerator;
    private sk.tuke.magsa.maketool.component.ResourceComponent databaseScript;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent databaseScriptGenerator;
    private sk.tuke.magsa.maketool.component.ResourceComponent entityClass;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent entityClassGenerator;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent externalParser;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private sk.tuke.magsa.maketool.component.ResourceComponent metamodel;
    private sk.tuke.magsa.maketool.component.ResourceComponent model;
    private sk.tuke.magsa.maketool.component.ResourceComponent modelFile;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent yajco;
    // End of variables declaration//GEN-END:variables
}
