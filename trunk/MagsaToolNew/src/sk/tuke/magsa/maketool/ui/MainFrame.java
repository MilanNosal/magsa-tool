package sk.tuke.magsa.maketool.ui;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import sk.tuke.magsa.maketool.MagsaConfig;
import sk.tuke.magsa.maketool.PrintProvider;
import sk.tuke.magsa.maketool.task.AbstractTaskPanel;

public class MainFrame extends javax.swing.JFrame {
    private final PrintProvider printProvider;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();

        jTextField4.setText(MagsaConfig.getInstance().getModelDir());
        jTextField3.setText(MagsaConfig.getInstance().getConstraintClass());
        jTextField1.setText(MagsaConfig.getInstance().getModelFile());
        jTextField2.setText(MagsaConfig.getInstance().getUiFile());

        printProvider = new PrintProviderImpl(modelPane, consolePane);
        
        configure(printProvider);
        init();
    }
    
    private void configure(PrintProvider printProvider) {
        for (Component component : tasks.getComponents()) {
            if (component instanceof AbstractTaskPanel) {
                ((AbstractTaskPanel) component).configure(printProvider);
            }
        }
    }
    
    private void init() {
        for (Component component : tasks.getComponents()) {
            if (component instanceof AbstractTaskPanel) {
                ((AbstractTaskPanel) component).init();
            }
        }
    }

    private void openProject() {
        //File curr = new File(System.getProperty("user.dir"));
        //TODO - opravit nastavenie cesty
        File curr = new File(System.getProperty("user.dir") + "/..");
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setCurrentDirectory(curr);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                curr = chooser.getSelectedFile();
                printProvider.printInfo("Načítavam projekt z adresára " + curr);
                MagsaConfig.getInstance().setProjectPath(curr.getCanonicalPath());
            }
            catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //        if (!curr.getAbsolutePath().endsWith("magsa")) {
            //            JFileChooser chooser = new JFileChooser();
            //            chooser.setLocation(50, 50);
            //            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //            chooser.setDialogTitle("Vyberte adresár projektu MAGSA");
            //
            //            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            //            chooser.showOpenDialog(this);
            //            curr = chooser.getSelectedFile();
            //        }
            //
            //        try {
            //            magsaLogic.setMagsaPath(curr.getAbsolutePath());
            //            magsaLogic.init();
            //        } catch (Exception ex) {
            //            //Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            //            getOutput().printError("Chyba pri načítavaní projektu: ").printError(ex.getMessage()).printError("Presvedčte sa, že ste vybrali správny adresár. Pre detailnejšie informácie pozrite stacktrace nižšie.");
            //            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //            PrintStream ps = new PrintStream(baos);
            //            ex.printStackTrace(ps);
            //            getOutput().printError("\nStackTrace:").printError(baos.toString());
            //        }
            
        }


