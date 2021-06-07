package impl.memorymanager;

import impl.memory.Memory;
import impl.memory.Segment;
import impl.structdata.DuoLinkedList;

public class MemoryManager {

    private final DuoLinkedList<Segment> segmentsList;
    private final LruCache<Integer> lruCache;

    public MemoryManager(Memory memory, int cashCapacity) {
        Segment segment = new Segment(0, memory.getMemory().length, false);
        segmentsList = new DuoLinkedList<>(segment);
        memory.getMemory()[0] = segment;
        lruCache = new LruCache<>(cashCapacity);
        lruCache.put(0);
    }

    public String cashToString() {
        return lruCache.toString();
    }

    /*public int malloc(int sizeSegmentMemory) {

    }

    public boolean free(int startingIndexOfLastFreeSegment) {

    }*/
}
