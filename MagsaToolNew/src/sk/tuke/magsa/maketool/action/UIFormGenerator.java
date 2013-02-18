package sk.tuke.magsa.maketool.action;

import sk.tuke.magsa.maketool.MagsaConfig;

public class UIFormGenerator extends MagsaAction {
    @Override
    public void execute() throws Exception {
        Class modelClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
        Class uiClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.ui.UI");
        Class collectionGeneratorClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.generator.CollectionTemplateGenerator");        
        Object uiModel = modelClass.getMethod("getUi").invoke(context.getModel());

        Object forms[] = (Object[]) uiClass.getMethod("getForms").invoke(uiModel);
        Object collectionTemplateGenerator =
                collectionGeneratorClass.getConstructor(MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model"), String.class, MagsaConfig.getInstance().loadClass("[Lsk.tuke.magsa.tools.metamodel.Named;")).
                newInstance(context.getModel(), "ui_form", forms);
        collectionGeneratorClass.getMethod("generate").invoke(collectionTemplateGenerator);    
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("new CollectionTemplateGenerator<Entity>(model, \"ui_form\", model.getUi().getForms()).generate();");

        return sb.toString();
    }
}
