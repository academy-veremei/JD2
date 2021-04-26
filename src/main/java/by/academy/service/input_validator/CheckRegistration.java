package by.academy.service.input_validator;

public interface CheckRegistration {
    boolean isInputCorrect(String login, String password, String firstName, String lastName, String email);
}
