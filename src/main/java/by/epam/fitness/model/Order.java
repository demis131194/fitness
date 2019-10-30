package by.epam.fitness.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order extends AbstractBaseEntity {

    private Integer clientId;
    private Integer trainerId;
    private LocalDateTime registerDate;
    private String exercises;
    private String nutrition;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    private boolean accept;
    private String userComment;
    private OrderStatus orderStatus;
    private boolean active = true;

    public Order() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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
        return accept == order.accept &&
                active == order.active &&
                Objects.equals(clientId, order.clientId) &&
                Objects.equals(trainerId, order.trainerId) &&
                Objects.equals(registerDate, order.registerDate) &&
                Objects.equals(exercises, order.exercises) &&
                Objects.equals(nutrition, order.nutrition) &&
                Objects.equals(startDate, order.startDate) &&
                Objects.equals(endDate, order.endDate) &&
                Objects.equals(price, order.price) &&
                Objects.equals(userComment, order.userComment) &&
                orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, accept, userComment, orderStatus, active);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("clientId=").append(clientId);
        sb.append(", trainerId=").append(trainerId);
        sb.append(", registerDate=").append(registerDate);
        sb.append(", exercises='").append(exercises).append('\'');
        sb.append(", nutrition='").append(nutrition).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", price=").append(price);
        sb.append(", accept=").append(accept);
        sb.append(", userComment='").append(userComment).append('\'');
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", active=").append(active);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
