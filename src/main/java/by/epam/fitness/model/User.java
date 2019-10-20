package by.epam.fitness.model;

import java.time.LocalDateTime;

public class User extends AbstractNamedEntity {

    private String lastName;
    private LocalDateTime dateTime;
    private UserRole userRole;
    private boolean isActive;

    public User(Integer id, String name, String lastName, LocalDateTime dateTime, UserRole userRole) {
        super(id, name);
        this.lastName = lastName;
        this.dateTime = dateTime;
        this.userRole = userRole;
        this.isActive = true;
    }

    public User(String name, String lastName, LocalDateTime dateTime, UserRole userRole) {
        super(null, name);
        this.lastName = lastName;
        this.dateTime = dateTime;
        this.userRole = userRole;
        this.isActive = true;
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
