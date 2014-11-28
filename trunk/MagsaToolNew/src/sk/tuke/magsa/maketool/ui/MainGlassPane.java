package sk.tuke.magsa.maketool.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

class MainGlassPane extends JPanel {
    private final Timer timer = new Timer(3000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            hideRectagles();
        }
    });
    
    private List<Rectangle> rectangles;

    public MainGlassPane() {
        setOpaque(false);
    }

    public void showRectagles(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
        setVisible(true);
        timer.start();
    }

    public void hideRectagles() {
        this.rectangles = null;
        setVisible(false);
        timer.stop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(3));
        if (rectangles != null) {
            for (Rectangle rectangle : rectangles) {
                g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
    }
    
}
