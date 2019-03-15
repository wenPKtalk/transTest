package exception;

public class InputException extends RuntimeException {

    public InputException() {
        super(ErrorCodes.ERROR_INPUT.toString());
    }
}
