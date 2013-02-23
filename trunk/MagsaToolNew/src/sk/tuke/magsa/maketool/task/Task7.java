package sk.tuke.magsa.maketool.task;

import sk.tuke.magsa.maketool.PrintProvider;
import sk.tuke.magsa.maketool.State;
import sk.tuke.magsa.maketool.action.processor.ExternalParser;
import sk.tuke.magsa.maketool.action.MagsaContext;
import sk.tuke.magsa.maketool.action.processor.UIProcessor;
import sk.tuke.magsa.maketool.action.processor.Yajco;
import sk.tuke.magsa.maketool.ui.ActionWrapper;

public class Task7 extends AbstractTaskPanel {

    public Task7() {
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
        yajco = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        modelFile = new sk.tuke.magsa.maketool.component.ResourceComponent();
        uiFile = new sk.tuke.magsa.maketool.component.ResourceComponent();
        model = new sk.tuke.magsa.maketool.component.ResourceComponent();
        uiProcessor = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        model2 = new sk.tuke.magsa.maketool.component.ResourceComponent();
        arrow3 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow4 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow13 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow14 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow15 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow22 = new sk.tuke.magsa.maketool.component.Arrow();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("sk/tuke/magsa/maketool/Bundle"); // NOI18N
        metamodel.setLabel(bundle.getString("metamodel")); // NOI18N
        add(metamodel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 130, 80));

        externalParser.setActionName(bundle.getString("parse")); // NOI18N
        externalParser.setLabel(bundle.getString("externalParser")); // NOI18N
        add(externalParser, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 120, 60));

        arrow2.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow2.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.NORTHEAST);
        add(arrow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 370, 60));

        yajco.setActionName(bundle.getString("generate")); // NOI18N
        yajco.setLabel(bundle.getString("yajco")); // NOI18N
        add(yajco, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 100, 60));

        modelFile.setLabel(bundle.getString("modelFile")); // NOI18N
        add(modelFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 105, 55));

        uiFile.setLabel(bundle.getString("uiFile")); // NOI18N
        add(uiFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 100, 55));

        model.setLabel(bundle.getString("model")); // NOI18N
        add(model, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 70, 60));

        uiProcessor.setActionName(bundle.getString("compose")); // NOI18N
        uiProcessor.setLabel(bundle.getString("uiProcessor")); // NOI18N
        add(uiProcessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 90, 50));

        model2.setLabel(bundle.getString("model2")); // NOI18N
        add(model2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 270, 70, 50));

        arrow3.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        add(arrow3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 40, 20));

        arrow4.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        add(arrow4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 40, 20));
        add(arrow13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 30, 20));

        arrow14.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow14.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTHEAST);
        add(arrow14, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 20, 30));
        add(arrow15, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, 30, 30));

        arrow22.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTH);
        add(arrow22, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 40, 50));

        jLabel4.setBackground(new java.awt.Color(255, 255, 153));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(bundle.getString("parserGenerationPhase")); // NOI18N
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setOpaque(true);
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 310, 150));

        jLabel5.setBackground(new java.awt.Color(255, 255, 153));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setOpaque(true);
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 140, 260));

        jLabel1.setBackground(new java.awt.Color(175, 249, 165));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(bundle.getString("compositionPhase")); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setOpaque(true);
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 540, 530));

        jLabel3.setBackground(new java.awt.Color(241, 182, 139));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(bundle.getString("parsingPhase")); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setOpaque(true);
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 530));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sk.tuke.magsa.maketool.component.Arrow arrow13;
    private sk.tuke.magsa.maketool.component.Arrow arrow14;
    private sk.tuke.magsa.maketool.component.Arrow arrow15;
    private sk.tuke.magsa.maketool.component.Arrow arrow2;
    private sk.tuke.magsa.maketool.component.Arrow arrow22;
    private sk.tuke.magsa.maketool.component.Arrow arrow3;
    private sk.tuke.magsa.maketool.component.Arrow arrow4;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent externalParser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private sk.tuke.magsa.maketool.component.ResourceComponent metamodel;
    private sk.tuke.magsa.maketool.component.ResourceComponent model;
    private sk.tuke.magsa.maketool.component.ResourceComponent model2;
    private sk.tuke.magsa.maketool.component.ResourceComponent modelFile;
    private sk.tuke.magsa.maketool.component.ResourceComponent uiFile;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent uiProcessor;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent yajco;
    // End of variables declaration//GEN-END:variables
}
