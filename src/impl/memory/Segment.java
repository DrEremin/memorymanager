package impl.memory;

public final class Segment {

    private final int startingIndexOfSegment;
    private final int length;
    private final boolean freelyBusy;

    public Segment() {
        startingIndexOfSegment = 0;
        length = 0;
        freelyBusy = false;
    }

    public Segment(int startingIndex, int length, boolean freelyBusy) {
        this.startingIndexOfSegment = startingIndex;
        this.length = length;
        this.freelyBusy = freelyBusy;
    }

    public int getStartingIndex() {
        return startingIndexOfSegment;
    }

    public int getLength() {
        return length;
    }

    public boolean getFreelyBusy() {
        return freelyBusy;
    }
}
