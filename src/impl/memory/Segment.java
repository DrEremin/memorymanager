package impl.memory;

public final class Segment {

    private int startingIndexOfSegment;
    private int length;
    private boolean busyStatus;

    public Segment() {
        startingIndexOfSegment = 0;
        length = 0;
        busyStatus = false;
    }

    public Segment(int startingIndex, int length, boolean busyStatus) {
        this.startingIndexOfSegment = startingIndex;
        this.length = length;
        this.busyStatus = busyStatus;
    }

    public int getStartingIndex() {
        return startingIndexOfSegment;
    }

    public int getLength() {
        return length;
    }

    public boolean getBusyStatus() {
        return busyStatus;
    }

    public void setLength(int newLength) {
        length = newLength;
    }

    public void setBusyStatus(boolean newBusyStatus) {
        busyStatus = newBusyStatus;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(start = ");
        builder.append(String.valueOf(startingIndexOfSegment));
        builder.append(" length = ");
        builder.append(String.valueOf(length));
        builder.append(busyStatus ? " Busy)" : " Freely)");
        return builder.toString();
    }
}
