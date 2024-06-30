package by.dmitry_skachkov.vodarodtest.core.exception;

import java.util.Objects;

public class ExceptionResponseDto {

    private final String logref = "error";

    private String message;

    public ExceptionResponseDto() {
    }

    public ExceptionResponseDto(String message) {

        this.message = message;
    }

    public String getLogref() {
        return logref;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExceptionResponseDto that)) return false;
        return Objects.equals(logref, that.logref) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logref, message);
    }
}