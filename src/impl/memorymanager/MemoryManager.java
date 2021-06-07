package impl.memorymanager;

import impl.memory.Memory;
import impl.memory.Segment;

public class MemoryManager {

    private final LruCache<Integer> lruCache;
    private final Memory memory;

    public MemoryManager(Memory memory, int cashCapacity) {
        Segment segment = new Segment(0,
                memory.getMemory().length,
                false,
                -1);
        this.memory = memory;
        this.memory.setMemoryLocation(0, segment);
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
        if (segmentLength < currentSegmentLength) {
            currentSegment.setLength(segmentLength);
            memory.setMemoryLocation(
                    currentIndex + segmentLength,
                    new Segment(currentIndex + segmentLength,
                    currentSegmentLength - segmentLength,
                    false, currentIndex));
            lruCache.put(currentIndex + segmentLength);
        }
        currentSegment.setBusyStatus(true);
        return currentIndex;
    }

    public boolean free(int startingIndex) {
        Segment currentSegment = memory.getMemoryLocation(
                startingIndex);
        if (currentSegment.getStartingIndex() == -1) {
            return false;
        }
        currentSegment.setBusyStatus(false);
        int nextIndex;
        int nextLength;
        if (isCorrectSegmentForMerging(
                nextIndex = currentSegment.getLength() + startingIndex)) {
            nextLength = memory.getMemoryLocation(nextIndex).getLength();
            currentSegment.setLength(nextLength + currentSegment.getLength());
            memory.setMemoryLocation(nextIndex, null);
        }
        int previousIndex;
        if (isCorrectSegmentForMerging(
                previousIndex = currentSegment.getPreviousIndex())) {
            nextLength = currentSegment.getLength();
            currentSegment = memory.getMemoryLocation(previousIndex);
            currentSegment.setLength(nextLength + currentSegment.getLength());
            memory.setMemoryLocation(startingIndex, null);
        }
        lruCache.put(currentSegment.getStartingIndex());
        return true;
    }

    public boolean isCorrectSegmentForMerging(int startingIndex) {
        Segment currentSegment = memory.getMemoryLocation(startingIndex);
        return  currentSegment.getStartingIndex() != -1 && !currentSegment.getBusyStatus();
    }

}
