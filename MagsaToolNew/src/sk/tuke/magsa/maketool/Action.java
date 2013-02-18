package sk.tuke.magsa.maketool;

public interface Action {
    void setContext(Context context);
    
    void execute() throws Exception;

    String describe();
}
