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

/**
 * This is a Controller of About Application to handle event of About View
 */
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

    /**
     * Handles the action event when a hyperlink is clicked. Opens the link in the system's default browser.
     *
     * @param event The action event triggered by the hyperlink click.
     * @throws URISyntaxException If the URI syntax is invalid.
     * @throws IOException        If an I/O error occurs.
     */
    @FXML
    private void goToLink(ActionEvent event) throws URISyntaxException, IOException {

        // get hyperlink object clicked
        Hyperlink hyperlink = (Hyperlink) event.getSource();

        // get text of hyper link (url)
        String url = hyperlink.getText();

        // use java.awt.Desktop to open the link in system default browser
        Desktop.getDesktop().browse(new URI(url));

    }

    /**
     * Initializes the About section of the B&N Media Player UI.
     *
     * @param url            The URL of the FXML file for the About section.
     * @param resourceBundle The resource bundle for the About section.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create Text objects for software name and description
        Text softwareName = new Text("B&N Media Player Release 2023\n\n");
        Text softwareDescription = new Text("This program was written by Pham Cao Bang and Nguyen Phuong Nha for Object Orientation Design subject. Technicals have been used for this project included: \n" +
                "\t- JavaFX (Scene Builder) for building UI\n" +
                "\t- FXML for JavaFX to hierarchy the components of the UI and handle event of its\n" +
                "\t- Handling music file (*.mp3, *.aac, *.wav) with javafx.scene.media.Media package");

        // get default system font name
        String defaultFontName = Font.getDefault().getName();
        // Set font and size for software name and description
        softwareName.setFont(Font.font(defaultFontName, FontWeight.BOLD, 20));
        softwareDescription.setFont(Font.font(defaultFontName, 15));
        // Set text color for software name and description
        softwareName.setFill(Paint.valueOf("#fefefe"));
        softwareDescription.setFill(Paint.valueOf("#fefefe"));
        // Add software name and description to text flow container
        textFlowAbout.getChildren().add(softwareName);
        textFlowAbout.getChildren().add(softwareDescription);

    }


}
