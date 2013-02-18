package sk.tuke.magsa.maketool.action;

import sk.tuke.magsa.maketool.MagsaConfig;

public class DaoImplementationGenerator extends MagsaAction {
    @Override
    public void execute() throws Exception {
        Class modelClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
        Class collectionGeneratorClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.generator.CollectionTemplateGenerator");
        Object entities[] = (Object[]) modelClass.getMethod("getEntities").invoke(context.getModel());
        Object collectionTemplateGenerator =
                collectionGeneratorClass.getConstructor(MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model"), String.class, MagsaConfig.getInstance().loadClass("[Lsk.tuke.magsa.tools.metamodel.Named;")).
                newInstance(context.getModel(), "dao_impl", entities);
        collectionGeneratorClass.getMethod("generate").invoke(collectionTemplateGenerator);
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("new CollectionTemplateGenerator<Entity>(model, \"dao_impl\", model.getEntities()).generate();");

        return sb.toString();
    }
}