package impl.memorymanager;

import interfaces.Cache;
import impl.structdata.Stack;
import impl.memory.Segment;

public class LruCache implements Cache<Segment> {

    Stack<Segment> stack;

    @Override
    public Segment get(int page) {
        return stack.pop();
        //implements
        //return stack.push();
    }

    @Override
    public boolean contains(int page) {
        return true;
    }
}
