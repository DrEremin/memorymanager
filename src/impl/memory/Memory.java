package impl.memory;

import impl.structdata.DuoLinkedList;
import interfaces.MyQueue;

import java.util.Arrays;

public class Memory {
    private final Segment[] sharedMemory;
    private final DuoLinkedList<Segment> segmentsList;

    public Memory(int length) {
        sharedMemory = new Segment[length];
        segmentsList = new DuoLinkedList<>(
                new Segment(0, length, false));
        Arrays.fill(sharedMemory, segmentsList.getDataOfFrontNode().get());
    }

    public Segment getMemoryLocation(int index) {
        return sharedMemory[index];
    }

    public int getSizeSharedMemory() {
        return sharedMemory.length;
    }

    /*public boolean isValidIndex(int index) {
        return index >= 0 && index < sharedMemory.length;
    }*/
}

