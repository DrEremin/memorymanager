package impl.memory;

/**
 * This class implements a segment of memory.
 */

public final class Segment {

    private int startingIndex;
    private int length;
    private int previousIndex;
    private boolean busyStatus;


    public Segment() {
        startingIndex = 0;
        length = 0;
        busyStatus = false;
        previousIndex = -1;
    }

    public Segment(int startingIndex,
                   int length,
                   boolean busyStatus,
                   int previousSegmentIndex) {
        this.startingIndex = startingIndex;
        this.length = length;
        this.busyStatus = busyStatus;
        this.previousIndex = previousSegmentIndex;
    }

    public void setStartingIndex(int newValue) {
        startingIndex = newValue;
    }

    public void setLength(int newLength) {
        length = newLength;
    }

    public void setBusyStatus(boolean newBusyStatus) {
        busyStatus = newBusyStatus;
    }

    public void setPreviousIndex(int newValue) {
        previousIndex = newValue;
    }

    public int getStartingIndex() {
        return startingIndex;
    }

    public int getLength() {
        return length;
    }

    public boolean getBusyStatus() {
        return busyStatus;
    }

    public int getPreviousIndex() {
        return previousIndex;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(start:");
        builder.append(startingIndex);
        builder.append(", length:");
        builder.append(length);
        builder.append(", status:");
        builder.append(busyStatus ? "Busy)" : "Freely)");
        return builder.toString();
    }
}
