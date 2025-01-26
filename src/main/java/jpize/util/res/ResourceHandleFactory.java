package jpize.util.res;

@FunctionalInterface
public interface ResourceHandleFactory<K, H> {

    H create(K key);

}
