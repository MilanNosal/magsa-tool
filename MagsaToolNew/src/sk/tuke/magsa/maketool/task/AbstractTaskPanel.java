package sk.tuke.magsa.maketool.task;

import java.awt.Component;
import javax.swing.JPanel;
import sk.tuke.magsa.maketool.PrintProvider;
import sk.tuke.magsa.maketool.Resource;
import sk.tuke.magsa.maketool.State;

public abstract class AbstractTaskPanel extends JPanel {
    public abstract void configure(PrintProvider printProvider);
    
    public abstract void init();

    public void reset() {
        for (Component component : getComponents()) {
            if (component instanceof Resource) {
                ((Resource) component).setState(State.UNAVAILABLE);
            }
        }
    }
}
