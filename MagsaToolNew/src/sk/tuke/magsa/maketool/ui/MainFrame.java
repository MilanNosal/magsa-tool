package sk.tuke.magsa.maketool.ui;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sk.tuke.magsa.maketool.PrintProvider;
import sk.tuke.magsa.maketool.component.ExecutableResourceComponent;
import sk.tuke.magsa.maketool.core.MagsaConfig;
import sk.tuke.magsa.maketool.core.Project;
import sk.tuke.magsa.maketool.task.AbstractTaskPanel;

public class MainFrame extends javax.swing.JFrame {

    private final Project project = new Project();

    private final PrintProvider printProvider;

    private final AbstractTaskPanel[] tasks;

    private int previuosSelectedTabIndex;

    private MainGlassPane glassPane = new MainGlassPane();

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();

        tasks = new AbstractTaskPanel[]{task1, task2, task3, task4, task5, task6, task7, task8};

        ImageIcon img = new ImageIcon(getClass().getResource("/resources/icon.png"));
        this.setIconImage(img.getImage());

        modelDirTextField.setText(MagsaConfig.getInstance().getModelDir());
        constraintClassTextField.setText(MagsaConfig.getInstance().getConstraintClass());
        modelFileTextField.setText(MagsaConfig.getInstance().getModelFile());
        uiFileTextField.setText(MagsaConfig.getInstance().getUiFile());

        printProvider = new HTMLPrintProviderImpl(modelPane, consolePane);

        configure(printProvider);

