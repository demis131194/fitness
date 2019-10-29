package by.epam.fitness.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order extends AbstractBaseEntity {

    private int clientId;
    private int trainerId;
    private LocalDateTime registerDate;
    private String description;
    private boolean active = true;

    public Order() {
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(clientId, order.clientId) &&
                Objects.equals(trainerId, order.trainerId) &&
                Objects.equals(registerDate, order.registerDate) &&
                Objects.equals(description, order.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, trainerId, registerDate, description);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("userId=").append(clientId);
        sb.append(", trainerId=").append(trainerId);
        sb.append(", description='").append(description).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
