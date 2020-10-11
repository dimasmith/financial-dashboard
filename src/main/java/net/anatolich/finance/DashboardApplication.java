package net.anatolich.finance;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.anatolich.finance.cards.application.CardManagementService;
import net.anatolich.finance.cards.domain.CreditCardRepository;
import net.anatolich.finance.cards.infra.hibernate.HibernateCreditCardRepository;
import net.anatolich.finance.infra.configuration.ExternalizedConfiguration;
import net.anatolich.finance.infra.flyway.FlywayConfiguration;
import net.anatolich.finance.infra.hibernate.HibernateConfiguration;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JavaFX App
 */
public class DashboardApplication extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardApplication.class);
    private CardManagementService cardManagementService;

    @Override
    public void init() {
        LOGGER.info("initializing Dashboard application");
        final var externalizedConfiguration = new ExternalizedConfiguration();
        final var flywayConfiguration = new FlywayConfiguration(externalizedConfiguration.configuration());
        flywayConfiguration.runMigrations();
        final var hibernateConfiguration = new HibernateConfiguration(externalizedConfiguration.configuration());
        hibernateConfiguration.boostrap();
        final SessionFactory sessionFactory = hibernateConfiguration.sessionFactory();
        final CreditCardRepository creditCardRepository = new HibernateCreditCardRepository(sessionFactory);
        cardManagementService = new CardManagementService(creditCardRepository);
    }

    @Override
    public void start(Stage stage) {
        final var layout = new VBox();
        final Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        cardManagementService.displayCards()
            .forEach(card -> {
                layout.getChildren().add(
                    new Label(card.alias()));
                layout.getChildren().add(
                    new Label(card.readableNumber()));
            });
    }

    public static void main(String[] args) {
        launch();
    }

}