//        if (!curr.getAbsolutePath().endsWith("magsa")) {
//            JFileChooser chooser = new JFileChooser();
//            chooser.setLocation(50, 50);
//            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//            chooser.setDialogTitle("Vyberte adresár projektu MAGSA");
//
//            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
//            chooser.showOpenDialog(this);
//            curr = chooser.getSelectedFile();
//        }
//
//        try {
//            magsaLogic.setMagsaPath(curr.getAbsolutePath());
//            magsaLogic.init();
//        } catch (Exception ex) {
//            //Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
//            getOutput().printError("Chyba pri načítavaní projektu: ").printError(ex.getMessage()).printError("Presvedčte sa, že ste vybrali správny adresár. Pre detailnejšie informácie pozrite stacktrace nižšie.");
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            PrintStream ps = new PrintStream(baos);
//            ex.printStackTrace(ps);
//            getOutput().printError("\nStackTrace:").printError(baos.toString());
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        centerPanel = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        tasks = new javax.swing.JTabbedPane();
        task11 = new sk.tuke.magsa.maketool.task.Task1();
        task21 = new sk.tuke.magsa.maketool.task.Task2();
        task31 = new sk.tuke.magsa.maketool.task.Task3();
        task41 = new sk.tuke.magsa.maketool.task.Task4();
        task51 = new sk.tuke.magsa.maketool.task.Task5();
        task61 = new sk.tuke.magsa.maketool.task.Task6();
        task71 = new sk.tuke.magsa.maketool.task.Task7();
        task81 = new sk.tuke.magsa.maketool.task.Task8();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        consolePane = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        modelPane = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        nacitatMenu = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        zavrietMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MagsaTool");

        topPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel6.setText("Rel. cesta k priečinku model:");
        topPanel.add(jLabel6);

        jTextField4.setColumns(20);
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });
        topPanel.add(jTextField4);

        jLabel5.setText("Trieda s def. interného DSL:");
        topPanel.add(jLabel5);

        jTextField3.setColumns(20);
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });
        topPanel.add(jTextField3);

        jLabel3.setText("Rel. cesta k model.el:");
        topPanel.add(jLabel3);

        jTextField1.setColumns(20);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        topPanel.add(jTextField1);

        jLabel4.setText("Rel. cesta k ui.xml:");
        topPanel.add(jLabel4);

        jTextField2.setColumns(20);
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        topPanel.add(jTextField2);

        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

        centerPanel.setLayout(new java.awt.BorderLayout());

        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        tasks.addTab("Cvičenie 2 (úloha 1)", task11);
        tasks.addTab("Cvičenie 3 (úloha 2)", task21);
        tasks.addTab("Cvičenie 5 (úloha 3)", task31);
        tasks.addTab("Cvičenie 6 (úloha 4)", task41);
        tasks.addTab("Cvičenie 8 (úloha 5)", task51);
        tasks.addTab("Cvičenie 9 (úloha 6)", task61);
        tasks.addTab("Cvičenie 11 (úloha 7)", task71);
        tasks.addTab("Cvičenie 12 (úloha 8)", task81);

        jSplitPane2.setLeftComponent(tasks);

        jSplitPane1.setDividerLocation(500);

        jScrollPane2.setViewportView(consolePane);

        jLabel1.setText("Konzola");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 618, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel1);

        jScrollPane1.setViewportView(modelPane);

        jLabel2.setText("Aktuálna reprezentácia modelu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 331, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel2);

        jSplitPane2.setRightComponent(jSplitPane1);

        centerPanel.add(jSplitPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Projekt MaGSA");

        nacitatMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        nacitatMenu.setText("Načítať projekt");
        nacitatMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nacitatMenuActionPerformed(evt);
            }
        });
        jMenu1.add(nacitatMenu);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Reštartovať projekt");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        zavrietMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        zavrietMenu.setText("Zavrieť projekt");
        zavrietMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zavrietMenuActionPerformed(evt);
            }
        });
        jMenu1.add(zavrietMenu);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        //magsaLogic.setModeldef(jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        //magsaLogic.setUidef(jTextField2.getText());
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        //magsaLogic.setIdsldef(jTextField3.getText());
    }//GEN-LAST:event_jTextField3KeyReleased

    private void nacitatMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nacitatMenuActionPerformed
        openProject();
    }//GEN-LAST:event_nacitatMenuActionPerformed

    private void zavrietMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zavrietMenuActionPerformed
//        magsaLogic.reset();
    }//GEN-LAST:event_zavrietMenuActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
//        magsaLogic.setModelDirdef(jTextField4.getText());
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
//        try {
//            magsaLogic.restart();
//        } catch (Exception ex) {
//            getOutput().printError("Tu by som sa nemal nikdy dostat.");
//        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel centerPanel;
    private javax.swing.JTextPane consolePane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextPane modelPane;
    private javax.swing.JMenuItem nacitatMenu;
    private sk.tuke.magsa.maketool.task.Task1 task11;
    private sk.tuke.magsa.maketool.task.Task2 task21;
    private sk.tuke.magsa.maketool.task.Task3 task31;
    private sk.tuke.magsa.maketool.task.Task4 task41;
    private sk.tuke.magsa.maketool.task.Task5 task51;
    private sk.tuke.magsa.maketool.task.Task6 task61;
    private sk.tuke.magsa.maketool.task.Task7 task71;
    private sk.tuke.magsa.maketool.task.Task8 task81;
    private javax.swing.JTabbedPane tasks;
    private javax.swing.JPanel topPanel;
    private javax.swing.JMenuItem zavrietMenu;
    // End of variables declaration//GEN-END:variables
}
