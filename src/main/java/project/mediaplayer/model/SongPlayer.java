package project.mediaplayer.model;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;


public class SongPlayer {



    public static void main(String[] args) {
        File file = new File("/Users/bangpham/Documents/Yasuo hasagi/BN_MediaProject_OOD/src/main/resources/music/Đường một chiều.mp3");

    }

    private String path;
    private int playState;
    private boolean isComplete;
//    private long songDurationMs;


    // Constant for Playstate
    public final int PLAYING = 1;
    public final int STOP = 2;

    public SongPlayer(String path, int playState) {
        this.path = path;
        this.playState = playState;
    }

//
//
//    public void playMusic(String path) {
//        File file = new File(path);
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // GETTER METHODS

    public int getPlayState() {
        return playState;
    }


    /////////////////////////////

    // SETTER METHODS
    public void setPlayState(int playState) {
        this.playState = playState;
    }


    public void play() {
        File file = new File(path);

        // catch file input stream exception
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);

            // catch the player exception
            try {
                Player player = new Player(bis);

                // switch play state -> play
                switch (playState) {
                    case 1:
                        player.play();
                        break;
                    case 2:
                        player.close();
                        break;
                    case 3:
                        break;
                }

                isComplete = player.isComplete();

            } catch (JavaLayerException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

//    public void play() {
//    }

//    public Clip play() {
//        try {
//            File musicPath = new File(path);
//            if (musicPath.exists()) {
//                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioInputStream);
//                songDurationMs = clip.getMicrosecondLength();
//                switch (playState) {
//                    case 1: {
//                        clip.start();
//                        break;
//                    }
//                    case 2: {
//                        clip.stop();
//                        break;
//                    }
//                    case 3:
//                        break;
//                }
//                return clip;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


//    public Clip PlayMusic(String location) {
//        try {
//
//            File musicPath = new File(location);
//                clip.start();
//                return clip;
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        return null;
//    }


}
