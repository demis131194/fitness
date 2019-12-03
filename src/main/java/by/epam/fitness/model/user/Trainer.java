package by.epam.fitness.model.user;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Trainer.
 */
public class Trainer extends User {

    private String name;
    private String lastName;
    private LocalDateTime registerDateTime;
    private String phone;
    private String mail;

    /**
     * Instantiates a new Trainer.
     */
    public Trainer() {
        role = UserRole.TRAINER;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets register date time.
     *
     * @return the register date time
     */
    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    /**
     * Sets register date time.
     *
     * @param registerDateTime the register date time
     */
    public void setRegisterDateTime(LocalDateTime registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets mail.
     *
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets mail.
     *
     * @param mail the mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(name, trainer.name) &&
                Objects.equals(lastName, trainer.lastName) &&
                Objects.equals(registerDateTime, trainer.registerDateTime) &&
                Objects.equals(phone, trainer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, registerDateTime, phone);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trainer{");
        sb.append("name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", registerDateTime=").append(registerDateTime);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
