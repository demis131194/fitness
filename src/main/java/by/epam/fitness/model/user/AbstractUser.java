package by.epam.fitness.model.user;

import by.epam.fitness.model.AbstractBaseEntity;

public abstract class AbstractUser extends AbstractBaseEntity {

    protected String login;
    protected String password;
    protected UserRole role;

    public AbstractUser() {
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
