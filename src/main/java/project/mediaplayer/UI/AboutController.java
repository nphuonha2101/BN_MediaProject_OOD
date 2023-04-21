package project.mediaplayer.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {

    @FXML
    private Button okCloseButton;
    @FXML
    private TextFlow textFlowAbout;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Text softwareName = new Text("B&N Media Player Release 2023\n\n");
        Text softwareDescription = new Text("This program was written by Pham Cao Bang and Nguyen Phuong Nha for Object Orientation Design subject. Technicals have been used for this project included: \n" +
                "\t- JavaFX (Scene Builder) for building UI\n" +
                "\t- FXML for JavaFX to hierarchy the components of the UI and handle event of its\n" +
                "\t- Handling music file (*.mp3, *.aac, *.wav) with javafx.scene.media.Media package");

        // get default system font name
        String defaultFontName = Font.getDefault().getName();
        softwareName.setFont(Font.font(defaultFontName, FontWeight.BOLD, 20));
        softwareDescription.setFont(Font.font(defaultFontName, 15));

        softwareName.setFill(Paint.valueOf("#fefefe"));
        softwareDescription.setFill(Paint.valueOf("#fefefe"));

        textFlowAbout.getChildren().add(softwareName);
        textFlowAbout.getChildren().add(softwareDescription);

    }
}
