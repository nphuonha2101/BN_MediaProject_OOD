package project.mediaplayer.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutController {

    @FXML
    private Hyperlink githubHL;

    @FXML
    private Hyperlink bangGithubHL;

    @FXML
    private Hyperlink nhaGithubHL;

    @FXML
    private Button okCloseButton;


    // close stage about when click ok button
    @FXML
    private void closeStage(ActionEvent event) {
        Stage stage = (Stage) okCloseButton.getScene().getWindow();
        stage.close();
    }

    // open link in hyperlink
    @FXML
    private void goToLink(ActionEvent event) throws URISyntaxException, IOException {


        // get hyperlink object clicked
        Hyperlink hyperlink = (Hyperlink) event.getSource();

        // get text of hyper link (url)
        String url = hyperlink.getText();
//        System.out.println(url);

        Desktop.getDesktop().browse(new URI(url));

    }

}
