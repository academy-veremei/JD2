package by.academy.exceptions;

public class NewsException extends Exception {

    public NewsException() {
        super();
    }

    public NewsException(String message) {
        super(message);
    }

    public NewsException(Exception e) {
        super(e);
    }

    public NewsException(String message, Exception e) {
        super(message, e);
    }
}
