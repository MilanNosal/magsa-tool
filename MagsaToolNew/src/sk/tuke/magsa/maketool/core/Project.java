package sk.tuke.magsa.maketool.core;

import java.io.File;
import org.apache.tools.ant.ProjectHelper;

public class Project {
    public void openProject(String path) throws Exception {
        MagsaConfig.getInstance().setProjectPath(path);

        buildProject();

        MagsaConfig.getInstance().refreshClassLoader();

        // maly test pre istotu
        MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
    }

    private void buildProject() {
        File buildFile = new File(
                MagsaConfig.getInstance().getProjectPath() + "/nbproject/build-impl.xml");
        org.apache.tools.ant.Project project = new org.apache.tools.ant.Project();
        project.setUserProperty("ant.file", buildFile.getAbsolutePath());
        project.init();

        ProjectHelper helper = ProjectHelper.getProjectHelper();
        helper.parse(project, buildFile);

        project.executeTarget("clean");
        project.executeTarget("compile");
    }
}
