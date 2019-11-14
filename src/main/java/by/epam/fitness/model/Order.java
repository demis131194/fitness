package by.epam.fitness.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order extends AbstractBaseEntity {

    private Integer clientId;
    private String clientName;
    private String clientLastName;
    private Integer trainerId;
    private String trainerName;
    private String trainerLastName;
    private LocalDateTime registerDate;
    private String exercises;
    private String nutrition;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    private Boolean accept;
    private String clientComment;
    private OrderStatus orderStatus;
    private Boolean active = true;

    public Order() {
    }


    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getTrainerLastName() {
        return trainerLastName;
    }

    public void setTrainerLastName(String trainerLastName) {
        this.trainerLastName = trainerLastName;
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

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public String getClientComment() {
        return clientComment;
    }

    public void setClientComment(String clientComment) {
        this.clientComment = clientComment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
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
                Objects.equals(clientComment, order.clientComment) &&
                orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, accept, clientComment, orderStatus, active);
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
        sb.append(", userComment='").append(clientComment).append('\'');
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", active=").append(active);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
