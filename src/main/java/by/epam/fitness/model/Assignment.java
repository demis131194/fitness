package by.epam.fitness.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Assignment extends AbstractBaseEntity {

    private Integer orderId;
    private Integer clientId;
    private Integer trainerId;
    private LocalDateTime registerDate;
    private String exercises;
    private String nutrition;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    private Integer price;
    private Boolean accept;
    private String userComment;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getExercises() {
        return exercises;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return active == that.active &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(trainerId, that.trainerId) &&
                Objects.equals(registerDate, that.registerDate) &&
                Objects.equals(exercises, that.exercises) &&
                Objects.equals(nutrition, that.nutrition) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(price, that.price) &&
                Objects.equals(accept, that.accept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, active, price, accept);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Assignment{");
        sb.append("orderId=").append(orderId);
        sb.append(", registerDate=").append(registerDate);
        sb.append(", exercises='").append(exercises).append('\'');
        sb.append(", nutrition='").append(nutrition).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", active=").append(active);
        sb.append(", price=").append(price);
        sb.append(", accept=").append(accept);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}