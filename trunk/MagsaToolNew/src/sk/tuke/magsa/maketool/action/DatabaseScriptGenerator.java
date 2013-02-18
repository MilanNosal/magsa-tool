package sk.tuke.magsa.maketool.action;

import sk.tuke.magsa.maketool.MagsaConfig;

public class DatabaseScriptGenerator extends MagsaAction {
    @Override
    public void execute() throws Exception {
        Class dbGeneratorClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.generator.DatabaseScriptGenerator");
        Object dbGenerator = dbGeneratorClass.getConstructor(MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model")).newInstance(context.getModel());
        dbGeneratorClass.getMethod("generate").invoke(dbGenerator);
    }
    
    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("new DatabaseScriptGenerator(model).generate();");
        
        return sb.toString();
    }
}
