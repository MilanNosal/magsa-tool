package sk.tuke.magsa.maketool.action;

import sk.tuke.magsa.maketool.Action;
import sk.tuke.magsa.maketool.Context;

public abstract class MagsaAction implements Action {
    protected MagsaContext context;

    public void setContext(Context context) {
        this.context = (MagsaContext)context;
    }
}
