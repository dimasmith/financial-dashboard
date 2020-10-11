package net.anatolich.finance.cards.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CardNumber {

    @Column(name = "card_number", length = 16)
    private String rawNumber;

    protected CardNumber() {
    }

    private CardNumber(String rawNumber) {
        this.rawNumber = rawNumber;
    }

    public static CardNumber from(String rawNumber) {
        return new CardNumber(rawNumber);
    }

    public String getRawNumber() {
        return rawNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CardNumber)) {
            return false;
        }
        final CardNumber that = (CardNumber) o;
        return rawNumber.equals(that.rawNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawNumber);
    }

    @Override
    public String toString() {
        return "CardNumber{" +
            "rawNumber='" + rawNumber + '\'' +
            '}';
    }
}
