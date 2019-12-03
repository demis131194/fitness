package by.epam.fitness.model.user;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Client.
 */
public class Client extends User {

    private String name;
    private String lastName;
    private LocalDateTime registerDateTime;
    private Integer discount;
    private Integer discountLevel;
    private String phone;
    private String mail;
    private BigDecimal cash;

    /**
     * Instantiates a new Client.
     */
    public Client() {
        role = UserRole.CLIENT;
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
     * Gets discount.
     *
     * @return the discount
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * Sets discount.
     *
     * @param discount the discount
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
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
     * Gets cash.
     *
     * @return the cash
     */
    public BigDecimal getCash() {
        return cash;
    }

    /**
     * Sets cash.
     *
     * @param cash the cash
     */
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    /**
     * Gets discount level.
     *
     * @return the discount level
     */
    public Integer getDiscountLevel() {
        return discountLevel;
    }

    /**
     * Sets discount level.
     *
     * @param discountLevel the discount level
     */
    public void setDiscountLevel(Integer discountLevel) {
        this.discountLevel = discountLevel;
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
        Client client = (Client) o;
        return Objects.equals(name, client.name) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(registerDateTime, client.registerDateTime) &&
                Objects.equals(discount, client.discount) &&
                Objects.equals(phone, client.phone) &&
                Objects.equals(cash, client.cash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, registerDateTime, discount, phone, cash);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", registerDateTime=").append(registerDateTime);
        sb.append(", discount=").append(discount);
        sb.append(", discountLevel=").append(discountLevel);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", cash=").append(cash);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", isActive=").append(isActive);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
