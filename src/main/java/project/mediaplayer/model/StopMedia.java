package project.mediaplayer.model;

import javafx.scene.media.MediaPlayer;

public class StopMedia implements MediaPlayerControl {
    @Override
    public void playerControl(MediaPlayerManagement mediaPlayerManagement) {
        MediaTimer mediaTimer = mediaPlayerManagement.getMediaTimer();
        MediaPlayer mediaPlayer = mediaPlayerManagement.getMediaPlayer();

        if (mediaTimer.getTimer() != null)
            mediaTimer.cancelTimer();
        mediaPlayer.stop();
    }
}
