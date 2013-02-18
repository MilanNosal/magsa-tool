package sk.tuke.magsa.maketool.action;

import sk.tuke.magsa.maketool.MagsaConfig;

public class ConstraintBuilder extends MagsaAction {
    @Override
    public void execute() throws Exception {
        Class constraintBuilderClass = MagsaConfig.getInstance().loadClass(MagsaConfig.getInstance().getConstraintClass());
        Object constraintBuilder = constraintBuilderClass.getConstructor().newInstance();
        Class modelClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
        constraintBuilderClass.getMethod("compose", modelClass).invoke(constraintBuilder, context.getModel());        
    }
    
    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("ConstraintBuilder builder = new ").append(MagsaConfig.getInstance().getConstraintClass()).append("();\n");
        sb.append("builder.compose(model);");
        
        return sb.toString();
    }
}
