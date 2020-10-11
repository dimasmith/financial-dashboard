package net.anatolich.finance.cards.application;

import net.anatolich.finance.cards.domain.CardNumber;
import net.anatolich.finance.cards.domain.CreditCard;

public record CardInfo(CardNumber number, String alias) {

    public static CardInfo from(CreditCard card) {
        return new CardInfo(
            card.getCardNumber(),
            card.getAlias()
        );
    }

    public String readableNumber() {
        final var raw = number.getRawNumber();
        return String.join(" ",
            raw.substring(0, 4),
            raw.substring(4, 8),
            raw.substring(8, 12),
            raw.substring(12, 16)
        );
    }
}
