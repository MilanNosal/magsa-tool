package sk.tuke.magsa.maketool.action.processor;

import ak.tuke.task.annotation.Task;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import sk.tuke.magsa.maketool.core.MagsaConfig;
import sk.tuke.magsa.maketool.action.MagsaAction;

@Task(module = "11")
public class UIProcessor extends MagsaAction {
    @Override
    public void execute() throws Exception {
        Class uiProcessorClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.parserxml.UIProcessor");

        // Docasne menim user.dir
        File uidefi = new File(MagsaConfig.getInstance().getProjectPath() + "/" + MagsaConfig.getInstance().getUiFile());
        String tempSP = System.getProperty("user.dir");
        System.setProperty("user.dir", uidefi.getParentFile().getAbsolutePath());

        Object uiProcessor = uiProcessorClass.getConstructor(Reader.class, File.class)
                .newInstance(new FileReader(uidefi), 
                        new File(MagsaConfig.getInstance().getProjectPath() + "/" + MagsaConfig.getInstance().getUiXSDFile()));

        uiProcessorClass.getMethod("compose", MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model")).invoke(uiProcessor, context.getModel());

        // navrat zmeny
        System.setProperty("user.dir", tempSP);
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("UIProcessor uiProcessor = new UIProcessor(new FileReader(\"").append(MagsaConfig.getInstance().getUiFile()).append("\"))\n");
        sb.append("uiProcessor.compose(model);\n");

        return sb.toString();
    }
}
