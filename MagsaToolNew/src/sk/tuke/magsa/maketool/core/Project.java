package sk.tuke.magsa.maketool.core;

import concerns.ProjectCompilation;
import concerns.ProjectCompilation.Process;
import concerns.ProjectConfiguration;
import concerns.ProjectConfiguration.ConfigurationValue;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class Project {

    @ProjectConfiguration(ConfigurationValue.TYPE)
    public boolean isMagsaProject(File dir) throws IOException {
        return (new File(dir, "pom.xml")).exists();
    }

    @ProjectConfiguration(ConfigurationValue.TYPE)
    public void openProject(String path) throws Exception {
        MagsaConfig.getInstance().setProjectPath(path, this);

        buildMavenProject();

        MagsaConfig.getInstance().refreshClassLoader();

        // maly test pre istotu
        MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
    }

    @ProjectConfiguration(ConfigurationValue.TYPE)
    @ProjectCompilation(Process.BUILDING)
    private void buildMavenProject() {
        try {
            InvocationRequest request = new DefaultInvocationRequest();
            File pom = new File(MagsaConfig.getInstance().getProjectPath() + "/magsa-generator/pom.xml");
            request.setPomFile(pom);
            request.setGoals(Collections.singletonList("compile"));

            Invoker invoker = new DefaultInvoker();
            invoker.setMavenHome(new File(MagsaConfig.getInstance().getProjectPath() + "/magsa-tool-libs/apache-maven-3.2.5"));
            invoker.execute(request);
        } catch (MavenInvocationException ex) {
            System.err.println("A problem with Maven build:");
            ex.printStackTrace(System.err);
        }
    }
}
