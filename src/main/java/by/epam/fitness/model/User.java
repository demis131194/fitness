package by.epam.fitness.model;

import java.time.LocalDateTime;

public class User extends AbstractNamedEntity {

    private String login;
    private String password;
    private String lastName;
    private LocalDateTime dateTime;
    private UserRole userRole;
    private boolean isActive;

    public User(Integer id, String name, String login, String password, String lastName, LocalDateTime dateTime, UserRole userRole) {
        super(id, name);
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.dateTime = dateTime;
        this.userRole = userRole;
        this.isActive = true;
    }

    public User(String name, String login, String password, String lastName, LocalDateTime dateTime, UserRole userRole) {
        super(null, name);
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.dateTime = dateTime;
        this.userRole = userRole;
        this.isActive = true;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
