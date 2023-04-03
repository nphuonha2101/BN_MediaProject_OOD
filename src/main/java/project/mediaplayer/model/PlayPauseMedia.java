package project.mediaplayer.model;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.MediaPlayer;

public class PlayPauseMedia implements MediaPlayerControl {
    @Override
    public void playerControl(MediaPlayerManagement mediaPlayerManagement) {

        ToggleButton favoriteSongButton = mediaPlayerManagement.getFavoriteSongButton();
        Label songNameLabel = mediaPlayerManagement.getSongNameLabel();
        ListView<String> songsListView = mediaPlayerManagement.getSongsListView();
        Slider volumeSlider = mediaPlayerManagement.getVolumeSlider();
        MediaPlayer mediaPlayer = mediaPlayerManagement.getMediaPlayer();
        MediaTimer mediaTimer = mediaPlayerManagement.getMediaTimer();
        Playlist playlist = mediaPlayerManagement.getPlaylist();
        int songNumber = mediaPlayerManagement.getSongNumber();


        /* update state of favorite button for each time playing new song
         *  the value of method setSelected() is true or false
         *  and this value update from attribute isFavorite of songs
         */
        favoriteSongButton.setSelected(playlist.getSongList().get(songNumber).isFavorite());

        // open choose directory of music if current playlist have nothing
        songNameLabel.setText(playlist.getSongList().get(songNumber).getSongName());
        System.out.println("Song Number: " + songNumber);
        // focus to current song is playing in list view
        songsListView.getFocusModel().focus(songNumber);
        // scroll to current playing music on listView
//        songsListView.scrollTo(songNumber);

        // set volume for media player when choose a new song with value of volumeSlider
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        System.out.println(mediaPlayer.getVolume());

        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            mediaTimer.cancelTimer();

        } else {
            mediaPlayer.play();
            // begin timer for progress bar
            // start from 0 to total duration of song then stop
            mediaTimer.beginTimer();
        }

    }
}
