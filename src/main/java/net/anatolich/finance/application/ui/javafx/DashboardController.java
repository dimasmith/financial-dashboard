package net.anatolich.finance.application.ui.javafx;

import java.io.IOException;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import net.anatolich.finance.cards.application.CardInfo;
import net.anatolich.finance.cards.infra.ui.CardController;

public class DashboardController {

    @FXML
    private TilePane cards;

    @FXML
    public void quit() {
        Platform.exit();
    }

    public void displayCards(List<CardInfo> displayCards) {
        for (CardInfo cardInfo : displayCards) {
            final var loader = new FXMLLoader(CardController.class.getResource("card.fxml"));
            try {
                Node elementRoot = loader.load();
                CardController controller = loader.getController();
                controller.setCardInfo(cardInfo);
                cards.getChildren().add(elementRoot);
            } catch (IOException e) {
                throw new IllegalStateException("component loading failure", e);
            }

        }

    }
}
