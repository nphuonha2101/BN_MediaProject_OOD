package project.mediaplayer.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.mediaplayer.model.MediaPlayerDemo;

import java.io.File;
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

    private final MediaPlayerDemo playlist = new MediaPlayerDemo();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MediaPlayerApplication.class.getResource("main-player-application.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 756);
        String basePath = new File("").getAbsolutePath();
        stage.setTitle("B&N Music Player");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}