package sk.tuke.magsa.maketool.core;

import concerns.ProjectCompilation;
import concerns.ProjectCompilation.Process;
import concerns.ProjectConfiguration;
import concerns.ProjectConfiguration.ConfigurationValue;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationOutputHandler;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class Project {
    private final Invoker mavenInvoker = new DefaultInvoker();

    @ProjectConfiguration(ConfigurationValue.TYPE)
    public boolean isMagsaProject(File dir) throws IOException {
        return (new File(dir, "pom.xml")).exists();
    }

    @ProjectConfiguration(ConfigurationValue.TYPE)
    public void openProject(String path) throws Exception {
        MagsaConfig.getInstance().setProjectPath(path, this);

        buildMavenProject();

        //MagsaConfig.getInstance().refreshClassLoader();

        // maly test pre istotu
        MagsaConfig.getInstance().loadClass("sk.tuke.magsa.tools.metamodel.Model");
    }

    @ProjectConfiguration(ConfigurationValue.TYPE)
    @ProjectCompilation(Process.BUILDING)
    private void buildMavenProject() {
        try {
            InvocationRequest request = createMavenRequest("compile");
            mavenInvoker.execute(request);
        } catch (MavenInvocationException ex) {
            System.err.println("A problem with Maven build:");
            ex.printStackTrace(System.err);
        }
    }

    public URL[] getClassPath() {
        final List<URL> urls = new ArrayList<>();
        try {
            urls.add(new File(MagsaConfig.getInstance().getProjectPath() +
                    "/magsa-generator/target/classes/").toURI().toURL());
            InvocationRequest request = createMavenRequest("dependency:build-classpath");
            request.setOutputHandler(new InvocationOutputHandler() {
                @Override
                public void consumeLine(String line) {
                    if (!line.startsWith("[")) {
                        List<URL> dependencies = parseClassPath(line);
                        urls.addAll(dependencies);
                    }
                }
            });
            mavenInvoker.execute(request);
        } catch (MavenInvocationException ex) {
            System.err.println("A problem with Maven build:");
            ex.printStackTrace(System.err);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        return urls.toArray(new URL[urls.size()]);
    }

    private InvocationRequest createMavenRequest(String goal) {
        InvocationRequest request = new DefaultInvocationRequest();
        File pom = new File(MagsaConfig.getInstance().getProjectPath() + "/magsa-generator/pom.xml");
        request.setPomFile(pom);
        request.setGoals(Collections.singletonList(goal));
        return request;
    }

    private List<URL> parseClassPath(String pathLine) {
        List<URL> urls = new ArrayList<>();
        for (String path : pathLine.split(File.pathSeparator)) {
            try {
                urls.add(new File(path).toURI().toURL());
            } catch (MalformedURLException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.WARNING, null, ex);
            }
        }
        return urls;
    }
}
