package by.epam.fitness.model;

import java.time.LocalDateTime;

public class User extends AbstractNamedEntity {

    private String login;
    private String password;
    private String lastName;
    private LocalDateTime registerDateTime;
    private UserRole userRole;
    private boolean isActive;

    public User(Integer id, String name, String lastName, String login, String password, LocalDateTime registerDateTime, UserRole userRole) {
        super(id, name);
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.registerDateTime = registerDateTime;
        this.userRole = userRole;
        this.isActive = true;
    }

    public User(String login, String password, String name, String lastName, LocalDateTime registerDateTime, UserRole userRole) {
        super(null, name);
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.registerDateTime = registerDateTime;
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

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(LocalDateTime registerDateTime) {
        this.registerDateTime = registerDateTime;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", registerDateTime=").append(registerDateTime);
        sb.append(", userRole=").append(userRole);
        sb.append(", isActive=").append(isActive);
        sb.append(", name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
