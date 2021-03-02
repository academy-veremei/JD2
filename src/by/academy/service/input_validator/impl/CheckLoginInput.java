package by.academy.service.input_validator.impl;

import by.academy.service.input_validator.CheckLogin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckLoginInput implements CheckLogin {
    private Pattern patternLogin = Pattern.compile("[a-z0-9]{6,16}");
    private Pattern patternPassword = Pattern.compile("[a-z0-9]{6,16}");

    @Override
    public boolean check(String login, String password) {
        Matcher matcherLogin = patternLogin.matcher(login);
        Matcher matcherPassword = patternPassword.matcher(password);

        boolean firstCheck = login == null || "".equals(login) || password == null || "".equals(password);

        if (firstCheck) {
            return false;
        } else return matcherLogin.find() && matcherPassword.find();
    }
}
