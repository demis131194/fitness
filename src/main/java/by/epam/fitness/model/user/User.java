package by.epam.fitness.model.user;

import by.epam.fitness.model.AbstractBaseEntity;

import java.util.Objects;

/**
 * The type User.
 */
public class User extends AbstractBaseEntity {

    /**
     * The Login.
     */
    protected String login;
    /**
     * The Password.
     */
    protected String password;
    /**
     * The Role.
     */
    protected UserRole role;
    /**
     * The Is active.
     */
    protected boolean isActive;
    private String profileImagePath;
    private boolean verification;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Gets profile image path.
     *
     * @return the profile image path
     */
    public String getProfileImagePath() {
        return profileImagePath;
    }

    /**
     * Sets profile image path.
     *
     * @param profileImagePath the profile image path
     */
    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Is verification boolean.
     *
     * @return the boolean
     */
    public boolean isVerification() {
        return verification;
    }

    /**
     * Sets verification.
     *
     * @param verification the verification
     */
    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isActive == user.isActive &&
                verification == user.verification &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role, isActive, verification);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", isActive=").append(isActive);
        sb.append(", verification=").append(verification);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
