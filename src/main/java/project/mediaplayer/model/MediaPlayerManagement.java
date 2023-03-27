package project.mediaplayer.model;

import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MediaPlayerManagement {

    private final Playlist playlist;

    private final ListView<String> songsListView;
    private final List<File> songFiles = new ArrayList<>();
    private Media media;
    private MediaPlayer mediaPlayer;
    private final ToggleButton shuffleMediaButton;
    private final Label songNameLabel;
    private final ToggleButton favoriteSongButton;
    private MediaPlayerControl mediaPlayerControl;
    private int songNumber = 0;
    private MediaTimer mediaTimer;
    private final ProgressBar songProgressBar;
    private final Slider volumeSlider;

    public MediaPlayerManagement(Playlist playlist, ListView<String> songsListView, Label songNameLabel, ToggleButton favoriteSongButton, ToggleButton shuffleMediaButton, ProgressBar songProgressBar, Slider volumeSlider) {
        this.playlist = playlist;
        this.songsListView = songsListView;
        this.songNameLabel = songNameLabel;
        this.favoriteSongButton = favoriteSongButton;
        this.shuffleMediaButton = shuffleMediaButton;
        this.songProgressBar = songProgressBar;
        this.volumeSlider = volumeSlider;
    }

    public int getSongNumber() {
        return this.songNumber;
    }

    public void setSongNumber(int songNumber) {
        this.songNumber = songNumber;
    }

    public double getVolume() {
        return this.mediaPlayer.getVolume();
    }

    public void setVolume(double volume) {
        this.mediaPlayer.setVolume(volume);
    }

    public void setMediaPlayerControl(MediaPlayerControl mediaPlayerControl) {
        this.mediaPlayerControl = mediaPlayerControl;
    }

    public Playlist getPlaylist() {
        return this.playlist;
    }

    public Label getSongNameLabel() {
        return this.songNameLabel;
    }

    public ToggleButton getFavoriteSongButton() {
        return this.favoriteSongButton;
    }

    public ToggleButton getShuffleMediaButton() {
        return this.shuffleMediaButton;
    }

    public ProgressBar getSongProgressBar() {
        return this.songProgressBar;
    }

    public Slider getVolumeSlider() {
        return this.volumeSlider;
    }

    public ListView<String> getSongsListView() {
        return this.songsListView;
    }

    public List<File> getSongFiles() {
        return this.songFiles;
    }

    public void doActionControl() {
        this.mediaPlayerControl.playerControl(this);
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public MediaTimer getMediaTimer() {
        return this.mediaTimer;
    }

    /**
     * preparing for media player before play
     */
    public void prepareMedia() {

        // if old media player is playing then stop to avoid conflict
        if (mediaPlayer != null)
            mediaPlayer.stop();

        // if old timer is currently working then stop to avoid conflict
        // because when playMedia() method start, a new timer will begin
        if (mediaTimer.getTimer() != null)
            mediaTimer.cancelTimer();

        // new media and media player for new song
        media = new Media(songFiles.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        // create new timer instance to begin count for progress bar
        // if not create this instance for each prepare media,
        // the song progress bar will work once then after it doesn't work
        mediaTimer = new MediaTimer(this, mediaPlayer, songProgressBar);
    }

    /**
     * Initializes the player by adding songs from the current playlist and setting up media and media player.
     * <p>
     * The initializing will work after first run program or after shuffle playlist to update {@link MediaPlayerManagement#songFiles}
     */
    public void initializePlayer() {
        setSongNumber(0);

        // clear songFiles if it already has elements
        songFiles.clear();
        // add songs to songFiles from Current Playlist
        for (Song song : playlist.getSongList()) {
            File songFile = new File(song.getSongPath());
            songFiles.add(songFile);
        }
        // create media object with the path of the first song in the list
        media = new Media(songFiles.get(songNumber).toURI().toString());
        // create media player with the media object
        mediaPlayer = new MediaPlayer(media);
        // create media timer with the media player and the song progress bar
        mediaTimer = new MediaTimer(this, mediaPlayer, songProgressBar);

        // update songs list view
        playlist.updateListView(songsListView);

    }


}
