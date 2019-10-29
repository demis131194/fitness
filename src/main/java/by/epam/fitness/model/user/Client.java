package by.epam.fitness.model.user;

import by.epam.fitness.model.AbstractBaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Client extends AbstractBaseEntity {

    private String name;
    private String lastName;
    private LocalDateTime registerDateTime;
    private Integer trainerId;
    private boolean isActive;
    private Integer discount;
    private String phone;

    public Client() {
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

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return isActive == client.isActive &&
                Objects.equals(name, client.name) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(registerDateTime, client.registerDateTime) &&
                Objects.equals(trainerId, client.trainerId) &&
                Objects.equals(discount, client.discount) &&
                Objects.equals(phone, client.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, registerDateTime, discount, phone);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", registerDateTime=").append(registerDateTime);
        sb.append(", trainerId=").append(trainerId);
        sb.append(", isActive=").append(isActive);
        sb.append(", discount=").append(discount);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
