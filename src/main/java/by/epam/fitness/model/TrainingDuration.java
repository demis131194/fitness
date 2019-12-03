package by.epam.fitness.model;

import java.math.BigDecimal;

/**
 * The enum Training duration.
 */
public enum TrainingDuration {
    /**
     * The Day.
     */
    DAY(new BigDecimal(10), 1),
    /**
     * The Week.
     */
    WEEK(new BigDecimal(50), 7),
    /**
     * The Month.
     */
    MONTH(new BigDecimal(150), 30),
    /**
     * The Three months.
     */
    THREE_MONTHS(new BigDecimal(320), 92),
    /**
     * The Half year.
     */
    HALF_YEAR(new BigDecimal(499.99), 182);

    private BigDecimal price;
    private int durationDay;

    TrainingDuration(BigDecimal price, int durationDay) {
        this.price = price;
        this.durationDay = durationDay;
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
     * Gets duration day.
     *
     * @return the duration day
     */
    public int getDurationDay() {
        return durationDay;
    }

}
