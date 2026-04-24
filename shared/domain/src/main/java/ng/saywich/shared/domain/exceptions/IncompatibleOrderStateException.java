package ng.saywich.shared.domain.exceptions;

public class IncompatibleOrderStateException extends RuntimeException {
    public IncompatibleOrderStateException(String message) {
        super(message);
    }
}
