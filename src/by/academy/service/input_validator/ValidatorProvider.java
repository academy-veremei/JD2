package by.academy.service.input_validator;

import by.academy.service.input_validator.impl.CheckLoginInput;
import by.academy.service.input_validator.impl.CheckModifyInput;
import by.academy.service.input_validator.impl.CheckRegistrationInput;

public class ValidatorProvider {
    private static final ValidatorProvider instance = new ValidatorProvider();

    private ValidatorProvider() {
    }

    private final CheckLoginInput checkLoginInput = new CheckLoginInput();
    private final CheckRegistrationInput checkRegistrationInput = new CheckRegistrationInput();
    private final CheckModifyInput checkModifyInput = new CheckModifyInput();

    public static ValidatorProvider getInstance() {
        return instance;
    }

    public CheckLoginInput getCheckLoginInput() {
        return checkLoginInput;
    }

    public CheckRegistrationInput getCheckRegistrationInput() {
        return checkRegistrationInput;
    }

    public CheckModifyInput getCheckModifyInput() {
        return checkModifyInput;
    }

}
