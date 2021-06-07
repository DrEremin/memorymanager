package impl.memorymanager;

import interfaces.Cache;
import impl.memory.Segment;

public class LruCache<T> implements Cache<T> {

    private T[] dequeue;
    private int head;
    private int tail;

    public LruCache(int capacity) {
        dequeue = (T[]) new Object[capacity + 1];
        head = -1;
        tail = -1;
    }

    @Override
    public void put(T data) {
        if (head == -1 && tail == -1) {
            head += 2;
            dequeue[head] = data;
            tail++;
        } else if (head == dequeue.length - 1) {
            head = 0;
            dequeue[head] = data;
            tail++;
        } else if (tail == dequeue.length - 1) {
            tail = 0;
            dequeue[++head] = data;
        } else {
            if (head < tail) {
                tail++;
            }
            dequeue[++head] = data;
        }
    }

    @Override
    public T get(int page) {
        return dequeue[0];
        //implements
        //return stack.push();
    }

    @Override
    public boolean contains(int page) {
        return true;
    }

    public T getLast() {
        return dequeue[head];
    }

    private String stringBuilder(int startIndex, int endIndex) {
        StringBuilder builder = new StringBuilder();
        for (int i = startIndex; i > endIndex; i--) {
            builder.append(dequeue[i].toString());
            builder.append(" ");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        String string;
        if (head == tail) {
            return "";
        } else if (head > tail) {
            string = stringBuilder(head, tail);
        } else {
            string = stringBuilder(head, -1)
                    + stringBuilder(dequeue.length - 1, tail);
        }
        return string;
    }
}
