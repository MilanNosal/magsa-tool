package sk.tuke.magsa.maketool.component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JPanel;
import sk.tuke.magsa.maketool.Resource;
import sk.tuke.magsa.maketool.State;

public class AbstractResourceComponent extends JPanel implements Resource {
    private State state = State.UNAVAILABLE;

    private PropertyChangeSupport statePropertSupport = new PropertyChangeSupport(this);

    public AbstractResourceComponent() {
        setOpaque(true);

        updateStyle();
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
    }

    protected void updateStyle() {
        setBackground(state.getColor());
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
