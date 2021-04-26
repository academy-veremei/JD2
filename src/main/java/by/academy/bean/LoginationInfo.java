package by.academy.bean;

import java.io.Serializable;
import java.util.Objects;

public class LoginationInfo implements Serializable {
    private static final long serialVersionUID = 2552753980624646126L;
    private String login;
    private String password;

    public LoginationInfo() {
        super();
    }

    public LoginationInfo(String login, String password) {
        super();
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        LoginationInfo lInfo = (LoginationInfo) obj;
        return (login != null && login.equals(lInfo.login))
                && (password != null && password.equals(lInfo.password));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Login: ").append(this.login).append(" ").append("Password: ").append(this.password);
        return stringBuilder.toString();
    }
}