package lesson5;

import java.util.Arrays;

public class TrigonArray {
    static final int SIZE = 10_000_000;
    static final int HALF_SIZE = SIZE / 2;

    public void replaceStraight() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Простая замена: " + (endTime - startTime));
    }

    public void replaceBifurcated() throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        long startTime = System.currentTimeMillis();
        float[] arrFirstHalf = new float[HALF_SIZE];
        float[] arrSecondHalf = new float[SIZE - HALF_SIZE];
        System.arraycopy(arr, 0, arrFirstHalf, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, arrSecondHalf, 0, SIZE - HALF_SIZE);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < arrFirstHalf.length; i++) {
                arrFirstHalf[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < arrSecondHalf.length; i++) {
                arrSecondHalf[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.arraycopy(arrFirstHalf, 0, arr, 0, HALF_SIZE);
        System.arraycopy(arrSecondHalf, 0, arr, HALF_SIZE, SIZE - HALF_SIZE);
        long endTime = System.currentTimeMillis();
        System.out.println("Замена с раздвоением: " + (endTime - startTime));
    }
}
