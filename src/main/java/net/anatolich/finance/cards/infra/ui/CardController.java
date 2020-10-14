package net.anatolich.finance.cards.infra.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import net.anatolich.finance.cards.application.CardInfo;

public class CardController {

    @FXML
    private Label number;
    @FXML
    private Label holder;

    public void setCardInfo(CardInfo cardInfo) {
        number.setText(cardInfo.readableNumber());
        holder.setText(cardInfo.alias());
    }
}
