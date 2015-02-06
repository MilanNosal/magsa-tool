package sk.tuke.magsa.maketool.core;

import java.io.File;
import java.util.Arrays;
import org.codehaus.plexus.util.DirectoryScanner;

/**
 * MavenFinder tryies to find Maven and JDK installed in the system by checking
 * several common paths.
 * @author Sergej Chodarev
 */
public class MavenFinder {
    private static File mavenHome = null;
    private static File javaHome = null;

    /**
     * Try to find Maven installed in the system
     *
     * @return Path to Maven home or <code>null</code> if <code>M2_HOME</code>
     * was already set or Maven could not be found.
     */
    public static File getMavenHome() {
        if (mavenHome == null) {
            mavenHome = findMaven();
            if (mavenHome != null)
                System.err.println("M2_HOME was not set, but Maven was detected at " + mavenHome);
        }
        return mavenHome;
    }

    /**
     * Try to find JDK installed in the system
     *
     * @return Path to JDK home or <code>null</code> if <code>JAVA_HOME</code>
     * was already set or JDK could not be found.
     */
    public static File getJavaHome() {
        if (javaHome == null) {
            javaHome = findJDK();
            if (javaHome != null)
                System.err.println("JAVA_HOME was not set, but JDK was detected at " + javaHome);
        }
        return javaHome;
    }

    private static File findMaven() {
        if (System.getProperty("maven.home") != null || System.getenv("M2_HOME") != null)
            return null; // MavenInvoker would not be able to find Maven

        // Try other environment variable
        if (System.getenv("MAVEN_HOME") != null)
            return new File(System.getenv("MAVEN_HOME"));

        // Try dafult UNIX path
        File path = new File("/usr/share/maven");
        if (path.exists())
            return path;

        // Try Maven provided with Netbeans
        return findMatchingPath("C:/Program Files", "NetBeans*/java/maven");
    }

    private static File findJDK() {
        if (System.getenv("JAVA_HOME") != null)
            return null; // Maven would not be able to find JDK

        // Try some UNIX paths
        File path = new File("/usr/lib/jvm/java");
        if (path.exists())
            return path;
        path = new File("/usr/lib/jvm/default-java");
        if (path.exists())
            return path;

        // Try dafult Windows path
        return findMatchingPath("C:/Program Files", "Java/jdk*");
    }

    private static File findMatchingPath(String base, String pattern) throws IllegalStateException {
        DirectoryScanner ds = new DirectoryScanner();
        ds.setBasedir(base);
        String[] includes = {pattern};
        ds.setIncludes(includes);
        ds.scan();
        String[] paths = ds.getIncludedDirectories();
        Arrays.sort(paths);
        // Get last one as it is probably the newest version
        if (paths.length >= 1)
            return new File(base, paths[paths.length-1]);
        return null;
    }
}
