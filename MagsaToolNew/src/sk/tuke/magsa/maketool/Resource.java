package sk.tuke.magsa.maketool;

import java.beans.PropertyChangeListener;

public interface Resource {
    State getState();

    void setState(State state);

    void addStateListener(PropertyChangeListener listener);

    public void removeStateListener(PropertyChangeListener listener);
}
