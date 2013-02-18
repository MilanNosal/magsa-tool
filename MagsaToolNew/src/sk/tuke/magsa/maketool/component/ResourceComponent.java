package sk.tuke.magsa.maketool.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import sk.tuke.magsa.maketool.Resource;

public class ResourceComponent extends AbstractResourceComponent implements Resource {
    private JLabel label;

    public ResourceComponent() {
        this.setLayout(new java.awt.GridBagLayout());

        createBorder();
        createLabel();
    }

    protected void createBorder() {
        setBorder(new DashedBorder(Color.BLACK, 1));
    }

    private void createLabel() {
        label = new JLabel("Resource");

        GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(label, gridBagConstraints);
    }

    public void setLabel(String text) {
        label.setText("<html>" + text + "</html>");
    }

    public String getLabel() {
        return label.getText();
    }
}
