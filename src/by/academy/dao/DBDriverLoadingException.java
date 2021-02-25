package by.academy.dao;

public class DBDriverLoadingException extends RuntimeException{

    public DBDriverLoadingException(Exception e) {
        super(e);
    }
}
