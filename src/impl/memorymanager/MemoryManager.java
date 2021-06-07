package impl.memorymanager;

import impl.memory.Memory;
import impl.memory.Segment;
import impl.structdata.DuoLinkedList;

public class MemoryManager {

    private DuoLinkedList<Segment> segmentsList;

    public MemoryManager(Memory memory) {
        Segment segment = new Segment(0, memory.getMemory().length, false);
        segmentsList = new DuoLinkedList<>(segment);
        memory.getMemory()[0] = segment;
    }

   /* public int malloc(int startingIndexOfSegment) {

    }

    public boolean free(int startingIndexOfLastFreeSegment) {

    }*/
}
