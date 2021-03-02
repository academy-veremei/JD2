package by.academy.exceptions;

public class UserException extends Exception {

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(Exception e) {
        super(e);
    }

    public UserException(String message, Exception e) {
        super(message, e);
    }
}
