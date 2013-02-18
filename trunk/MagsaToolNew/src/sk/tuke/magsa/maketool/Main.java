package sk.tuke.magsa.maketool;

import javax.swing.UIManager;
import sk.tuke.magsa.maketool.ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        // set System L&F
        try {            
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            // handle exception
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });

    }
}
