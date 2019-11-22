package by.epam.fitness.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Card {
    private String cardNumber;
    private BigDecimal cardAmount;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getCardAmount() {
        return cardAmount;
    }

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
