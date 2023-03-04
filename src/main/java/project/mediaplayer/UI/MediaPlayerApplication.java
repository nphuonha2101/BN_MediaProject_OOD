package project.mediaplayer.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import project.mediaplayer.model.MediaPlayerDemo;

import java.io.File;
import java.io.IOException;


public class MediaPlayerApplication extends Application {

    private final MediaPlayerDemo playlist = new MediaPlayerDemo();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MediaPlayerApplication.class.getResource("main-player-application.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 756);
        String basePath = new File("").getAbsolutePath();

        // get current project path
        File currentProjectDirectory = new File("");
        // get image icon path
        String applicationIconImagePath = currentProjectDirectory.getAbsolutePath() + "\\src\\main\\resources\\img\\logo.png";
        // get image icon for title bar and icon to display on taskbar
        System.out.println(applicationIconImagePath);

//        System.out.println(applicationIconFile);
        stage.getIcons().add(new Image(applicationIconImagePath));
        stage.setTitle("B&N Music Player");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}