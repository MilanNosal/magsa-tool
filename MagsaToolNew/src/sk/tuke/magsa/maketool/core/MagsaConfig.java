package sk.tuke.magsa.maketool.core;

import concerns.ProjectCompilation;
import static concerns.ProjectCompilation.Process;
import concerns.ProjectConfiguration;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MagsaConfig {

    private static final MagsaConfig instance = new MagsaConfig();

    private final ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();

    private String projectPath = "";

    private String modelDir = "model/entities";

    private String constraintClass = "PersonalistikaObmedzenia";

    private String modelFile = "model/model.el";

    private String uiFile = "model/ui.xml";

    private Project project;

    private MagsaConfig() {
    }

    public static MagsaConfig getInstance() {
        return instance;
    }

    @ProjectCompilation(Process.CLASS_LOADING)
    public Class loadClass(String name) throws Exception {
        //return Thread.currentThread().getContextClassLoader().loadClass(name);
        return Class.forName(name, true, Thread.currentThread().getContextClassLoader());
    }

    public String getProjectPath() {
        return projectPath;
    }

    @ProjectCompilation(Process.CLASS_LOADING)
    @ProjectConfiguration(ProjectConfiguration.ConfigurationValue.PATH)
    public void refreshClassLoader() {
        try {
            URL[] urls = new URL[]{new URL("file:///" + projectPath + "/magsa-generator/target/classes/")};
            ClassLoader loader = new URLClassLoader(urls, originalClassLoader);
            Thread.currentThread().setContextClassLoader(loader);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MagsaConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ProjectConfiguration(ProjectConfiguration.ConfigurationValue.PATH)
    public void setProjectPath(String projectPath, Project project) {
        this.project = project;
        this.projectPath = projectPath;
        refreshClassLoader();
    }

    public String getConstraintClass() {
        return constraintClass;
    }

    public void setConstraintClass(String constraintClass) {
        this.constraintClass = constraintClass;
    }

    public String getModelDir() {
        return modelDir;
    }

    @ProjectConfiguration(ProjectConfiguration.ConfigurationValue.PATH)
    public void setModelDir(String modelDir) {
        this.modelDir = modelDir;
    }

    public String getModelFile() {
        return modelFile;
    }

    @ProjectConfiguration(ProjectConfiguration.ConfigurationValue.PATH)
    public void setModelFile(String modelFile) {
        this.modelFile = modelFile;
    }

    public String getUiFile() {
        return uiFile;
    }
    
    public String getUiXSDFile() {
        return uiFile.substring(0, uiFile.length() - 3) + "xsd";
    }


    @ProjectConfiguration(ProjectConfiguration.ConfigurationValue.PATH)
    public void setUiFile(String uiFile) {
        this.uiFile = uiFile;
    }
}
