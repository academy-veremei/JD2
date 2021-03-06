package by.academy.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 5917698791970011014L;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String role;

    public User() {
        super();
    }

    public User(String firstName, String lastName, String email, String login,
                String password, String role) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, login, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        User user = (User) obj;

        return (firstName != null && firstName.equals(user.firstName))
                && (lastName != null && lastName.equals(user.lastName))
                && (email != null && email.equals(user.email))
                && (login != null && login.equals(user.login))
                && (password != null && password.equals(user.password));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Имя: ").append(this.firstName).append("\nФамилия: ").append(this.lastName)
                .append("\nEmail: ").append(this.email).append("\nЛогин: ").append(this.login)
                .append("\nПароль: ").append(this.password);
        return stringBuilder.toString();
    }
}
