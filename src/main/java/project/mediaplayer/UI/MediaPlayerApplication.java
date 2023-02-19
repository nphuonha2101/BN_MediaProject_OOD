package project.mediaplayer.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.media.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


//        AudioClip audioClip = new AudioClip("D:/Music/AiChoAi.mp3");
//        audioClip.play();
////        mediaPlayer.play();
//
//        String source = "D:/Music/AiChoAi.mp3" ;
//        Media media;
//
//        MediaView mediaView;
//        try {
//            media = new Media(source);
//            if (media.getError() == null) {
//                media.setOnError(new Runnable() {
//                    public void run() {
//                        // Handle asynchronous error in Media object.
//                    }
//                });
//                try {
//                    mediaPlayer = new MediaPlayer(media);
//                        mediaPlayer.play();
//                        System.out.println(mediaPlayer.getVolume());
//                    if (mediaPlayer.getError() == null) {
//                        mediaPlayer.setOnError(new Runnable() {
//                            public void run() {
//                                // Handle asynchronous error in MediaPlayer object.
//                            }
//                        });
//                        mediaView = new MediaView(mediaPlayer);
//                        mediaView.setOnError(new EventHandler() {
//                            /**
//                             * @param event
//                             */
//                            @Override
//                            public void handle(Event event) {
//
//                            }
//
//                            public void handle(MediaErrorEvent t) {
//                                // Handle asynchronous error in MediaView.
//                            }
//                        });
//                    } else {
//                        // Handle synchronous error creating MediaPlayer.
//                    }
//                } catch (Exception mediaPlayerException) {
//                    // Handle exception in MediaPlayer constructor.
//                }
//            } else {
//                // Handle synchronous error creating Media.
//            }
//        } catch (Exception mediaException) {
//            // Handle exception in Media constructor.
//        }
//
//
public class MediaPlayerApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MediaPlayerApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 756);
        String basePath = new File("").getAbsolutePath();
//        basePath = basePath.replace("\\", "/") + "/src/main/resources/img/logo_title.png";

//        stage.getIcons().add(new Image(basePath));
        stage.setTitle("B&N Music Player");
        stage.setScene(scene);
        stage.show();
    }

    public void playMedia() {
        Media media = new Media("file:///D:/Music/AiChoAi.mp3");
        MediaPlayer player = new MediaPlayer(media);
        player.play();
        player.setVolume(1.0);
        System.out.println(player.getVolume());
    }

    public static void main(String[] args) {
//        MediaPlayerApplication mediaPlayerApplication = new MediaPlayerApplication();
//
//        mediaPlayerApplication.playMedia();
//        File file = new File("D:/Music/24H - LyLy, Magazine - Bài hát, lyrics.mp3");
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            BufferedInputStream bis = new BufferedInputStream(fileInputStream);
//            try {
//                Player player = new Player(bis);
//                player.play();
//                System.out.println(player.isComplete());
//            } catch (JavaLayerException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
        launch();


    }

}