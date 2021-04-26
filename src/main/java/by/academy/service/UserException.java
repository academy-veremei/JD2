package by.academy.service;

public class UserException extends Exception {

    private static final long serialVersionUID = -4928654198582933838L;

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