        tasksTab.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                highlightInfoAfterTabChange();
            }
        });

        //set glass pane
        setGlassPane(glassPane);
    }

    private void configure(PrintProvider printProvider) {
        for (AbstractTaskPanel task : tasks) {
            task.configure(printProvider);
        }
    }

    private void reset() {
        for (AbstractTaskPanel task : tasks) {
            task.reset();
        }
    }

    private void init() {
        for (AbstractTaskPanel task : tasks) {
            task.init();
        }
    }

    private void openProject() {
        Cursor oldCursor = getCursor();
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            printProvider.reset();

            //File curr = new File(System.getProperty("user.dir"));
            //TODO - opravit nastavenie cesty
            File currentDirectory = new File(System.getProperty("user.dir"));

            try {
                if (!project.isMagsaProject(currentDirectory)) {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                        currentDirectory = chooser.getSelectedFile();
                    } else {
                        return;
                    }
                    project.isMagsaProject(currentDirectory);
                }
                
                String path = currentDirectory.getCanonicalPath();

                //Nacitanie a build projektu
                project.openProject(path);

                //Resetovanie komponentov (State.UNAVAILABLE) a nastavenie inicialneho stavu
                reset();
                init();

                printProvider.printInfo(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("sk/tuke/magsa/maketool/Bundle").getString("PROJEKT_USPESNE_NACITANY"), new Object[]{path}));
            } catch (Exception ex) {
                printProvider.printError(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("sk/tuke/magsa/maketool/Bundle").getString("CHYBA"), new Object[]{ex.getMessage()}));
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, java.util.ResourceBundle.getBundle("sk/tuke/magsa/maketool/Bundle").getString("ERROR_OPENING_PROJECT"), ex);
            }
        } finally {
            setCursor(oldCursor);
        }
    }

    private void refreshProject() {
        Cursor oldCursor = getCursor();
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            printProvider.reset();
            try {
                String path = MagsaConfig.getInstance().getProjectPath();

                //Nacitanie a build projektu
                project.openProject(path);

                //Resetovanie komponentov (State.UNAVAILABLE) a nastavenie inicialneho stavu
                reset();
                init();

                printProvider.printInfo(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("sk/tuke/magsa/maketool/Bundle").getString("PROJEKT_BOL_USPESNE_OBNOVENY"), new Object[]{path}));
            } catch (Exception ex) {
                printProvider.printError(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("sk/tuke/magsa/maketool/Bundle").getString("CHYBA"), new Object[]{ex.getMessage()}));
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, java.util.ResourceBundle.getBundle("sk/tuke/magsa/maketool/Bundle").getString("ERROR_REFRESHING_PROJECT"), ex);
            }
        } finally {
            setCursor(oldCursor);
        }
    }

    private void highlightInfoAfterTabChange() {
        int newSelectedTabIndex = tasksTab.getSelectedIndex();
        if (newSelectedTabIndex != previuosSelectedTabIndex) {
            AbstractTaskPanel oldPanel = tasks[previuosSelectedTabIndex];
            AbstractTaskPanel newPanel = tasks[newSelectedTabIndex];
            previuosSelectedTabIndex = newSelectedTabIndex;
            AbstractTaskPanel.ModelDiff diff = newPanel.calcModelDiff(oldPanel);

            List<Rectangle> rectangles = new ArrayList<>();
            for (ExecutableResourceComponent erc : diff.getAddedComponents()) {
                Rectangle rect = erc.getBounds();
                rectangles.add(SwingUtilities.convertRectangle(erc.getParent(), rect, glassPane));
            }
            glassPane.showRectagles(rectangles);

            
            printProvider.printInfo("Complete make:\n");
            printProvider.printCode(newPanel.generateMake());
        }
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
        tasksTab = new javax.swing.JTabbedPane();
        scrollPane1 = new javax.swing.JScrollPane();
        panel1 = new javax.swing.JPanel();
        task1 = new sk.tuke.magsa.maketool.task.Task1();
        scrollPane2 = new javax.swing.JScrollPane();
        panel2 = new javax.swing.JPanel();
        task2 = new sk.tuke.magsa.maketool.task.Task2();
        scrollPane3 = new javax.swing.JScrollPane();
        panel3 = new javax.swing.JPanel();
        task3 = new sk.tuke.magsa.maketool.task.Task3();
        scrollPane4 = new javax.swing.JScrollPane();
        panel4 = new javax.swing.JPanel();
        task4 = new sk.tuke.magsa.maketool.task.Task4();
        scrollPane5 = new javax.swing.JScrollPane();
        panel5 = new javax.swing.JPanel();
        task5 = new sk.tuke.magsa.maketool.task.Task5();
        scrollPane6 = new javax.swing.JScrollPane();
        panel6 = new javax.swing.JPanel();
        task6 = new sk.tuke.magsa.maketool.task.Task6();
        scrollPane7 = new javax.swing.JScrollPane();
        panel7 = new javax.swing.JPanel();
        task7 = new sk.tuke.magsa.maketool.task.Task7();
        scrollPane8 = new javax.swing.JScrollPane();
        panel8 = new javax.swing.JPanel();
        task8 = new sk.tuke.magsa.maketool.task.Task8();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        consolePane = new javax.swing.JEditorPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        modelPane = new javax.swing.JEditorPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        nacitatMenu = new javax.swing.JMenuItem();
        restartujMenu = new javax.swing.JMenuItem();
        zavrietMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        materialyMenu = new javax.swing.JMenuItem();
        oProgrameMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("sk/tuke/magsa/maketool/Bundle"); // NOI18N
        setTitle(bundle.getString("MAGSATOOL")); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        topPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel6.setText(bundle.getString("REL_CESTA_K_PRIECINKU_MODELU")); // NOI18N
        topPanel.add(jLabel6);

        modelDirTextField.setColumns(17);
        modelDirTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                modelDirTextFieldKeyReleased(evt);
            }
        });
        topPanel.add(modelDirTextField);

        jLabel5.setText(bundle.getString("TRIEDA_S_DEF_INTERNEHO_DSL")); // NOI18N
        topPanel.add(jLabel5);

        constraintClassTextField.setColumns(17);
        constraintClassTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                constraintClassTextFieldKeyReleased(evt);
            }
        });
        topPanel.add(constraintClassTextField);

        jLabel3.setText(bundle.getString("REL_CESTA_K_MODEL_EL")); // NOI18N
        topPanel.add(jLabel3);

        modelFileTextField.setColumns(17);
        modelFileTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                modelFileTextFieldKeyReleased(evt);
            }
        });
        topPanel.add(modelFileTextField);

        jLabel4.setText(bundle.getString("REL_CESTA_K_UI")); // NOI18N
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

        tasksTab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tasksTabStateChanged(evt);
            }
        });

        panel1.setLayout(new java.awt.GridBagLayout());
        panel1.add(task1, new java.awt.GridBagConstraints());

        scrollPane1.setViewportView(panel1);

        tasksTab.addTab(bundle.getString("TASK1"), scrollPane1); // NOI18N

        panel2.setLayout(new java.awt.GridBagLayout());
        panel2.add(task2, new java.awt.GridBagConstraints());

        scrollPane2.setViewportView(panel2);

        tasksTab.addTab(bundle.getString("TASK2"), scrollPane2); // NOI18N

        panel3.setLayout(new java.awt.GridBagLayout());
        panel3.add(task3, new java.awt.GridBagConstraints());

        scrollPane3.setViewportView(panel3);

        tasksTab.addTab(bundle.getString("TASK3"), scrollPane3); // NOI18N

        panel4.setLayout(new java.awt.GridBagLayout());
        panel4.add(task4, new java.awt.GridBagConstraints());

        scrollPane4.setViewportView(panel4);

        tasksTab.addTab(bundle.getString("TASK4"), scrollPane4); // NOI18N

        panel5.setLayout(new java.awt.GridBagLayout());
        panel5.add(task5, new java.awt.GridBagConstraints());

        scrollPane5.setViewportView(panel5);

        tasksTab.addTab(bundle.getString("TASK5"), scrollPane5); // NOI18N

        panel6.setLayout(new java.awt.GridBagLayout());
        panel6.add(task6, new java.awt.GridBagConstraints());

        scrollPane6.setViewportView(panel6);

        tasksTab.addTab(bundle.getString("TASK6"), scrollPane6); // NOI18N

        panel7.setLayout(new java.awt.GridBagLayout());
        panel7.add(task7, new java.awt.GridBagConstraints());

        scrollPane7.setViewportView(panel7);

        tasksTab.addTab(bundle.getString("TASK7"), scrollPane7); // NOI18N

        panel8.setLayout(new java.awt.GridBagLayout());
        panel8.add(task8, new java.awt.GridBagConstraints());

        scrollPane8.setViewportView(panel8);

        tasksTab.addTab(bundle.getString("TASK8"), scrollPane8); // NOI18N

        jSplitPane2.setLeftComponent(tasksTab);

        jSplitPane1.setDividerLocation(500);

        jLabel1.setText(bundle.getString("KONZOLA")); // NOI18N

        jScrollPane3.setViewportView(consolePane);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel1);

        jLabel2.setText(bundle.getString("REPREZENTACIA_MODELU")); // NOI18N

        jScrollPane1.setViewportView(modelPane);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel2);

        jSplitPane2.setRightComponent(jSplitPane1);

        centerPanel.add(jSplitPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

        jMenu1.setText(bundle.getString("PROJEKT")); // NOI18N

        nacitatMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        nacitatMenu.setText(bundle.getString("NACITAT_PROJEKT")); // NOI18N
        nacitatMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nacitatMenuActionPerformed(evt);
            }
        });
        jMenu1.add(nacitatMenu);

        restartujMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        restartujMenu.setText(bundle.getString("RESTARTOVAT_PROJEKT")); // NOI18N
        restartujMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartujMenuActionPerformed(evt);
            }
        });
        jMenu1.add(restartujMenu);

        zavrietMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        zavrietMenu.setText(bundle.getString("UKONCIT")); // NOI18N
        zavrietMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zavrietMenuActionPerformed(evt);
            }
        });
        jMenu1.add(zavrietMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(bundle.getString("POMOC")); // NOI18N

        materialyMenu.setText(bundle.getString("MATERIALY")); // NOI18N
        materialyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materialyMenuActionPerformed(evt);
            }
        });
        jMenu2.add(materialyMenu);

        oProgrameMenu.setText(bundle.getString("O_PROGRAME")); // NOI18N
        oProgrameMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oProgrameMenuActionPerformed(evt);
            }
        });
        jMenu2.add(oProgrameMenu);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modelFileTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modelFileTextFieldKeyReleased
        MagsaConfig.getInstance().setModelFile(modelFileTextField.getText().trim());
    }//GEN-LAST:event_modelFileTextFieldKeyReleased

    private void uiFileTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uiFileTextFieldKeyReleased
        MagsaConfig.getInstance().setUiFile(uiFileTextField.getText().trim());
    }//GEN-LAST:event_uiFileTextFieldKeyReleased

    private void constraintClassTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_constraintClassTextFieldKeyReleased
        MagsaConfig.getInstance().setConstraintClass(constraintClassTextField.getText().trim());
    }//GEN-LAST:event_constraintClassTextFieldKeyReleased

    private void nacitatMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nacitatMenuActionPerformed
        openProject();
    }//GEN-LAST:event_nacitatMenuActionPerformed

    private void zavrietMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zavrietMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_zavrietMenuActionPerformed

    private void modelDirTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modelDirTextFieldKeyReleased
        MagsaConfig.getInstance().setModelDir(modelDirTextField.getText().trim());
    }//GEN-LAST:event_modelDirTextFieldKeyReleased

    private void restartujMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartujMenuActionPerformed
        refreshProject();
    }//GEN-LAST:event_restartujMenuActionPerformed

    private void tasksTabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tasksTabStateChanged
        modelDirTextField.setEditable(tasksTab.getSelectedIndex() < 4);
        constraintClassTextField.setEditable(tasksTab.getSelectedIndex() > 1 && tasksTab.getSelectedIndex() < 4);
        modelFileTextField.setEditable(tasksTab.getSelectedIndex() > 3);
        uiFileTextField.setEditable(tasksTab.getSelectedIndex() > 5);
    }//GEN-LAST:event_tasksTabStateChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        openProject();
    }//GEN-LAST:event_formWindowOpened

    private void materialyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materialyMenuActionPerformed
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(URI.create("http://it4kt.cnl.tuke.sk/c/magsa/student/info.html"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_materialyMenuActionPerformed

    private void oProgrameMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oProgrameMenuActionPerformed
        new OProgrameDialog(this).setVisible(true);
    }//GEN-LAST:event_oProgrameMenuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel centerPanel;
    private javax.swing.JEditorPane consolePane;
    private javax.swing.JTextField constraintClassTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JMenuItem materialyMenu;
    private javax.swing.JTextField modelDirTextField;
    private javax.swing.JTextField modelFileTextField;
    private javax.swing.JEditorPane modelPane;
    private javax.swing.JMenuItem nacitatMenu;
    private javax.swing.JMenuItem oProgrameMenu;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panel7;
    private javax.swing.JPanel panel8;
    private javax.swing.JMenuItem restartujMenu;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JScrollPane scrollPane3;
    private javax.swing.JScrollPane scrollPane4;
    private javax.swing.JScrollPane scrollPane5;
    private javax.swing.JScrollPane scrollPane6;
    private javax.swing.JScrollPane scrollPane7;
    private javax.swing.JScrollPane scrollPane8;
    private sk.tuke.magsa.maketool.task.Task1 task1;
    private sk.tuke.magsa.maketool.task.Task2 task2;
    private sk.tuke.magsa.maketool.task.Task3 task3;
    private sk.tuke.magsa.maketool.task.Task4 task4;
    private sk.tuke.magsa.maketool.task.Task5 task5;
    private sk.tuke.magsa.maketool.task.Task6 task6;
    private sk.tuke.magsa.maketool.task.Task7 task7;
    private sk.tuke.magsa.maketool.task.Task8 task8;
    private javax.swing.JTabbedPane tasksTab;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField uiFileTextField;
    private javax.swing.JMenuItem zavrietMenu;
    // End of variables declaration//GEN-END:variables
}
