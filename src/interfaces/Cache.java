package interfaces;

public interface Cache<T> {

    T get(int page);

    boolean contains(int page);
}
