package jpize.context;

public interface IContextManager {

    void run();

    void closeAll();

    void closeAllThatNotCurrent();

}
