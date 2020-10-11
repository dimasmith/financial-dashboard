package net.anatolich.finance;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class DashboardApplication extends Application {

    @Override
    public void start(Stage stage) {
        final Scene scene = new Scene(new VBox());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
