package project.mediaplayer.model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.security.spec.ECField;

public class Player {
    private String path;
    private int playState;
    private long songDurationMs;

    public Player(String path, int playState) {
        this.path = path;
        this.playState = playState;
    }

    public Clip play() {
        try {
            File musicPath = new File(path);
            if (musicPath.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                songDurationMs = clip.getMicrosecondLength();
                switch (playState) {
                    case 1: {
                        clip.start();
                        break;
                    }
                    case 2: {
                        clip.stop();
                        break;
                    }
                    case 3:
                        break;
                }
                return clip;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


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

    // GETTER METHODS

    public int getPlayState() {
        return playState;
    }

    public long getSongDurationMs() {
        return songDurationMs;
    }

    /////////////////////////////


    // SETTER METHODS
    public void setPlayState(int playState) {
        this.playState = playState;
    }

}
