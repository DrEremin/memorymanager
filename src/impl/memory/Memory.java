package impl.memory;

import impl.structdata.DuoLinkedList;

import java.util.Arrays;

public class Memory {
    private final Segment[] sharedMemory;
    private final Segment defaultValue;

    public Memory(int length) {
        sharedMemory = new Segment[length];
        defaultValue = new Segment(-1, -1, false);
    }

    public Segment[] getMemory() {
        return sharedMemory;
    }

    public Segment getMemoryLocation(int index) {
        if (isValidIndex(index)) {
            return sharedMemory[index];
        }
        return defaultValue;
    }

    /*public void setMemoryLocation(int index, Segment segment) {
        sharedMemory[index] = segment;
    }*/

    public int getSizeSharedMemory() {
        return sharedMemory.length;
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < sharedMemory.length;
    }
}

