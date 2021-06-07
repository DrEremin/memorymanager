package interfaces;

public interface Cache<T> {

    void put(T data);

    T get(int page);

    boolean contains(int page);
}
