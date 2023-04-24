package project.mediaplayer.model;

import javafx.util.Duration;

/**
 * This class is a Concrete Strategy Class of Class {@link MediaPlayerManagement}
 * <p>
 * The purpose of this class is to Reset Media (re-playing the current song)
 */

public class ConcreteStrategyResetMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {

        // set song progress to 0
        mediaPlayerManagement.setMediaTimerSongProgress(0);

        // set duration of media is to 0 then play it
        mediaPlayerManagement.getMediaPlayer().seek(Duration.seconds(0));

    }
}
