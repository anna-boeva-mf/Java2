package lesson2.exceptions;

public class MyArraySizeException extends RuntimeException {
    public MyArraySizeException() {
        super("Размер массива не равен 4х4");
    }
}
