package impl.memorymanager;

import interfaces.Cache;
import java.util.*;

/**
 * This class implements LRU cache based on
 * queue and HashMap<K,V>. To the queue puts
 * keys and to the HashMap<K,V> save pairs
 * key - value.
 * @param <K> - key
 * @param <V> - value
 */

public class LruCache<K, V> implements Cache<K, V> {

    private final K[] queue;
    private final V defaultValue;
    private final HashMap<K, Container<V>> hashMap;
    private int head;
    private int tail;
    private Container<V> container;

    /**This class is necessary to account for
     * the number of identical elements in the map,
     * since map cannot store duplicates
     * @param <V> - value
     */

    private static class Container<V> {
        private final V value;
        private int amount;

        private Container(V value) {
            this.value = value;
            amount = 1;
        }

        @Override
        public String toString() {
            return "[val:" + value.toString() + ", amount:" + amount + "]";
        }
    }

    public LruCache(int capacity, V defaultValue) {
        queue = (K[])new Object[capacity];
        head = -1;
        tail = -1;
        hashMap = new HashMap<>(capacity /= 0.75, 0.75f);
        this.defaultValue = defaultValue;
        container = null;
    }

    public LruCache(LruCache<K, V> other) {
        this.queue = Arrays.copyOf(other.queue, other.queue.length);
        this.head = other.head;
        this.tail = other.tail;
        this.hashMap = new HashMap<>(other.hashMap);
        this.defaultValue = other.defaultValue;
        container = null;
    }

    /**
     * This method provides insert the key and the
     * value to the cache. The queue lets fastly to get
     * access to last added the key. HashMap is
     * necessary for fast search value by key.
     * @param key key
     * @param value value
     */

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        popBack();
        movingCounters();
        pushFront(key, value);
    }

    /**
     * This method remove the pair key-value from
     * HashMap if queue is full and an amount
     * same keys equal 1.
     */

    private void popBack() {
        int difference = head - tail;
        if (difference < 0 || difference == queue.length - 1 ) {
            container = hashMap.get(queue[tail]);
            if (container == null) {
                return;
            }
            if (container.amount > 1) {
                container.amount--;
            } else {
                hashMap.remove(queue[tail]);
            }
        }
    }

    /**
     * This method move indexes of head and tail
     * of the queue when inserting a new key.
     */

    private void movingCounters() {
        if (head == -1) {
            head++;
            tail++;
        } else if (head == queue.length - 1) {
            head = 0;
            tail++;
        } else if (tail == queue.length - 1) {
            tail = 0;
            head++;
        } else {
            if (head < tail) {
                tail++;
            }
            head++;
        }
    }

    /**
     * This method direct adding key to the queue
     * and a pair key-value to the HashMap
     * @param key key
     * @param value value.
     */

    private void pushFront(K key, V value) {
        queue[head] = key;
        container = hashMap.get(key);
        if (container == null) {
            hashMap.put(key, new Container<>(value));
        } else {
            container.amount++;
        }
    }

    /**
     * This method lets to get the value by specified the key.
     * @param key key
     * @return Returns the value which matching the specified
     * the key or the default value if in the HashMap is not
     * such match.
     */

    @Override
    public V get(K key) {
        container = hashMap.get(key);
        return (container == null || container.value == null)
                ? defaultValue : container.value;
    }

    /**
     * This method returns the last added to the cache value.
     * @return The last added to the cache value.
     */

    public V getLast() {
        container = hashMap.get(queue[head]);
        return (container == null) ? defaultValue : container.value;
    }

    public int getTail() {
        return tail;
    }

    public int getHead() {
        return head;
    }

    public K getKey(int index) {
        return queue[index];
    }

    /**
     * This method checks existence specified key
     * in the HashMap of cache.
     * @param key key
     * @return Returns true is the specified key
     * exist in the HashMap of cache else false.
     */

    @Override
    public boolean contains(K key) {
        return hashMap.containsKey(key);
    }

    /**
     * This method builds strings for group of elements of cache.
     * @param startIndex Starting index in the queue on base
     * array.
     * @param endIndex Ending index in the queue on base array.
     * @return Returns strings like "key:K  [val:V, amount:A]".
     */

    private String stringBuilder(int startIndex, int endIndex) {
        StringBuilder builder = new StringBuilder();
        for (int i = startIndex; i >= endIndex; i--) {
            builder.append("\nkey:");
            builder.append(queue[i].toString());
            builder.append(" ");
            builder.append(hashMap.get(queue[i]));
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        String string;
        if (head == -1) {
            return "";
        }
        if (head >= tail) {
            string = stringBuilder(head, tail);
        } else {
            string = stringBuilder(head, 0)
                    + stringBuilder(queue.length - 1, tail);
        }
        return string;
    }
}
