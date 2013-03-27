package sk.tuke.magsa.maketool.ui;

import ak.tuke.task.annotation.Task;
import sk.tuke.magsa.maketool.Action;
import sk.tuke.magsa.maketool.Context;
import sk.tuke.magsa.maketool.PrintProvider;
import sk.tuke.magsa.maketool.action.MagsaAction;
import sk.tuke.magsa.maketool.action.MagsaContext;

public class ActionWrapper extends MagsaAction {
    private static final String WEB_CVICENIA = "http://hornad.fei.tuke.sk/~poruban/magsa/%s.html#%s";
    private static final String LINK_WEB_CVICENIA = "<a href='%s'>%s</a>";

    private final Action action;

    private final PrintProvider printProvider;

    public ActionWrapper(Action action, MagsaContext context, PrintProvider printProvider) {
        this.action = action;
        this.printProvider = printProvider;
        this.context = context;
    }

    @Override
    public void setContext(Context context) {
        this.context = (MagsaContext) context;
        action.setContext(context);
    }

    @Override
    public void execute() throws Exception {
        try {
            printProvider.reset();
            printProvider.printInfo("Vykonaný kód:\n");
            printProvider.printCode(describe());
            action.setContext(context);
            action.execute();
            if (context.getModel() != null) {
                printProvider.printModel(context.getModel());
            }
        } catch (Exception ex) {
            //TODO: lepsie spracovanie chyby
            printProvider.printError("Chyba: " + ex.getMessage() + ".\n");
            printTaskWebLink();
            throw ex;
        }
    }

    private void printTaskWebLink() {
        Task taskAnnotation = action.getClass().getAnnotation(Task.class);
        if (taskAnnotation != null) {
            String id = taskAnnotation.id();
            if ("".equals(id)) {
                id = action.getClass().getSimpleName();
            }
            String web = String.format(WEB_CVICENIA, taskAnnotation.module(), id);
            String webLink = String.format(LINK_WEB_CVICENIA, web, web);
            printProvider.printInfo(String.format("Pozrite si opis úlohy na %s\n", webLink));
        }
    }

    @Override
    public String describe() {
        return action.describe();
    }
}
