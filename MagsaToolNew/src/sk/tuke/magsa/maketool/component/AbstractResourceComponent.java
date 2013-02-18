package sk.tuke.magsa.maketool.component;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import sk.tuke.magsa.maketool.Resource;
import sk.tuke.magsa.maketool.State;

public class AbstractResourceComponent extends JPanel implements Resource {
    private State state = State.UNAVAILABLE;

    private PropertyChangeSupport statePropertSupport = new PropertyChangeSupport(this);

    public AbstractResourceComponent() {
        setOpaque(true);

        updateStyle();
        updateBorder();
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        State oldState = this.state;
        this.state = state;
        statePropertSupport.firePropertyChange("state", oldState, state);
        
        updateStyle();
        updateBorder();
    }

    private void updateStyle() {
        setBackground(state.getColor());
    }

    private void updateBorder() {
        if (state == State.UNAVAILABLE) {
            setBorder(new DashedBorder(Color.BLACK, 1));
        } else {
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }

    @Override
    public void addStateListener(PropertyChangeListener listener) {
        statePropertSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removeStateListener(PropertyChangeListener listener) {
        statePropertSupport.removePropertyChangeListener(listener);
    }
}
