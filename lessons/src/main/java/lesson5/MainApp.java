package lesson5;

public class MainApp {
    public static void main(String[] args) {
        TrigonArray arrClass = new TrigonArray();
        arrClass.replaceStraight();
        try {
            arrClass.replaceBifurcated();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        arrClass.replaceStraight();
        arrClass.replaceStraight();
        arrClass.replaceStraight();
        arrClass.replaceStraight();
        arrClass.replaceStraight();
        arrClass.replaceStraight();
    }
}
