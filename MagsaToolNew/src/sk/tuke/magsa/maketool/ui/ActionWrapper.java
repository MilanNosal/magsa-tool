package sk.tuke.magsa.maketool.ui;

import sk.tuke.magsa.maketool.Action;
import sk.tuke.magsa.maketool.Context;
import sk.tuke.magsa.maketool.PrintProvider;
import sk.tuke.magsa.maketool.action.MagsaAction;
import sk.tuke.magsa.maketool.action.MagsaContext;

public class ActionWrapper extends MagsaAction {
    private final Action action;

    private final PrintProvider printProvider;

    public ActionWrapper(Action action, MagsaContext context, PrintProvider printProvider) {
        this.action = action;
        this.printProvider = printProvider;
        this.context = context;
    }

    @Override
    public void setContext(Context context) {
        this.context = (MagsaContext)context;
        action.setContext(context);
    }
    
    @Override
    public void execute() throws Exception {
        try {
            printProvider.reset();
            printProvider.printInfo("Vykonaný kód:");
            printProvider.printCode(describe());
            action.setContext(context);
            action.execute();
            if (context.getModel() != null) {
                printProvider.printModel(context.getModel());
            }
        } catch (Exception ex) {
            printProvider.printError("Chyba: " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public String describe() {
        return action.describe();
    }
}
