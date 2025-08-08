package jpize.context;

import resourceflow.Disposable;

public abstract class JpizeApplication implements Disposable {

    public void init(){ }

    public void update(){ }

    public void render(){ }

    public void resize(int width, int height){ }

    @Override
    public void dispose(){ }

}
