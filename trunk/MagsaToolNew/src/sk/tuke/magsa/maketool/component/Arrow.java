package sk.tuke.magsa.maketool.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import javax.swing.JLabel;

/**
 * Komponent sipky do diagramu. 
 * 
 * @author jaro
 */
public class Arrow extends JLabel {
    /**
     * Smer sipky.
     */
    public enum Orientation {
        EAST, NORTHEAST, SOUTHEAST, SOUTH

    }

    /**
     * Styl konca sipky (plna, vecko.
     */
    public enum BarbStyle {
        FILLED, VSTYLE

    }
    private int barbSize = 12;

    private Orientation orientation = Orientation.EAST;

    private BarbStyle barbStyle = BarbStyle.VSTYLE;

    public Arrow() {
        setOpaque(false);
        setText("");
    }

    public int getBarbSize() {
        return barbSize;
    }

    public void setBarbSize(int barb) {
        this.barbSize = barb;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public BarbStyle getBarbStyle() {
        return barbStyle;
    }

    public void setBarbStyle(BarbStyle barbStyle) {
        this.barbStyle = barbStyle;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int length = 0;
        double theta = 0;
        
        switch (orientation) {
            case EAST:
                length = getWidth();
                theta = 0;
                break;
            case NORTHEAST:
                length = (int) Math.round(Math.sqrt(getWidth() * getWidth() + getHeight() * getHeight()));
                theta = -Math.atan((double) getHeight() / (double) getWidth());
                break;
            case SOUTHEAST:
                length = (int) Math.round(Math.sqrt(getWidth() * getWidth() + getHeight() * getHeight()));
                theta = Math.atan((double) getHeight() / (double) getWidth());
                break;
            case SOUTH:
                length = getHeight();
                theta = Math.toRadians(90);
                break;
        }
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        AffineTransform at = AffineTransform.getTranslateInstance(cx, cy);
        at.rotate(theta);
        at.scale(0.9, 0.9);
        Shape line = at.createTransformedShape(createLine(length));
        g2.setPaint(Color.blue);
        g2.draw(line);
        if (barbStyle.equals(BarbStyle.FILLED)) {
            Shape shape = at.createTransformedShape(createFilledPointer(length, this.barbSize));
            g2.fill(shape);
        } else {
            Shape shape = at.createTransformedShape(createEmptyPointer(length, this.barbSize));
            g2.draw(shape);
        }
    }

    private Path2D.Double createLine(int length) {
        Path2D.Double path = new Path2D.Double();
        path.moveTo(-length / 2, 0);
        path.lineTo(length / 2, 0);
        return path;
    }

    private Path2D.Double createEmptyPointer(int length, int barb) {
        double angle = Math.toRadians(20);
        Path2D.Double path = new Path2D.Double();

        double x = length / 2 - barb * Math.cos(angle);
        double y = barb * Math.sin(angle);
        path.moveTo(length / 2, 0);
        path.lineTo(x, y);
        x = length / 2 - barb * Math.cos(-angle);
        y = barb * Math.sin(-angle);
        path.moveTo(length / 2, 0);
        path.lineTo(x, y);
        return path;
    }

    private Path2D.Double createFilledPointer(int length, int barb) {
        double angle = Math.toRadians(20);
        Path2D.Double path = new Path2D.Double();

        double x = length / 2 - barb * Math.cos(angle);
        double y = barb * Math.sin(angle);
        path.moveTo(length / 2, 0);
        path.lineTo(x, y);
        x = length / 2 - barb * Math.cos(-angle);
        y = barb * Math.sin(-angle);
        path.lineTo(x, y);
        path.lineTo(length / 2, 0);
        return path;
    }
}