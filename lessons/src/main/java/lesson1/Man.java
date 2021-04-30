package lesson1;

public class Man implements Running, Jumping {
    public String name;
    public int maxLength;
    public int maxHeight;

    public Man(String name, int mL, int mH) {
        this.name = name;
        this.maxLength = mL;
        this.maxHeight = mH;
    }

    @Override
    public String toString() {
        return "Человек по имени " + name + " бегает до " + maxLength + " прыгает до " + maxHeight;
    }

    @Override
    public int run(Sport s) {
        if (s instanceof RunTrack) {
            if (maxLength >= ((RunTrack) s).getLength()) {
                System.out.printf("Человек %s пробежал по дорожке длиной %d.\n", name, ((RunTrack) s).getLength());
            } else {
                System.out.printf("Человек %s не смог пробежать по дорожке длиной %d.\n", name, ((RunTrack) s).getLength());
                return -1;
            }
        }
        return 0;
    }

    @Override
    public int jump(Sport s) {
        if (s instanceof Wall) {
            if (maxHeight >= ((Wall) s).getHeight()) {
                System.out.printf("Человек %s перепрыгнул стену высотой %d.\n", name, ((Wall) s).getHeight());
            } else {
                System.out.printf("Человек %s не смог перепрыгнуть стену высотой %d.\n", name, ((Wall) s).getHeight());
                return -1;
            }
        }
        return 0;
    }
}
