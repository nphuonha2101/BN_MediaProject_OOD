package project.mediaplayer.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MediaPlayerController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}