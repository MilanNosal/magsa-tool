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
import org.codehaus.plexus.util.DirectoryScanner;

public class Project {
    private final Invoker mavenInvoker;

    public Project() {
        mavenInvoker = new DefaultInvoker();
        File mavenHome = findMaven();
        if (mavenHome != null) {
            System.err.println("M2_HOME was not set, but Maven was detected at " + mavenHome);
            mavenInvoker.setMavenHome(mavenHome);
        }
    }

    private File findMaven() {
        if (System.getProperty("maven.home") != null || System.getenv("M2_HOME") != null)
            return null; // MavenInvoker would not be able to find Maven
        // Try other environment variable
        if (System.getenv("MAVEN_HOME") != null)
            return new File(System.getenv("MAVEN_HOME"));
        // Try dafult UNIX path
        File mavenHome = new File("/usr/share/maven");
        if (mavenHome.exists())
            return mavenHome;
        // Try Maven provided with Netbeans
        DirectoryScanner ds = new DirectoryScanner();
        ds.setBasedir("C:/Program Files");
        String[] includes = {"NetBeans*/java/maven"};
        ds.setIncludes(includes);
        ds.scan();
        String[] paths = ds.getIncludedDirectories();
        if (paths.length >= 1)
            return new File(paths[0]);
        
        return null;
    }

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
            InvocationRequest request = new DefaultInvocationRequest();
            File pom = new File(MagsaConfig.getInstance().getProjectPath() + "/magsa-generator/pom.xml");
            request.setPomFile(pom);
            request.setGoals(Collections.singletonList("compile"));
            mavenInvoker.execute(request);
        } catch (MavenInvocationException ex) {
            System.err.println("A problem with Maven build:");
            ex.printStackTrace(System.err);
        }
    }

    public URL[] getClassPath() {
        final List<URL> urls = new ArrayList<>();
        try {
            urls.add(new URL("file:///" + MagsaConfig.getInstance().getProjectPath() + "/magsa-generator/target/classes/"));
            InvocationRequest request = new DefaultInvocationRequest();
            File pom = new File(MagsaConfig.getInstance().getProjectPath() + "/magsa-generator/pom.xml");
            request.setPomFile(pom);
            request.setGoals(Collections.singletonList("dependency:build-classpath"));
            request.setOutputHandler(new InvocationOutputHandler() {
                @Override
                public void consumeLine(String line) {
                    if (!line.startsWith("[")) {
                        for (String path : line.split(":")) {
                            try {
                                urls.add(new URL("file://" + path));
                            } catch (MalformedURLException ex) {
                                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
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
}
