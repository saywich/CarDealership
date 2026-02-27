package ng.saywich.domain.exceptions;

public class IncompatibleOrderStateException extends RuntimeException {
    public IncompatibleOrderStateException(String message) {
        super(message);
    }
}
