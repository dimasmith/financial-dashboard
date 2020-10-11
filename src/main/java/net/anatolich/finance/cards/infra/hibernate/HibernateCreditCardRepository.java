package net.anatolich.finance.cards.infra.hibernate;

import java.util.List;
import net.anatolich.finance.cards.domain.CreditCard;
import net.anatolich.finance.cards.domain.CreditCardRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateCreditCardRepository implements CreditCardRepository {

    private final SessionFactory sessionFactory;

    public HibernateCreditCardRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CreditCard> findAll() {
        final var session = sessionFactory.getCurrentSession();
        return session
            .createQuery("select c from CreditCard  c", CreditCard.class)
            .getResultList();
    }

    @Override
    public CreditCard save(CreditCard card) {
        final var session = sessionFactory.getCurrentSession();
        session.save(card);
        return card;
    }
}

