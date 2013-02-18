package sk.tuke.magsa.maketool.action;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import sk.tuke.magsa.maketool.MagsaConfig;

public class UIProcessor extends MagsaAction {
    @Override
    public void execute() throws Exception {
        Class uiProcessorClass = MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.parserxml.UIProcessor");

        // Docasne menim user.dir
        File uidefi = new File(MagsaConfig.getInstance().getProjectPath() + "\\" + MagsaConfig.getInstance().getUiFile());
        String tempSP = System.getProperty("user.dir");
        System.setProperty("user.dir", uidefi.getParentFile().getAbsolutePath());

        Object uiProcessor = uiProcessorClass.getConstructor(Reader.class).newInstance(new FileReader(uidefi));

        uiProcessorClass.getMethod("compose", MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model")).invoke(uiProcessor, context.getModel());

        // navrat zmeny
        System.setProperty("user.dir", tempSP);
    }
    
    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("UIProcessor uiProcessor = new UIProcessor(new FileReader(\"").append(MagsaConfig.getInstance().getUiFile()).append("\"))");
        sb.append("uiProcessor.compose(model);");
        
        return sb.toString();
    }
}