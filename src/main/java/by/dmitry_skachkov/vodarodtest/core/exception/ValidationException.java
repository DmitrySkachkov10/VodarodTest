package by.dmitry_skachkov.vodarodtest.core.exception;

public class ValidationException extends CustomApplicationException {

    public ValidationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
