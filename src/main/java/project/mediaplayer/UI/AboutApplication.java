package project.mediaplayer.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class AboutApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AboutApplication.class.getResource("about.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("About B&N Media Player");
        stage.setScene(scene);
        stage.setResizable(false);

    stage.getIcons().add(new Image("https://drive.google.com/file/d/1U2aCvnbDFSdJX3eng9e2X0kx0pNwsioh/view?usp=sharing"));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
