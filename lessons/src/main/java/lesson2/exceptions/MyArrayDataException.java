package lesson2.exceptions;

public class MyArrayDataException extends NumberFormatException{
    public MyArrayDataException(int i, int j) {
        super("В поле массива [" + i + "][" + j + "] находится не число");
    }
}
