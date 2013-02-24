package sk.tuke.magsa.maketool.action.processor;

import ak.tuke.task.annotation.Task;
import java.io.FileReader;
import java.io.Reader;
import sk.tuke.magsa.maketool.core.MagsaConfig;
import sk.tuke.magsa.maketool.action.MagsaAction;

@Task(module = "08")
public class ExternalParser extends MagsaAction {
    @Override
    public void execute() throws Exception {
        Class parserextClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.parserext.Parser");
        Object parserext = parserextClass.getConstructor().newInstance();
        context.setModel(parserextClass.getMethod("parse", Reader.class).
                invoke(parserext, new FileReader(MagsaConfig.getInstance().getProjectPath() + "/" + MagsaConfig.getInstance().getModelFile())));
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("Parser parserext = new Parser();\n");
        sb.append("model = parserext.parse(new FileReader(\"").append(MagsaConfig.getInstance().getModelFile()).append("\"));\n");

        return sb.toString();
    }
}
