package project.mediaplayer.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class MediaPlayerApplication extends Application {
    public static final String APPLICATION_ICON_PATH = "src" + File.separator + "main" + File.separator + "resources"
            + File.separator + "img" + File.separator + "application-logo-icon.png";

    /**
     * Initializes and starts the main player application stage by loading the FXML file, setting the scene
     * Displaying the stage with the specified minimum width and height.
     * Sets the title and icon of the stage.
     *
     * @param stage The primary stage of the application.
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // create FXMLLoader from FXML file of this scene
        FXMLLoader fxmlLoader = new FXMLLoader(MediaPlayerApplication.class.getResource("main-player-application.fxml"));

        // load FXML file form FXMLLoader
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        File imgIconFile = new File(APPLICATION_ICON_PATH);

        stage.getIcons().add(new Image(imgIconFile.toURI().toString()));

        stage.setMinWidth(650);
        stage.setMinHeight(500);

        stage.setTitle("B&N Music Player");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}