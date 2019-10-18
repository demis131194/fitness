package by.epam.fitness.model;

import java.time.LocalDate;

public class Order extends AbstractBaseEntity {

    private Integer userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
    private boolean isPayed;
    private String description;

    public Order(Integer id, Integer userId, LocalDate startDate, LocalDate endDate, int price, boolean isPayed, String description) {
        super(id);
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.isPayed = isPayed;
        this.description = description;
    }

    public Order(Integer userId, LocalDate startDate, LocalDate endDate, int price, boolean isPayed, String description) {
        this(null, userId, startDate, endDate, price, isPayed, description);
    }
}
