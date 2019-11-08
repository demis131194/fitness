package by.epam.fitness.exception;

public class ValidatorExcepton extends Exception {
    public ValidatorExcepton() {
        super();
    }

    public ValidatorExcepton(String message) {
        super(message);
    }

    public ValidatorExcepton(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorExcepton(Throwable cause) {
        super(cause);
    }
}
