package by.epam.fitness.model;

import java.time.LocalDate;

public class Order extends AbstractBaseEntity {

    private int userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
    private boolean isPayed;
    private String description;

    public Order(Integer id, int userId, LocalDate startDate, LocalDate endDate, int price, boolean isPayed, String description) {
        super(id);
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.isPayed = isPayed;
        this.description = description;
    }

    public Order(Integer id, int userId, LocalDate startDate, LocalDate endDate, int price, String description) {
        this(id, userId, startDate, endDate, price, false, description);
    }

    public Order(int userId, LocalDate startDate, LocalDate endDate, int price, String description) {
        this(null, userId, startDate, endDate, price, false, description);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", price=").append(price);
        sb.append(", isPayed=").append(isPayed);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
