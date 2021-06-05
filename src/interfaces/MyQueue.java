package interfaces;

public interface MyQueue<T> {

    boolean isEmpty();

    int getSize();

    boolean push(T data);

    T pop();

    void clear();
}
