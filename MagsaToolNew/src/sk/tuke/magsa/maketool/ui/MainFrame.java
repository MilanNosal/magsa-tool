package sk.tuke.magsa.maketool.ui;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
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

        modelDirTextField.setText(MagsaConfig.getInstance().getModelDir());
        constraintClassTextField.setText(MagsaConfig.getInstance().getConstraintClass());
        modelFileTextField.setText(MagsaConfig.getInstance().getModelFile());
        uiFileTextField.setText(MagsaConfig.getInstance().getUiFile());

        printProvider = new PrintProviderImpl(modelPane, consolePane);

        configure(printProvider);
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

    private void reset() {
        for (Component component : tasks.getComponents()) {
            if (component instanceof AbstractTaskPanel) {
                ((AbstractTaskPanel) component).reset();
            }
        }
    }

    private void openProject() {
        //File curr = new File(System.getProperty("user.dir"));
        //TODO - opravit nastavenie cesty
        printProvider.reset();
        File currentDirectory = new File(System.getProperty("user.dir") + "/..");
        try {
            if (!currentDirectory.getCanonicalPath().endsWith("magsa")) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    currentDirectory = chooser.getSelectedFile();
                } else {
                    return;
                }
            }
            
            MagsaConfig.getInstance().setProjectPath(currentDirectory.getCanonicalPath());
            printProvider.printInfo("Načítavam projekt z adresára " + MagsaConfig.getInstance().getProjectPath());

            buildProject();

            // maly test pre istotu -- ale tn by sa mohol vyhodit
            MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
            ClassLoader.getSystemClassLoader().getResourceAsStream("generator.properties");

            reset();
            init();

            printProvider.printInfo("Projekt bol úspešne načítaný");
        } catch (Exception ex) {
            printProvider.printError("Chyba: " + ex.getMessage());
        }
    }

    private void refreshProject() {
        printProvider.reset();
        try {
            printProvider.printInfo("Obnovujem projekt z adresára " + MagsaConfig.getInstance().getProjectPath());
            buildProject();

            // Aj toto predpokladam ze treba -- da sa nahradit volanim setprojectpath s povodnou hodnotou
            // resp. da sa to dat do buildu, ale potom to bude zbytocne dvakrat volane
            // pri otvarani noveho projektu, pretoze pred zacatim buildu uz musi
            // byt projectpath nastaveny
            MagsaConfig.getInstance().refreshClassLoader();

            // maly test pre istotu -- ale tn by sa mohol vyhodit
            MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
            ClassLoader.getSystemClassLoader().getResourceAsStream("generator.properties");

            reset();
            init();

            printProvider.printInfo("Projekt bol úspešne obnovený");
        } catch (Exception ex) {
            printProvider.printError("Chyba: " + ex.getMessage());
        }
    }

    private void buildProject() throws Exception {
        File buildFile = new File(
                MagsaConfig.getInstance().getProjectPath() + "\\nbproject\\build-impl.xml");
        Project project = new Project();
        project.setUserProperty("ant.file", buildFile.getAbsolutePath());
        project.init();

        ProjectHelper helper = ProjectHelper.getProjectHelper();
        helper.parse(project, buildFile);

        project.executeTarget("clean");
        project.executeTarget("compile");
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
        modelDirTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        constraintClassTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        modelFileTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        uiFileTextField = new javax.swing.JTextField();
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

        modelDirTextField.setColumns(17);
        modelDirTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                modelDirTextFieldKeyReleased(evt);
            }
        });
        topPanel.add(modelDirTextField);

        jLabel5.setText("Trieda s def. interného DSL:");
        topPanel.add(jLabel5);

        constraintClassTextField.setColumns(17);
        constraintClassTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                constraintClassTextFieldKeyReleased(evt);
            }
        });
        topPanel.add(constraintClassTextField);

        jLabel3.setText("Rel. cesta k model.el:");
        topPanel.add(jLabel3);

        modelFileTextField.setColumns(17);
        modelFileTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                modelFileTextFieldKeyReleased(evt);
            }
        });
        topPanel.add(modelFileTextField);

        jLabel4.setText("Rel. cesta k ui.xml:");
        topPanel.add(jLabel4);

        uiFileTextField.setColumns(17);
        uiFileTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                uiFileTextFieldKeyReleased(evt);
            }
        });
        topPanel.add(uiFileTextField);

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
                        .addGap(0, 512, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
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

    private void modelFileTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modelFileTextFieldKeyReleased
        MagsaConfig.getInstance().setModelFile(modelFileTextField.getText());
    }//GEN-LAST:event_modelFileTextFieldKeyReleased

    private void uiFileTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uiFileTextFieldKeyReleased
        MagsaConfig.getInstance().setUiFile(uiFileTextField.getText());
    }//GEN-LAST:event_uiFileTextFieldKeyReleased

    private void constraintClassTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_constraintClassTextFieldKeyReleased
        MagsaConfig.getInstance().setConstraintClass(constraintClassTextField.getText());
    }//GEN-LAST:event_constraintClassTextFieldKeyReleased

    private void nacitatMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nacitatMenuActionPerformed
        openProject();
    }//GEN-LAST:event_nacitatMenuActionPerformed

    private void zavrietMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zavrietMenuActionPerformed
        reset();
        // tu by malo toto stacit, znemozni to pracovat s polozkami a pri
        // novom nacitavani sa resetne aj zvysok, tzn. projectpath atd.
    }//GEN-LAST:event_zavrietMenuActionPerformed

    private void modelDirTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modelDirTextFieldKeyReleased
        MagsaConfig.getInstance().setModelDir(modelDirTextField.getText().trim());
    }//GEN-LAST:event_modelDirTextFieldKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        refreshProject();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel centerPanel;
    private javax.swing.JTextPane consolePane;
    private javax.swing.JTextField constraintClassTextField;
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
    private javax.swing.JTextField modelDirTextField;
    private javax.swing.JTextField modelFileTextField;
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
    private javax.swing.JTextField uiFileTextField;
    private javax.swing.JMenuItem zavrietMenu;
    // End of variables declaration//GEN-END:variables
}
