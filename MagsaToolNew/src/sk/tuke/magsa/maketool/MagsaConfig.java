package sk.tuke.magsa.maketool;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MagsaConfig {
    private static final MagsaConfig instance = new MagsaConfig();
    
    private final ClassLoader currentThreadClassLoader = Thread.currentThread().getContextClassLoader();

    private String projectPath = "";

    private String modelDir = "model";

    private String constraintClass = "PersonalistikaObmedzenia";

    private String modelFile = "model.el";

    private String uiFile = "ui.xml";

    private MagsaConfig() {
    }

    public static MagsaConfig getInstance() {
        return instance;
    }

    public Class loadClass(String name) throws Exception {
        //return Thread.currentThread().getContextClassLoader().loadClass(name);
        return Class.forName(name, true, Thread.currentThread().getContextClassLoader());
    }

    public String getProjectPath() {
        return projectPath;
    }
    
    public void refreshClassLoader() {
        //TODO: odpamatat a nastavit class loader lebo pri reload ho budem musiet vytvorit nanovo
        try {
            System.out.println(">>>> " + projectPath);
            URL url = new URL("file://" + projectPath + "/build/classes/");
            URL[] urls = new URL[]{url};
            
            ClassLoader loader = new URLClassLoader(urls, currentThreadClassLoader);
            Thread.currentThread().setContextClassLoader(loader);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setProjectPath(String projectPath) {
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

    public void setModelDir(String modelDir) {
        this.modelDir = modelDir;
    }

    public String getModelFile() {
        return modelFile;
    }

    public void setModelFile(String modelFile) {
        this.modelFile = modelFile;
    }

    public String getUiFile() {
        return uiFile;
    }

    public void setUiFile(String uiFile) {
        this.uiFile = uiFile;
    }
}
