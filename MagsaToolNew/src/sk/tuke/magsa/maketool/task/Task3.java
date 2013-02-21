package sk.tuke.magsa.maketool.task;

import sk.tuke.magsa.maketool.PrintProvider;
import sk.tuke.magsa.maketool.State;
import sk.tuke.magsa.maketool.action.processor.ConstraintBuilder;
import sk.tuke.magsa.maketool.action.processor.LineParser;
import sk.tuke.magsa.maketool.action.MagsaContext;
import sk.tuke.magsa.maketool.ui.ActionWrapper;

public class Task3 extends AbstractTaskPanel {

    public Task3() {
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
        modelDir = new sk.tuke.magsa.maketool.component.ResourceComponent();
        constraintClass = new sk.tuke.magsa.maketool.component.ResourceComponent();
        model = new sk.tuke.magsa.maketool.component.ResourceComponent();
        constraintBuilder = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        model2 = new sk.tuke.magsa.maketool.component.ResourceComponent();
        arrow13 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow14 = new sk.tuke.magsa.maketool.component.Arrow();
        arrow15 = new sk.tuke.magsa.maketool.component.Arrow();
        lineParser = new sk.tuke.magsa.maketool.component.ExecutableResourceComponent();
        arrow4 = new sk.tuke.magsa.maketool.component.Arrow();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        arrow2.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow2.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.NORTHEAST);
        add(arrow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, 280, 60));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("sk/tuke/magsa/maketool/Bundle"); // NOI18N
        modelDir.setLabel(bundle.getString("modelDir")); // NOI18N
        add(modelDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 105, 55));

        constraintClass.setLabel(bundle.getString("constraintClass")); // NOI18N
        add(constraintClass, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 180, 55));

        model.setLabel(bundle.getString("model")); // NOI18N
        add(model, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 70, 60));

        constraintBuilder.setActionName(bundle.getString("compose")); // NOI18N
        constraintBuilder.setLabel(bundle.getString("constraintBuilder")); // NOI18N
        add(constraintBuilder, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 280, 90, 60));

        model2.setLabel(bundle.getString("model2")); // NOI18N
        add(model2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 280, 70, 60));
        add(arrow13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, 50, 20));

        arrow14.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        arrow14.setOrientation(sk.tuke.magsa.maketool.component.Arrow.Orientation.SOUTHEAST);
        add(arrow14, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, 20, 30));
        add(arrow15, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 290, 30, 30));

        lineParser.setActionName(bundle.getString("parse")); // NOI18N
        lineParser.setLabel(bundle.getString("lineParser")); // NOI18N
        add(lineParser, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 100, 60));

        arrow4.setBarbStyle(sk.tuke.magsa.maketool.component.Arrow.BarbStyle.FILLED);
        add(arrow4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 50, 20));

        jLabel1.setBackground(new java.awt.Color(175, 249, 165));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(bundle.getString("compositionPhase")); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setOpaque(true);
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 520, 530));

        jLabel3.setBackground(new java.awt.Color(241, 182, 139));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(bundle.getString("parsingPhase")); // NOI18N
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel3.setOpaque(true);
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 530));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sk.tuke.magsa.maketool.component.Arrow arrow13;
    private sk.tuke.magsa.maketool.component.Arrow arrow14;
    private sk.tuke.magsa.maketool.component.Arrow arrow15;
    private sk.tuke.magsa.maketool.component.Arrow arrow2;
    private sk.tuke.magsa.maketool.component.Arrow arrow4;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent constraintBuilder;
    private sk.tuke.magsa.maketool.component.ResourceComponent constraintClass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private sk.tuke.magsa.maketool.component.ExecutableResourceComponent lineParser;
    private sk.tuke.magsa.maketool.component.ResourceComponent model;
    private sk.tuke.magsa.maketool.component.ResourceComponent model2;
    private sk.tuke.magsa.maketool.component.ResourceComponent modelDir;
    // End of variables declaration//GEN-END:variables
}
