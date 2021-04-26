package by.academy.dao;

public class EmptyResultSetException extends Exception {
    public EmptyResultSetException() {
        super();
    }

    public EmptyResultSetException(String message) {
        super(message);
    }

    public EmptyResultSetException(Exception e) {
        super(e);
    }

    public EmptyResultSetException(String message, Exception e) {
        super(message, e);
    }

    public EmptyResultSetException(int code) {
        super(String.valueOf(code));
    }
}
