package sk.tuke.magsa.maketool.action;

import sk.tuke.magsa.maketool.Context;

public class MagsaContext implements Context {
    private Object model;

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}
