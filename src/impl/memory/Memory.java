package impl.memory;

public class Memory {
    Segment[] sharedMemory;

    public Memory(int length) {
        sharedMemory = new Segment[length];
    }

    public Segment[] getSharedMemory() {
        return sharedMemory;
    }
}

