package impl.memorymanager;

import impl.memory.Memory;
import impl.memory.Segment;
import impl.structdata.DuoLinkedList;

public class MemoryManager {

    private final DuoLinkedList<Segment> segmentsList;
    private final LruCache<Integer> lruCache;
    private final Memory memory;

    public MemoryManager(Memory memory, int cashCapacity) {
        Segment segment = new Segment(0, memory.getMemory().length, false);
        segmentsList = new DuoLinkedList<>(segment);
        this.memory = memory;
        memory.getMemory()[0] = segment;
        lruCache = new LruCache<>(cashCapacity);
        lruCache.put(0);
    }

    public String cashToString() {
        return lruCache.toString();
    }

    public int malloc(int segmentLength) {
        int currentIndex = lruCache.getLast();
        Segment currentSegment = memory.getMemoryLocation(currentIndex);
        int currentSegmentLength = currentSegment.getLength();
        if (segmentLength > currentSegmentLength) {
            return -1;
        }
        if (segmentLength == currentSegmentLength) {
            currentSegment.setBusyStatus(true);
        } else {
            currentSegment.setLength(segmentLength);
            currentSegment.setBusyStatus(true);
            segmentsList.pushFront(new Segment(currentIndex + segmentLength,
                    currentSegmentLength - segmentLength,
                    false));
            memory.getMemory()[currentSegmentLength - segmentLength] = segmentsList.getDataOfFrontNode().get();
            lruCache.put(currentIndex + segmentLength);
        }
        System.out.println("list: " + segmentsList);
        return currentIndex;
    }

    /*public boolean free(int startingIndexOfLastFreeSegment) {

    }*/
}
