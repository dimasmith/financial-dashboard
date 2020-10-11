package net.anatolich.finance.cards.application;

import java.util.List;
import java.util.stream.Collectors;
import net.anatolich.finance.cards.domain.CardNumber;
import net.anatolich.finance.cards.domain.CreditCard;
import net.anatolich.finance.cards.domain.CreditCardRepository;
import net.anatolich.finance.infra.hibernate.Transactions;

public class CardManagementService {

    private final CreditCardRepository cardRepository;

    protected CardManagementService() {
        cardRepository = null;
    }

    public CardManagementService(CreditCardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<CardInfo> displayCards() {
        return Transactions.transactionWithResult(() -> cardRepository
            .findAll().stream()
            .map(CardInfo::from)
            .collect(Collectors.toList()));
    }

    public void registerCard(String alias, String number) {
        CreditCard card = CreditCard.create(
            CardNumber.from(number),
            alias
        );
        Transactions.transactionWithResult(() -> cardRepository.save(card));
    }
}
