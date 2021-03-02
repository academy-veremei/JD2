package by.academy.service.input_validator.impl;

import by.academy.service.input_validator.CheckRegistration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckRegistrationInput implements CheckRegistration {
    private Pattern patternLogin = Pattern.compile("[a-z0-9]{6,16}");
    private Pattern patternPassword = Pattern.compile("[a-z0-9]{6,16}");
    private Pattern patternName = Pattern.compile("[(a-zA-z)|(а-яА-я)]{2,16}");
    private Pattern patternEmail = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$");

    @Override
    public boolean check(String login, String password, String firstName, String lastName, String email) {
        Matcher matcherLogin = patternLogin.matcher(login);
        Matcher matcherPassword = patternPassword.matcher(password);
        Matcher matcherFirstName = patternName.matcher(firstName);
        Matcher matcherLastName = patternName.matcher(lastName);
        Matcher matcherEmail = patternEmail.matcher(email);

        boolean firstCheck = login == null ||
                "".equals(login) ||
                password == null ||
                "".equals(password) ||
                firstName == null ||
                "".equals(firstName) ||
                lastName == null ||
                "".equals(lastName) ||
                email == null ||
                "".equals(email);

        boolean secondCheck = matcherLogin.find() &&
                matcherPassword.find() &&
                matcherFirstName.find() &&
                matcherLastName.find() &&
                matcherEmail.find();

        if (firstCheck) {
            return false;
        } else return secondCheck;
    }
}
