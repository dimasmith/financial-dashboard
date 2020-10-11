package net.anatolich.finance.cards.domain;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "credit_card", uniqueConstraints =
@UniqueConstraint(name = "uq_card_number", columnNames = {"card_number"})
)
public class CreditCard {

    @Id
    @Column(columnDefinition = "varchar(36)")
    private String id;
    @Column(length = 36)
    private String externalId;
    @Column(nullable = false)
    private CardNumber cardNumber;
    private String alias;

    protected CreditCard() {
    }

    private CreditCard(String externalId, CardNumber cardNumber, String alias) {
        this.id = UUID.randomUUID().toString();
        this.externalId = externalId;
        this.cardNumber = cardNumber;
        this.alias = alias;
    }

    public static CreditCard create(CardNumber cardNumber, String alias) {
        return new CreditCard(
            UUID.randomUUID().toString(),
            cardNumber,
            alias
        );
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreditCard)) {
            return false;
        }
        final CreditCard that = (CreditCard) o;
        return cardNumber.equals(that.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
            "id=" + id +
            ", externalId='" + externalId + '\'' +
            ", cardNumber=" + cardNumber +
            ", alias='" + alias + '\'' +
            '}';
    }
}
