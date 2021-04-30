package lesson2;

import lesson2.exceptions.*;

public class MainApp {
    public static void main(String[] args) {
        String[][] array1 = new String[4][4];
        String[][] array2 = new String[4][3];
        String[][] array3 = new String[4][4];
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = String.valueOf(10 * i * j);
            }
        }
        for (int i = 0; i < array2.length; i++) {
            for (int j = 0; j < array2[0].length; j++) {
                array2[i][j] = String.valueOf(10 * i * j);
            }
        }
        for (int i = 0; i < array3.length; i++) {
            for (int j = 0; j < array3[0].length; j++) {
                array3[i][j] = String.valueOf(10 * i * j);
            }
        }
        array3[2][2] = "fgdg";
        try{
            System.out.println(sumArray(array1));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
        try{
            System.out.println(sumArray(array2));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
        try{
            System.out.println(sumArray(array3));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }

    public static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("Размер массива не равен 4х4");
        }
        int sumAr = 0;
        int i = 0, j = 0;
        try {
            for (i = 0; i < array.length; i++) {
                for (j = 0; j < array[0].length; j++) {
                    sumAr += Integer.parseInt(array[i][j]);
                }
            }
        } catch (NumberFormatException e) {
            throw new MyArrayDataException("В поле массива [" + i + "][" + j + "] находится не число");
        }
        return sumAr;
    }
}
