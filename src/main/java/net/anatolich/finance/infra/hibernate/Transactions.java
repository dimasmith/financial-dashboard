package net.anatolich.finance.infra.hibernate;

import java.util.function.Supplier;
import org.hibernate.SessionFactory;

public class Transactions {

    private static Transactions transactions;
    private final SessionFactory sessionFactory;

    public Transactions(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static <T> T transactionWithResult(Supplier<T> operation) {
        return transactions.inTransactionWithResult(operation);
    }

    public static void transaction(Supplier<Void> operation) {
        transactions.inTransaction(operation);
    }

    static void initialize(Transactions transactions) {
        Transactions.transactions = transactions;
    }

    public <T> T inTransactionWithResult(Supplier<T> operation) {
        try (var session = sessionFactory.getCurrentSession()) {
            final var transaction = session.getTransaction();
            transaction.begin();
            final var result = operation.get();
            transaction.commit();
            return result;
        }
    }

    public void inTransaction(Supplier<Void> operation) {
        try (var session = sessionFactory.getCurrentSession()) {
            final var transaction = session.getTransaction();
            transaction.begin();
            operation.get();
            transaction.commit();
        }
    }
}
