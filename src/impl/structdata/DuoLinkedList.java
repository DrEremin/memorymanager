package impl.structdata;

import java.util.Optional;

/**
 * вернуть массив совпадающих значений
 * equals() и hashCode() для DuoLinkedNode
 * toString(), toArray(), конструктор с массивом
 */

public class DuoLinkedList <T> {

    /**
     * This nested class is a node of doubly linked list
     * @param <T>
     */

    private static class DuoLinkedNode <T>{

        T data;
        DuoLinkedNode<T> next;
        DuoLinkedNode<T> previous;

        DuoLinkedNode(T data,
                      DuoLinkedNode<T> previous,
                      DuoLinkedNode<T> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        /**
         * This method returns the data contained in this node
         */

        public Optional<T> getData() {
            return Optional.ofNullable(data);
        }

        /**
         * This method replace the data in this node
         */

        public void setData(T newData) {
            data = newData;
        }
    }

    int sizeList;
    int currentIndex;
    DuoLinkedNode<T> start;
    DuoLinkedNode<T> current;
    DuoLinkedNode<T> end;

    public DuoLinkedList() {
        sizeList = 0;
        currentIndex = 0;
        start = null;
        current = null;
        end = null;
    }

    public DuoLinkedList(T data) {

        start = new DuoLinkedNode<>(data, null, null);
        sizeList = 1;
        currentIndex = 0;
        current = start;
        end = start;
    }

    public DuoLinkedList(DuoLinkedList<T> other) {
        sizeList = 0;
        currentIndex = 0;
        start = null;
        current = null;
        end = null;
        if (other != null) {
            for (int i = 0; i < other.getSizeList(); i++) {
                if (other.getDataAtIndex(i).isPresent()) {
                    pushFront(other.getDataAtIndex(i).get());
                } else {
                    pushFront(null);
                }
            }
        }
    }

    public DuoLinkedList(T[] array) {
        sizeList = 0;
        currentIndex = 0;
        start = null;
        current = null;
        end = null;
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                pushFront(array[i]);
            }
        }
    }

    /**
     * This method returns the data is matching to the node,
     * wrapped in Optional
     */

    public Optional<T> getDataAtIndex(int index) {
        if (validIndex(index)) {
            getNodeAtIndex(index);
            return current.getData();
        } else {
            return Optional.empty();
        }
    }

    /**
     * This method returns the data is matching to the first node,
     * wrapped in Optional
     */

    public Optional<T> getDataOfBackNode() {
        return (isEmpty() ? start.getData() : Optional.empty());
    }

    /**
     * This method returns the data is matching to the first node,
     * wrapped in Optional
     */

    public Optional<T> getDataOfFrontNode() {
        return (isEmpty() ? end.getData() : Optional.empty());
    }

    /**
     * This method replace the data in node at index
     */

    public void setDataAtIndex(int index, T data) {
        if (validIndex(index)) {
            getNodeAtIndex(index);
            current.setData(data);
        }
    }

    /**
     * This method insert first element to the empty list
     */

    private void insertFirst(T data) {
        start = new DuoLinkedNode<>(data, null, null);
        sizeList = 1;
        currentIndex = 0;
        current = start;
        end = start;
    }

    /**
     * This method insert a node to the beginning of list
     */

    public void pushBack(T data) {
        if (isEmpty()) {
            insertFirst(data);
        } else {
            start.previous = new DuoLinkedNode<>(data, null, start);
            start = start.previous;
            sizeList++;
        }
    }

    /**
     * This method insert a node to the end of list
     */

    public void pushFront(T data) {
        if (isEmpty()) {
            insertFirst(data);
        } else {
            end.next = new DuoLinkedNode<>(data, end, null);
            end = end.next;
            sizeList++;
        }
    }

    /**
     * This method inserts a node between nodes matching
     * index - 1 and index
     */

    public void pushIndex(int index, T data) {
        if (index == sizeList) {
            pushFront(data);
        } else if (index == 0) {
            pushBack(data);
        } else {
            if (validIndex(index)) {
                getNodeAtIndex(index);
                current.previous.next = new DuoLinkedNode<>(
                        data,
                        current.previous,
                        current);
                current.previous = current.previous.next;
            }
        }
    }

    /**
     * This method remove the first node
     */

    public void popBack() {
        if (isEmpty()) {
            return;
        }
        if (sizeList == 1) {
            start = null;
            end = null;
        } else {
            start = start.next;
            start.previous = null;
        }
        current = null;
        sizeList--;
    }

    /**
     * This method remove the last node
     */

    public void popFront() {
        if (isEmpty()) {
            return;
        }
        if (sizeList == 1) {
            start = null;
            end = null;
        } else {
            end = end.previous;
            end.next = null;
        }
        current = null;
        sizeList--;
    }

    /**
     * This method remove the node at index
     */

    public void popIndex(int index) {
        if (validIndex(index)) {
            getNodeAtIndex(index);
            if (current.previous != null && current.next != null) {
                current.previous.next = current.next;
                current.next.previous = current.previous;
                current = null;
                sizeList--;
            } else if (current.previous == null && current.next != null) {
                popBack();
            } else if (current.next == null && current.previous != null) {
                popFront();
            }
        }
    }

    /**
     * This method goes back through the nodes to the
     * specified index, setting the variable "current"
     * on the desired node
     */

    private void iterationBack(int index) {
        current = end;
        currentIndex = sizeList - 1;
        while (currentIndex > index) {
            current = current.previous;
            currentIndex--;
        }
    }

    /**
     * This method goes forward through the nodes to the
     * specified index, setting the variable "current"
     * on the desired node
     */

    private void iterationFront(int index) {
        current = start;
        currentIndex = 0;
        while (currentIndex < index) {
            current = current.next;
            currentIndex++;
        }
    }

    /**
     * This method check this list on emptiness
     */

    public boolean isEmpty() {
        return (sizeList == 0);
    }

    /**
     * This method returns a size this DuoLinkedList
     */
    public int getSizeList() {
        return sizeList;
    }

    /**
     * This method checked validity of index for this list
     */

    private boolean validIndex(int index) {
        return (index >= 0 && index < sizeList);
    }

    /**
     * This method
     */

    private void getNodeAtIndex(int index) {
        if (index < sizeList - index - 1) {
            iterationFront(index);
        } else {
            iterationBack(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder concat = new StringBuilder();
        //concat.append("");
        current = start;
        currentIndex = 0;
        while (current != null) {
            if (current.getData().isPresent()) {
                concat.append("[" + current.getData().get() + "] ");
            } else {
                concat.append("[null] ");
            }
            current = current.next;
            currentIndex++;
        }
        return concat.toString();
    }
}
