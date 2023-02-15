package project.mediaplayer.model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Player {
    private boolean isPlaying;
    private boolean isLoop;

    private boolean isShuffle;

    public Clip PlayMusic(String location) {
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
