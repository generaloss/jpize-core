package jpize.util.res;

import jpize.util.Disposable;

public abstract class ResourceHandle<K, R> implements Disposable {

    private final K key;
    private String path;

    public ResourceHandle(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    abstract public void load(Resource resource);

    abstract public R resource();

}
