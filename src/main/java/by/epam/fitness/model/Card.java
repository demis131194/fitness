package by.epam.fitness.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The type Card.
 */
public class Card {
    private String cardNumber;
    private BigDecimal cardAmount;

    /**
     * Gets card number.
     *
     * @return the card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets card number.
     *
     * @param cardNumber the card number
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets card amount.
     *
     * @return the card amount
     */
    public BigDecimal getCardAmount() {
        return cardAmount;
    }

    /**
     * Sets card amount.
     *
     * @param cardAmount the card amount
     */
    public void setCardAmount(BigDecimal cardAmount) {
        this.cardAmount = cardAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardNumber, card.cardNumber) &&
                Objects.equals(cardAmount, card.cardAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardAmount);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Card{");
        sb.append("cardNumber='").append(cardNumber).append('\'');
        sb.append(", cardAmount=").append(cardAmount);
        sb.append('}');
        return sb.toString();
    }
}
