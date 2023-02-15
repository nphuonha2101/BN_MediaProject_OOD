package project.mediaplayer.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MediaPlayerApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MediaPlayerApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 756);
        String basePath = new File("").getCanonicalPath();
        basePath = basePath.replace("\\", "/") + "/src/main/resources/img/logo_title.png";
        stage.setTitle("B&N Media Player");
//        stage.getIcons().add(new Image(basePath));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        List<String> musicToPlay = new ArrayList<String>();
        musicToPlay.add("Đường một chiều.wav");
        musicToPlay.add("test.wav");

        try {
            for (int i = 0; i < musicToPlay.size(); i++) {
                System.out.println("Playing: " + musicToPlay.get(i));
                Clip currentClip = PlayMusic(musicToPlay.get(i));
                while (currentClip.getMicrosecondLength() != currentClip.getMicrosecondPosition()) {

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static Clip PlayMusic(String location) {
        try {
            File musicPath = new File(location);
            if (musicPath.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                return clip;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}