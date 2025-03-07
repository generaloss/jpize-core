package jpize.util.screen;

import jpize.context.Jpize;
import jpize.util.Disposable;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class ScreenManager<T> implements Disposable {

    private final Map<T, AbstractScreen<T>> map;
    private AbstractScreen<T> current;

    public ScreenManager() {
        this.map = new ConcurrentHashMap<>();
    }


    public Collection<AbstractScreen<T>> getScreens() {
        return map.values();
    }

    public <S extends AbstractScreen<T>> S get(T ID) {
        if(ID == null)
            throw new IllegalArgumentException();
        return (S) map.get(ID);
    }

    public <S extends AbstractScreen<T>> S register(T ID, AbstractScreen<T> screen) {
        if(ID == null || screen == null)
            throw new IllegalArgumentException();

        screen.setManager(this);
        screen.setID(ID);
        map.put(ID, screen);
        return (S) screen;
    }

    public <S extends AbstractScreen<T>> S unregister(AbstractScreen<T> screen) {
        if(screen == null)
            throw new IllegalArgumentException();

        map.remove(screen.getID());
        screen.setManager(null);
        screen.setID(null);
        return (S) screen;
    }

    public <S extends AbstractScreen<T>> S unregister(T ID) {
        final AbstractScreen<T> screen = this.get(ID);
        if(screen != null)
            this.unregister(screen);
        return (S) screen;
    }


    public boolean hasCurrent() {
        return (current != null);
    }

    public boolean isCurrent(AbstractScreen<T> screen) {
        return (current == screen);
    }

    public <S extends AbstractScreen<T>> S getCurrent() {
        return (S) current;
    }

    public boolean setCurrent(AbstractScreen<T> screen) {
        if(this.isCurrent(screen))
            return false;

        if(screen != null && !map.containsKey(screen.getID()))
            throw new IllegalArgumentException("Screen is not registered");

        if(this.hasCurrent())
            current.hide();

        this.current = screen;
        if(!this.hasCurrent())
            return true;

        current.tryToInit();
        current.resize(Jpize.getWidth(), Jpize.getHeight());
        current.show();
        return true;
    }

    public boolean setCurrent(T ID) {
        final AbstractScreen<T> screen = this.get(ID);
        if(screen == null)
            return false;
        this.setCurrent(screen);
        return true;
    }

    public ScreenManager<T> setCurrentNone() {
        this.setCurrent((AbstractScreen<T>) null);
        return this;
    }


    public void update() {
        if(this.hasCurrent())
            current.update();
    }

    public void render() {
        if(this.hasCurrent())
            current.render();
    }

    public void resize(int width, int height) {
        if(this.hasCurrent())
            current.resize(width, height);
    }

    @Override
    public void dispose() {
        this.setCurrentNone();
        for(AbstractScreen<T> screen: map.values())
            screen.dispose();
    }

}
