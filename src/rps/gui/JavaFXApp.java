package rps.gui;

// Java imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.logging.Logger;


/**
 * JavaFX implementation of the RPS game
 *
 * @author smsj
 */
public class JavaFXApp extends Application {

    public static void launch() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Font customFont = Font.loadFont("view/font/Minecraftia.ttf", 12);
        Parent root = FXMLLoader.load(getClass().getResource("view/GameView.fxml"));
        //root.getStylesheets().add(getClass().getResource("/rps/gui/view/GameViewCSS.css").toExternalForm());
        stage.setTitle("Welcome to the not-implemented Rock-Paper-Scissor game!");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
