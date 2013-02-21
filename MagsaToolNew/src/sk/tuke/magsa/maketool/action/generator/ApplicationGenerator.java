package sk.tuke.magsa.maketool.action.generator;

import sk.tuke.magsa.maketool.core.MagsaConfig;
import sk.tuke.magsa.maketool.action.MagsaAction;

public class ApplicationGenerator extends MagsaAction {
    @Override
    public void execute() throws Exception {
        Class applicationGeneratorClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.generator.ApplicationGenerator");
        Object applicationGenerator = applicationGeneratorClass.getConstructor(MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model")).newInstance(context.getModel());
        applicationGeneratorClass.getMethod("generate").invoke(applicationGenerator);
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("new ApplicationGenerator(model).generate();\n");

        return sb.toString();
    }
}
