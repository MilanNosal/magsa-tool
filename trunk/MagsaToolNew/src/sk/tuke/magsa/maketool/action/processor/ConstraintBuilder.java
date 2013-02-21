package sk.tuke.magsa.maketool.action.processor;

import sk.tuke.magsa.maketool.core.MagsaConfig;
import sk.tuke.magsa.maketool.action.MagsaAction;

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
        sb.append("builder.compose(model);\n");
        
        return sb.toString();
    }
}
