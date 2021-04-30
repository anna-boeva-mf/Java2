package lesson1;

public class Wall extends Sport {
    private int height;

    public Wall(int h) {
        this.height = h;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Стена высотой " + height;
    }
}