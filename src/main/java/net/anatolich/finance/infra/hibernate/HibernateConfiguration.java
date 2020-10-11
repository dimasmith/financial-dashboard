package net.anatolich.finance.infra.hibernate;

import net.anatolich.finance.cards.domain.CreditCard;
import org.apache.commons.configuration2.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateConfiguration.class);
    private final Configuration configuration;
    private SessionFactory sessionFactory;

    public HibernateConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void boostrap() {
        LOGGER.info("initializing hibernate");
        final var bootstrapRegistry = new BootstrapServiceRegistryBuilder()
            .build();
        final var serviceRegistry = new StandardServiceRegistryBuilder(bootstrapRegistry)
            .applySetting(AvailableSettings.URL, configuration.getString("database.url"))
            .applySetting(AvailableSettings.USER, configuration.getString("database.username"))
            .applySetting(AvailableSettings.PASS, configuration.getString("database.password"))
            .applySetting(AvailableSettings.HBM2DDL_AUTO, "validate")
            .applySetting(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, ThreadLocalSessionContext.class.getName())
            .build();
        final var metadata = new MetadataSources(serviceRegistry)
            .addAnnotatedClass(CreditCard.class)
            .buildMetadata();
        sessionFactory = metadata.getSessionFactoryBuilder()
            .build();
        Transactions.initialize(transactions());
    }

    public SessionFactory sessionFactory() {
        return sessionFactory;
    }

    public Transactions transactions() {
        return new Transactions(sessionFactory());
    }
}
