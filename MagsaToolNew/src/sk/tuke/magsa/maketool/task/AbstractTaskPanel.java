package sk.tuke.magsa.maketool.task;

import java.awt.Component;
import java.util.Collection;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JPanel;
import sk.tuke.magsa.maketool.PrintProvider;
import sk.tuke.magsa.maketool.Resource;
import sk.tuke.magsa.maketool.State;
import sk.tuke.magsa.maketool.component.ExecutableResourceComponent;

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

    public ModelDiff calcModelDiff(AbstractTaskPanel oldPanel) {
        HashMap<String, ExecutableResourceComponent> oldComponents = new HashMap<>();

        //Store components from old panel
        for (Component component : oldPanel.getComponents()) {
            if (component instanceof ExecutableResourceComponent) {
                ExecutableResourceComponent erc = (ExecutableResourceComponent) component;

                oldComponents.put(erc.getLabel(), erc);
            }
        }
        System.out.flush();

        //Compare diff between old and new panel
        Set<ExecutableResourceComponent> addedComponents = new HashSet<>();

        for (Component component : getComponents()) {
            if (component instanceof ExecutableResourceComponent) {
                ExecutableResourceComponent erc = (ExecutableResourceComponent) component;

                if (!oldComponents.containsKey(erc.getLabel())) {
                    addedComponents.add(erc);
                } else {
                    oldComponents.remove(erc.getLabel());
                }
            }
        }

        return new ModelDiff(addedComponents, oldComponents.values());
    }

    //generate make file
    public String generateMake() {
        StringBuilder sb = new StringBuilder("Model model;\n");
        for (Component component : getComponents()) {
            if (component instanceof ExecutableResourceComponent) {
                ExecutableResourceComponent erc = (ExecutableResourceComponent) component;
                sb.append(erc.getAction().describe());
            }
        }

        return sb.toString();
    }

    public static class ModelDiff {

        private final Collection<ExecutableResourceComponent> addedComponents;

        private final Collection<ExecutableResourceComponent> removedComponents;

        public ModelDiff(Collection<ExecutableResourceComponent> addedComponents, Collection<ExecutableResourceComponent> removedComponents) {
            this.addedComponents = addedComponents;
            this.removedComponents = removedComponents;
        }

        public Collection<ExecutableResourceComponent> getAddedComponents() {
            return addedComponents;
        }

        public Collection<ExecutableResourceComponent> getRemovedComponents() {
            return removedComponents;
        }
    }
}
