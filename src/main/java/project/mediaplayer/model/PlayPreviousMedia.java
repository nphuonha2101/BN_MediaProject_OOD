package project.mediaplayer.model;

import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.List;

public class PlayPreviousMedia implements MediaPlayerControl {
    @Override
    public void playerControl(MediaPlayerManagement mediaPlayerManagement) {

        MediaPlayer mediaPlayer = mediaPlayerManagement.getMediaPlayer();
        MediaTimer mediaTimer = mediaPlayerManagement.getMediaTimer();
        Playlist playlist = mediaPlayerManagement.getPlaylist();
        List<File> songFiles = mediaPlayerManagement.getSongFiles();
        int songNumber = mediaPlayerManagement.getSongNumber();


        // Check if the current songNumber is greater than zero (not the first media file)
        if (songNumber > 0)
            // Decrease the songNumber to go to the previous media file.
            mediaPlayerManagement.setSongNumber(songNumber - 1);
        else
            // If the current media file is the first one, go to the last media file.
            mediaPlayerManagement.setSongNumber(songFiles.size() - 1);

        // If the timer is running, cancel it.
        if (mediaTimer.getIsRunning())
            mediaTimer.cancelTimer();

        mediaPlayerManagement.prepareMedia();
        // Play the new media file.
        mediaPlayerManagement.setMediaPlayerControl(new PlayPauseMedia());
        mediaPlayerManagement.doActionControl();
    }
}
