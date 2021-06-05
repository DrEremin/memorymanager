package impl.memory;

public final class Segment {

    private final int startingIndex;
    private final int length;
    private final boolean freelyBusy;

    public Segment() {
        startingIndex = 0;
        length = 0;
        freelyBusy = false;
    }

    public Segment(int startingIndex, int length, boolean freelyBusy) {
        this.startingIndex = startingIndex;
        this.length = length;
        this.freelyBusy = freelyBusy;
    }

    public int getStartingIndex() {
        return startingIndex;
    }

    public int getLength() {
        return length;
    }

    public boolean getFreelyBusy() {
        return freelyBusy;
    }
}
