/**
 * This class implements a stack based on ArrayList<T>
 * @param T This type of runtime is type of elements
 * contains in stack
 */
package impl.structdata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import interfaces.MyQueue;

public class Stack<T> implements MyQueue<T> {

    protected ArrayList<T> stack;

    /**
     * Constructors
     */

    public Stack() {
        stack = new ArrayList<>();
    }

    public Stack(T data) {
        stack = new ArrayList<>();
        stack.add(data);
    }

    public Stack(Collection<T> collection) {
        stack = new ArrayList<>(collection);
    }

    /**
     * The check of stack at emptiness
     * @return true if stack is empty else false
     */

    @Override
    public boolean isEmpty() {
        return stack.size() == 0;
    }

    /**
     * @return Returns a size of this stack
     */

    @Override
    public int getSize() {
        return stack.size();
    }

    /**
     * @return Returns a string representation this stack
     */

    @Override
    public String toString() {
        return stack.toString();
    }

    /**
     * This method insert data to top of stack
     * @param data The data which must be inserted to stack
     * @return Returns true if element successfully
     * inserted to stack
     */

    @Override
    public boolean push(T data) {

        return data != null && stack.add(data);
    }

    /**
     * This method remove an element from top of stack
     * @return Returns either link at object which removed
     * from top of stack or link at object by default
     */

    @Override
    public T pop() {
        return stack.remove(stack.size() - 1);
    }

    /**
     * This method remove all elements from the stack
     */

    @Override
    public void clear() {
        stack.clear();
    }
}
