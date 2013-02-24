package sk.tuke.magsa.maketool.action.generator;

import ak.tuke.task.annotation.Task;
import sk.tuke.magsa.maketool.core.MagsaConfig;
import sk.tuke.magsa.maketool.action.MagsaAction;

@Task(module = "12")
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
