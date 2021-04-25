public class RunTrack extends Sport {
    private int length;

    public RunTrack(int l) {
        this.length = l;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Дорожка длиной " + length;
    }
}
