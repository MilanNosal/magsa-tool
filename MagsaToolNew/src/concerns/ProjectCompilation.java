package concerns;

/**
 *
 * @author Milan
 */
public @interface ProjectCompilation {
    public enum Process {
        CLASS_LOADING,
        BUILDING
    }
    
    public Process value();
}
