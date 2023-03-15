package project.mediaplayer.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MediaPlayerApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        // create FXMLLoader from FXML file of this scene
        FXMLLoader fxmlLoader = new FXMLLoader(MediaPlayerApplication.class.getResource("main-player-application.fxml"));

        // load FXML file form FXMLLoader
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);


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