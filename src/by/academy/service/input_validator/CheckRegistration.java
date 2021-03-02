package by.academy.service.input_validator;

public interface CheckRegistration {
    boolean check(String login, String password, String firstName, String lastName, String email);
}
