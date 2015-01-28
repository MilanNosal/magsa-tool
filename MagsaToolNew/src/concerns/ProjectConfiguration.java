package concerns;

/**
 *
 * @author Milan
 */
public @interface ProjectConfiguration {
    public enum ConfigurationValue {
        PATH,
        TYPE
    }
    
    public ConfigurationValue value();
}
