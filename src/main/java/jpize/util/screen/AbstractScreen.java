package jpize.util.screen;

import generaloss.resourceflow.Disposable;

public class AbstractScreen<T> implements Disposable {

    private ScreenManager<T> manager;
    private T ID;
    private volatile boolean initialized;

    void setManager(ScreenManager<T> manager) {
        this.manager = manager;
    }

    public ScreenManager<T> getManager() {
        return manager;
    }


    void setID(T ID) {
        this.ID = ID;
    }

    public T getID() {
        return ID;
    }


    synchronized void tryToInit() {
        if(initialized) return;
        initialized = true;
        this.init();
    }


    public boolean isCurrent() {
        if(manager == null)
            return false;
        return manager.isCurrent(this);
    }

    public boolean setCurrent() {
        if(manager == null)
            throw new IllegalStateException("Screen is not registered");
        return manager.setCurrent(this);
    }


    public void init() { }

    public void show() { }

    public void hide() { }

    public void update() { }

    public void render() { }

    public void resize(int width, int height) { }

    @Override
    public void dispose() { }

}
