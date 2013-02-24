package sk.tuke.magsa.maketool.action.generator;

import ak.tuke.task.annotation.Task;
import sk.tuke.magsa.maketool.core.MagsaConfig;
import sk.tuke.magsa.maketool.action.MagsaAction;

@Task(module = "12")
public class UITableGenerator extends MagsaAction {
    @Override
    public void execute() throws Exception {
        Class modelClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
        Class uiClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.ui.UI");
        Class collectionGeneratorClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.generator.CollectionTemplateGenerator");
        Object uiModel = modelClass.getMethod("getUi").invoke(context.getModel());

        Object tables[] = (Object[]) uiClass.getMethod("getTables").invoke(uiModel);
        Object collectionTemplateGenerator =
                collectionGeneratorClass.getConstructor(MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model"), String.class, MagsaConfig.getInstance().loadClass("[Lsk.tuke.magsa.tools.metamodel.Named;")).
                newInstance(context.getModel(), "ui_table", tables);
        collectionGeneratorClass.getMethod("generate").invoke(collectionTemplateGenerator);
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("new CollectionTemplateGenerator<Entity>(model, \"ui_table\", model.getUi().getTables()).generate();\n");

        return sb.toString();
    }
}
