package project.mediaplayer.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This is an About Application to show About stage
 * when click About button on {@link MediaPlayerController}
 */
public class AboutStage extends Application {

    /**
     * Starts the JavaFX application by loading the "about.fxml" file and displaying it on a new stage.
     *
     * @param stage The primary stage for this application.
     * @throws Exception If an exception occurs during the loading of the FXML file.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AboutStage.class.getResource("about.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("About B&N Media Player");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();

    }
}
