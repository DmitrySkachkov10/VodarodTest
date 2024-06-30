package by.dmitry_skachkov.vodarodtest.core.exception;

public class FeignClientException extends CustomApplicationException {

    public FeignClientException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
