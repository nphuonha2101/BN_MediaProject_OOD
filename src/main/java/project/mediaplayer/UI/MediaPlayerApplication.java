package project.mediaplayer.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class MediaPlayerApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MediaPlayerApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 756);
//        Image MediaPlayerApplication;
//        stage.getIcons().add(new Image(MediaPlayerApplication.class.getResourceAsStream("/path/to/logo.png")));
        stage.getIcons().add(new Image("https://drive.google.com/file/d/1A0jNwsp4SidYqEQoTT-88xCO5JZ5EZjb/view?usp=share_link"));
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}