package jpize.app;

public interface IContextManager {

    void run();

    void closeAll();

    void closeAllThatNotCurrent();

}
