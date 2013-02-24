package sk.tuke.magsa.maketool.action.generator;

import ak.tuke.task.annotation.Task;
import sk.tuke.magsa.maketool.core.MagsaConfig;
import sk.tuke.magsa.maketool.action.MagsaAction;

@Task(module = "03")
public class DaoInterfaceGenerator extends MagsaAction {
    @Override
    public void execute() throws Exception {
        Class modelClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
        Class collectionGeneratorClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.generator.CollectionTemplateGenerator");
        Object entities[] = (Object[]) modelClass.getMethod("getEntities").invoke(context.getModel());
        Object collectionTemplateGenerator =
                collectionGeneratorClass.getConstructor(MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model"), String.class, MagsaConfig.getInstance().loadClass("[Lsk.tuke.magsa.tools.metamodel.Named;")).
                newInstance(context.getModel(), "dao_interface", entities);
        collectionGeneratorClass.getMethod("generate").invoke(collectionTemplateGenerator);
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("new CollectionTemplateGenerator<Entity>(model, \"dao_interface\", model.getEntities()).generate();\n");

        return sb.toString();
    }
}
