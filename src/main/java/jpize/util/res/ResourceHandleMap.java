package jpize.util.res;

import jpize.util.Disposable;

import java.util.HashMap;
import java.util.Map;

public class ResourceHandleMap<K, H extends ResourceHandle<K, ?>> implements Disposable {

    private ResourceHandleFactory<K, H> handleFactory;
    private IResourceSource source;
    private final Map<K, H> map;

    public ResourceHandleMap() {
        this.map = new HashMap<>();
    }

    public ResourceHandleMap(IResourceSource source, ResourceHandleFactory<K, H> handleFactory) {
        this();
        this.setSource(source);
        this.setHandleFactory(handleFactory);
    }


    public ResourceHandleFactory<K, H> getHandleFactory() {
        return handleFactory;
    }

    public void setHandleFactory(ResourceHandleFactory<K, H> handleFactory) {
        this.handleFactory = handleFactory;
    }


    public IResourceSource getSource() {
        return source;
    }

    public void setSource(IResourceSource source) {
        this.source = source;
    }


    public H get(K key) {
        return map.get(key);
    }

    public void remove(H handle) {
        if(handle == null)
            return;

        handle.dispose();
        map.remove(handle.getKey());
    }

    public void remove(K key) {
        this.remove(map.get(key));
    }

    public H load(K key, String path) {
        if(source == null)
            throw new IllegalStateException("Resource source not set");

        final Resource resource = source.getResource(path);
        if(resource == null || !resource.exists())
            return null;

        final H handle = handleFactory.create(key);
        handle.setPath(path);
        handle.load(resource);

        map.put(key, handle);
        return handle;
    }

    public void reload() {
        if(source == null)
            throw new IllegalStateException("Resource source not set");

        map.forEach((key, handle) -> {
            final Resource resource = source.getResource(handle.getPath());
            if(resource == null || !resource.exists())
                return;
            handle.load(resource);
        });
    }

    @Override
    public void dispose() {
        for(H handle: map.values())
            handle.dispose();
        map.clear();
    }

}
