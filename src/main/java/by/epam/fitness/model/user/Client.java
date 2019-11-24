package by.epam.fitness.model.user;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Client extends User {

    private String name;
    private String lastName;
    private LocalDateTime registerDateTime;
    private Integer discount;
    private Integer discountLevel;
    private String phone;
    private String mail;
    private BigDecimal cash;

    public Client() {
        role = UserRole.CLIENT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public Integer getDiscountLevel() {
        return discountLevel;
    }

    public void setDiscountLevel(Integer discountLevel) {
        this.discountLevel = discountLevel;
    }

    public String getMail() {
        return mail;
    }

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
