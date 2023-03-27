package project.mediaplayer.model;

import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.List;

public class PlayNextMedia implements MediaPlayerControl {
    @Override
    public void playerControl(MediaPlayerManagement mediaPlayerManagement) {
        ListView<String> songsListView = mediaPlayerManagement.getSongsListView();
        Slider volumeSlider = mediaPlayerManagement.getVolumeSlider();
        MediaPlayer mediaPlayer = mediaPlayerManagement.getMediaPlayer();
        MediaTimer mediaTimer = mediaPlayerManagement.getMediaTimer();
        Playlist playlist = mediaPlayerManagement.getPlaylist();
        List<File> songFiles = mediaPlayerManagement.getSongFiles();
        int songNumber = mediaPlayerManagement.getSongNumber();


        if (songNumber < songFiles.size() - 1)
            mediaPlayerManagement.setSongNumber(songNumber + 1);

        else
            mediaPlayerManagement.setSongNumber(0);
        mediaPlayer.stop();
        if (mediaTimer.getIsRunning())
            mediaTimer.cancelTimer();

        mediaPlayerManagement.prepareMedia();

        mediaPlayerManagement.setMediaPlayerControl(new PlayPauseMedia());
        mediaPlayerManagement.doActionControl();
    }
}
