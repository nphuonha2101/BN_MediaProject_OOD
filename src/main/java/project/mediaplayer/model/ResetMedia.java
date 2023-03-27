package project.mediaplayer.model;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.List;

public class ResetMedia implements MediaPlayerControl {
    @Override
    public void playerControl(MediaPlayerManagement mediaPlayerManagement) {
        ProgressBar songProgressBar = mediaPlayerManagement.getSongProgressBar();
        Label songNameLabel = mediaPlayerManagement.getSongNameLabel();
        MediaPlayer mediaPlayer = mediaPlayerManagement.getMediaPlayer();
        int songNumber = mediaPlayerManagement.getSongNumber();
        List<File> songFiles = mediaPlayerManagement.getSongFiles();

        songProgressBar.setProgress(0);
        songNameLabel.setText(songFiles.get(songNumber).getName());
        mediaPlayer.seek(Duration.seconds(0));
    }
}
