package project.mediaplayer.model;

import javafx.scene.media.MediaPlayer;

public class ConcreteStrategyStopMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {
        MediaTimer mediaTimer = mediaPlayerManagement.getMediaTimer();
        MediaPlayer mediaPlayer = mediaPlayerManagement.getMediaPlayer();

        if (mediaTimer.getTimer() != null)
            mediaTimer.cancelTimer();
        mediaPlayer.stop();
    }
}
