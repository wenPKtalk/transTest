package exception;

public class InputException extends Exception {
    public InputException() {
    }


    public InputException(String message) {
        super(message);
        System.out.println(message);
    }
}
