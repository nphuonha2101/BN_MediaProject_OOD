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
import project.mediaplayer.model.MediaPlayerDemo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


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

    private MediaPlayerDemo playlist = new MediaPlayerDemo();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MediaPlayerApplication.class.getResource("main-player-application.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 756);
        String basePath = new File("").getAbsolutePath();
//        basePath = basePath.replace("\\", "/") + "/src/main/resources/img/logo_title.png";

//        playlist.playListMusic("src/main/resources/music");

//        stage.getIcons().add(new Image(basePath));
        stage.setTitle("B&N Music Player");
        stage.setScene(scene);
        stage.show();
    }

//    public void playMedia() {
//        Media media = new Media("file:///D:/Music/AiChoAi.mp3");
//        MediaPlayer player = new MediaPlayer(media);
//        player.play();
//        player.setVolume(1.0);
//        System.out.println(player.getVolume());
//    }


//    public class MusicList {
//        public static ArrayList<String> getMusicList(String folderPath) {
//            ArrayList<String> musicList = new ArrayList<>();
//            File folder = new File(folderPath);
//            File[] files = folder.listFiles();
//            for (File file : files) {
//                if (file.isFile()) {
//                    String fileName = file.getName();
//                    String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
//                    if (fileExtension.equals("mp3") || fileExtension.equals("wav")) {
//                        String filePath = file.getAbsolutePath();
//                        musicList.add(filePath);
//                    }
//                }
//            }
//            return musicList;
//        }
//    }
//    public static void playListMusic(ArrayList<String> musicList) {
//        File musicFolder = new File("src/main/resources/music");
//        File[] musicFiles = musicFolder.listFiles();
//        ArrayList<Media> mediaList = new ArrayList<>();
//
//        for (File musicFile : musicFiles) {
//            Media media = new Media(musicFile.toURI().toString());
//            mediaList.add(media);
//        }
//
//        for (Media media : mediaList) {
//            MediaPlayer mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.play();
//        }
//
//        // if you want to add the music to a playlist
//
//    }

//    public static void playListMusic(ArrayList<String> musicList) {
//        // method code goes here
//        File musicFolder = new File("src/main/resources/music");
//        File[] musicFiles = musicFolder.listFiles();
//        ArrayList<Media> mediaList = new ArrayList<>();
//        for (File musicFile : musicFiles) {
//            Media media = new Media(musicFile.toURI().toString());
//            mediaList.add(media);
//        }
//        int count = 0;
//        while (count < mediaList.size()) {
//            Media media = mediaList.get(count);
//            MediaPlayer mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.play();
//            if (mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED) {
//                count++;
//            }
//        }
//    }



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
//        ArrayList<String> musicList = MusicList.getMusicList("src/main/resources/music");
//        playListMusic(musicList);
        launch(args);
    }

}