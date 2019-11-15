package by.epam.fitness.model;

import java.math.BigDecimal;

public enum TrainingDuration {
    DAY(new BigDecimal(10), 1),
    WEEK(new BigDecimal(50), 7),
    MONTH(new BigDecimal(150), 30),
    THREE_MONTHS(new BigDecimal(320), 92),
    HALF_YEAR(new BigDecimal(499.99), 182);

    private BigDecimal price;
    private int durationDay;

    TrainingDuration(BigDecimal price, int durationDay) {
        this.price = price;
        this.durationDay = durationDay;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getDurationDay() {
        return durationDay;
    }

}
