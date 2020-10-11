package net.anatolich.finance.cards.domain;

import java.util.List;

public interface CreditCardRepository {

    List<CreditCard> findAll();

    CreditCard save(CreditCard card);
}
