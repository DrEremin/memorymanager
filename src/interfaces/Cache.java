package interfaces;


public interface Cache<K, V> {

    void put(K key, V value);

    V get(K key);

    boolean contains(K key);
}
