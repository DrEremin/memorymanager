package impl.memory;

public class Memory {
    private final Segment[] sharedMemory;
    private final Segment defaultValue;

    public Memory(int length) {
        sharedMemory = new Segment[length];
        defaultValue = new Segment(-1, -1, false, -1);
    }

    public Segment[] getMemory() {
        return sharedMemory;
    }

    public Segment getMemoryLocation(int index) {
        if (!isValidIndex(index) || sharedMemory[index] == null) {
            return defaultValue;
        }
        return sharedMemory[index];
    }

    public int getSizeSharedMemory() {
        return sharedMemory.length;
    }

    public void setMemoryLocation(int index, Segment newSegment) {
        sharedMemory[index] = newSegment;
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < sharedMemory.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sharedMemory.length; i++) {
            builder.append("[");
            builder.append(String.valueOf(i));
            builder.append("] = ");
            builder.append(String.valueOf(sharedMemory[i]));
            builder.append("\n");
        }
        builder.replace(builder.length() - 1,
                builder.length(),
                "");
        return builder.toString();
    }
}

