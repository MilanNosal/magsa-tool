package sk.tuke.magsa.maketool;

public interface PrintProvider {
    void reset();
    
    void printModel(Object model);
    
    void printCode(String code);
    
    void printInfo(String text);
    
    void printError(String error);
}
