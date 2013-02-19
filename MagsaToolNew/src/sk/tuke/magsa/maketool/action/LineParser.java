package sk.tuke.magsa.maketool.action;

import java.io.File;
import sk.tuke.magsa.maketool.MagsaConfig;

public class LineParser extends MagsaAction {
    @Override
    public void execute() throws Exception {
        Class lineParserClass = Thread.currentThread().getContextClassLoader().loadClass("sk.tuke.magsa.tools.parser.LineParser");
        Object lineParser = lineParserClass.getConstructor().newInstance();
        context.setModel(lineParserClass.getMethod("parseDir", File.class).
                invoke(lineParser, new File(MagsaConfig.getInstance().getProjectPath() + "/" + MagsaConfig.getInstance().getModelDir() + "/")));
    }
    
    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("File dir = new File(\"").append(MagsaConfig.getInstance().getModelDir()).append("\");\n");
        sb.append("LineParser parser = new LineParser();\n");
        sb.append("model = parser.parseDir(dir);\n");
        
        return sb.toString();
    }
}
