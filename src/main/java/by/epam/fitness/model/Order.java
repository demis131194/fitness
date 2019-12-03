package by.epam.fitness.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Order.
 */
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
    private String clientComment;
    private OrderStatus orderStatus;
    private Boolean active = true;

    /**
     * Instantiates a new Order.
     */
    public Order() {
    }


    /**
     * Gets client id.
     *
     * @return the client id
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets client name.
     *
     * @return the client name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets client name.
     *
     * @param clientName the client name
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Gets client last name.
     *
     * @return the client last name
     */
    public String getClientLastName() {
        return clientLastName;
    }

    /**
     * Sets client last name.
     *
     * @param clientLastName the client last name
     */
    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    /**
     * Gets trainer id.
     *
     * @return the trainer id
     */
    public Integer getTrainerId() {
        return trainerId;
    }

    /**
     * Sets trainer id.
     *
     * @param trainerId the trainer id
     */
    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    /**
     * Gets trainer name.
     *
     * @return the trainer name
     */
    public String getTrainerName() {
        return trainerName;
    }

    /**
     * Sets trainer name.
     *
     * @param trainerName the trainer name
     */
    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    /**
     * Gets trainer last name.
     *
     * @return the trainer last name
     */
    public String getTrainerLastName() {
        return trainerLastName;
    }

    /**
     * Sets trainer last name.
     *
     * @param trainerLastName the trainer last name
     */
    public void setTrainerLastName(String trainerLastName) {
        this.trainerLastName = trainerLastName;
    }

    /**
     * Gets register date.
     *
     * @return the register date
     */
    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    /**
     * Sets register date.
     *
     * @param registerDate the register date
     */
    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * Gets exercises.
     *
     * @return the exercises
     */
    public String getExercises() {
        return exercises;
    }

    /**
     * Sets exercises.
     *
     * @param exercises the exercises
     */
    public void setExercises(String exercises) {
        this.exercises = exercises;
    }

    /**
     * Gets nutrition.
     *
     * @return the nutrition
     */
    public String getNutrition() {
        return nutrition;
    }

    /**
     * Sets nutrition.
     *
     * @param nutrition the nutrition
     */
    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets client comment.
     *
     * @return the client comment
     */
    public String getClientComment() {
        return clientComment;
    }

    /**
     * Sets client comment.
     *
     * @param clientComment the client comment
     */
    public void setClientComment(String clientComment) {
        this.clientComment = clientComment;
    }

    /**
     * Gets order status.
     *
     * @return the order status
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets order status.
     *
     * @param orderStatus the order status
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Gets active.
     *
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return  active == order.active &&
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
        return Objects.hash(clientId, trainerId, registerDate, exercises, nutrition, startDate, endDate, price, clientComment, orderStatus, active);
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
        sb.append(", userComment='").append(clientComment).append('\'');
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", active=").append(active);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
