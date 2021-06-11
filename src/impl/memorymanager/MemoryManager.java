package impl.memorymanager;

import impl.memory.Memory;
import impl.memory.Segment;

/**
 * This class provides correct allocated and release of memory.
 */

public class MemoryManager {

    private final LruCache<Integer, Segment> lruCache;
    private final Memory memory;

    public MemoryManager(Memory memory, int cashCapacity) {
        Segment segment = new Segment(0, memory.getMemory().length, false, -1);
        this.memory = memory;
        this.memory.setMemoryLocation(0, segment);
        lruCache = new LruCache<>(cashCapacity, memory.getDefaultValue());
        lruCache.put(0, segment);
    }

    /**
     * This method provides allocation memory, size which
     * equals the specified argument.
     * @param newSegmentLength A size of allocated segment of memory
     * @return Index of cell of array which links on new segment of memory
     */

    public int malloc(int newSegmentLength) {
        Segment currentSegment = lruCache.getLast();
        int currentSegmentLength = currentSegment.getLength();
        int currentStartingIndex = currentSegment.getStartingIndex();
        if (newSegmentLength > currentSegmentLength
                || currentSegment.getBusyStatus()) {
            return -1;
        }
        if (newSegmentLength < currentSegmentLength) {
            createNewSegment(currentSegment, newSegmentLength);
        }
        currentSegment.setBusyStatus(true);
        return currentStartingIndex;
    }

    /**
     *This method cuts specified segment of memory
     * which will afterwards busy and  directly create
     * the new segment of free memory to the right
     * from circumcised . Size of new segment equal a
     * difference sizes of the specified segment before
     * and after the cut.
     * @param currentSegment - segment of memory for cut
     * @param newSegmentLength - new length of specified
     * segment.
     */

    private void createNewSegment(Segment currentSegment, int newSegmentLength) {
        int currentSegmentLength = currentSegment.getLength();
        int currentStartingIndex = currentSegment.getStartingIndex();
        int newStartingIndex = currentStartingIndex + newSegmentLength;
        currentSegment.setLength(newSegmentLength);
        Segment newSegment = new Segment(
                newStartingIndex,
                currentSegmentLength - newSegmentLength,
                false,
                currentStartingIndex);
        memory.setMemoryLocation(newStartingIndex, newSegment);
        lruCache.put(newStartingIndex, memory.getMemoryLocation(newStartingIndex));
    }

    /**
     * This method rules the release memory of segment
     * on which refers the cell of array with the
     * specified index.
     * @param startingIndex - starting index of
     * segment of memory
     * @return : true if the specified index is
     * the index of cell of array which refer on
     * start of segmenta else false
     */

    public boolean free(int startingIndex) {
        Segment currentSegment = memory
                .getMemoryLocation(startingIndex);
        Segment previousSegment = memory
                .getMemoryLocation(currentSegment.getPreviousIndex());
        if (currentSegment.getStartingIndex() == -1) {
            return false;
        }
        currentSegment.setBusyStatus(false);
        rightMerging(currentSegment);
        if (isValidForMerging(previousSegment)) {
            currentSegment = rightMerging(previousSegment);
        }
        lruCache.put(currentSegment.getStartingIndex(), currentSegment);
        return true;
    }

    /**
     * This method merges the specified segment with the
     * adjoining right segment only then when right
     * segment has free memory.
     * @param currentSegment - segment of memory for merge
     * @return - merged segment or segment without changes.
     */

    private Segment rightMerging(Segment currentSegment) {
        int currentLength = currentSegment.getLength();
        int nextIndex = currentSegment.getStartingIndex() + currentLength;
        Segment nextSegment = memory.getMemoryLocation(nextIndex);
        if (isValidForMerging(nextSegment)) {
            currentLength += nextSegment.getLength();
            currentSegment.setLength(currentLength);
            memory.setMemoryLocation(nextIndex, null);
        }
        return currentSegment;
    }

    public boolean isValidForMerging(Segment currentSegment) {
        return  currentSegment.getStartingIndex() != -1 && !currentSegment.getBusyStatus();
    }

    public LruCache<Integer, Segment> getLruCache() {
        return new LruCache<>(lruCache);
    }
}
