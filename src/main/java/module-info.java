module net.anatolich.finance {
    requires javafx.controls;
    requires javafx.fxml;

    opens net.anatolich.finance to javafx.fxml;
    exports net.anatolich.finance;
}