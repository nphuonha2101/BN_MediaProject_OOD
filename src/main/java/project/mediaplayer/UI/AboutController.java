package project.mediaplayer.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.application.HostServices;
public class AboutController {

    @FXML
    private Hyperlink link;

    @FXML
    private Button okCloseButton;




    @FXML
    void closeStage(ActionEvent event) {
        Stage stage = (Stage) okCloseButton.getScene().getWindow();
        stage.close();
    }

}
