package sk.tuke.magsa.maketool.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import sk.tuke.magsa.maketool.Action;
import sk.tuke.magsa.maketool.ExecutableResource;
import sk.tuke.magsa.maketool.Resource;
import sk.tuke.magsa.maketool.State;

public class ExecutableResourceComponent extends ResourceComponent implements ExecutableResource, PropertyChangeListener, ActionListener {
    private Action action;

    private List<Resource> inputs = new ArrayList<Resource>();

    private Resource output;

    private JButton button;

    public ExecutableResourceComponent() {
        createButton();
    }

    @Override
    protected void createBorder() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void createButton() {
        button = new JButton("Action");
        button.addActionListener(this);

        GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 2);
        add(button, gridBagConstraints);
        button.setEnabled(false);
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void addInputs(Resource... resources) {
        for (Resource resource : resources) {
            inputs.add(resource);
            resource.addStateListener(this);
        }

        if (isRunable()) {
            setState(State.READY);
        } else {
            setState(State.UNAVAILABLE);
        }
    }

    public List<Resource> getInputs() {
        return inputs;
    }

    public Resource getOutput() {
        return output;
    }

    public void setOutput(Resource output) {
        this.output = output;
    }

    public String getActionName() {
        return button.getText();
    }

    public void setActionName(String text) {
        button.setText(text);
    }

    private boolean isRunable() {
        for (Resource input : inputs) {
            if (input.getState() != State.COMPLETED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        if (action != null && getState() != State.UNAVAILABLE) {
            try {
                action.execute();
                setState(State.COMPLETED);
                if (output != null) {
                    if (output instanceof ExecutableResource) {
                        output.setState(State.READY);
                    } else {
                        output.setState(State.COMPLETED);
                    }
                }
            } catch (Exception ex) {
                setState(State.ERROR);
                Logger.getLogger(ExecutableResourceComponent.class.getName()).log(Level.SEVERE, "Error executing action", ex);
            }
        }
    }

    @Override
    public void setState(State state) {
        super.setState(state);
        if (state != State.UNAVAILABLE) {
            button.setEnabled(true);
        } else {
            button.setEnabled(false);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (isRunable()) {
            setState(State.READY);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        run();
    }
}
