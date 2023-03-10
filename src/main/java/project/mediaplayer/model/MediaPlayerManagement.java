package project.mediaplayer.model;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class MediaPlayerManagement {

    private final CurrentPlaylist currentPlaylist;

    private final ListView<String> songsListView;
    private final ArrayList<File> songFiles = new ArrayList<>();
    private Media media;
    private MediaPlayer mediaPlayer;
    private final Label songNameLabel;
    private int songNumber = 0;
    private MediaTimer mediaTimer;
    private final ProgressBar songProgressBar;
    private final Slider volumeSlider;

    public MediaPlayerManagement(CurrentPlaylist currentPlaylist,
                                 ListView<String> songsListView,
                                 Label songNameLabel,
                                 ProgressBar songProgressBar,
                                 Slider volumeSlider) {
        this.currentPlaylist = currentPlaylist;
        this.songsListView = songsListView;
        this.songNameLabel = songNameLabel;
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

    /**
     * Initializes the player by adding songs from the current playlist and setting up media and media player
     */
    public void initialPlayer() {
        // clear songFiles if it already has elements
        songFiles.clear();
        // add songs to songFiles from Current Playlist
        for (Song song : currentPlaylist.getSongs()) {
            File songFile = new File(song.getSongPath());
            songFiles.add(songFile);
        }
        // create media object with the path of the first song in the list
        media = new Media(songFiles.get(songNumber).toURI().toString());
        // create media player with the media object
        mediaPlayer = new MediaPlayer(media);
        // create media timer with the media player and the song progress bar
        mediaTimer = new MediaTimer(mediaPlayer, songProgressBar);
    }

    /**
     * <p>This method ({@link MediaPlayerManagement#playMedia()} ()}) is describes event click play button will play music current in ListView
     * <ul> When clicked {@link project.mediaplayer.UI.MediaPlayerController#playButton}
     *     <li>songNameLabel will show songName from method {@link Song#getSongName()}</li>
     *     <li>songsListView will focus to current song is playing in list view</li>
     *     <li>songsListView will scroll to current song is playing in list view</li>
     *     <li>{@link MediaPlayer#setVolume(double)}  } will set volume for media player when choose a new song with value of volumeSlider</li>
     *     <li>media will called new Media(), after that new MediaPlayer(media) to add current media into {@link MediaPlayer} </li>
     *     <li>When media is playing, if we click button play, called method {@link MediaPlayer#pause()} and {@link MediaTimer#cancelTimer()} else
     *     called method {@link MediaPlayer#play()}
     *     </li>
     *     <li>Finally, called method {@link MediaTimer#beginTimer()} to start count duration when playing music</li>
     * </ul>
     * </p>
     */
    public void playMedia() {
        // open choose directory of music if current playlist have nothing
        songNameLabel.setText(currentPlaylist.getSongs().get(songNumber).getSongName());
        // focus to current song is playing in list view
        songsListView.getFocusModel().focus(songNumber);
        // scroll to current song is playing in list view
        songsListView.scrollTo(songNumber);

        // set volume for media player when choose a new song with value of volumeSlider
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        System.out.println(mediaPlayer.getVolume());

        media = new Media(songFiles.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            mediaTimer.cancelTimer();

        } else mediaPlayer.play();

        // begin timer for progress bar
        // start from 0 to total duration of song then stop
        mediaTimer.beginTimer();

    }

    /**
     * <p>This method ({@link MediaPlayerManagement#resetMedia()} ()}) to replay current music is playing</p>
     * <ul>When clicked button {@link project.mediaplayer.UI.MediaPlayerController#resetButton}
     *     <li>Method {@link javafx.scene.control.ProgressIndicator#setProgress(double)} is called, and set progress to zero</li>
     *     <li>Simultaneously method {@link javafx.scene.control.Labeled#setText(String)} is called, and set text label same songName</li>
     *     <li>And {@link MediaPlayer#seek(Duration)} is called, set duration to zero</li>
     * </ul>
     */
    public void resetMedia() {
        songProgressBar.setProgress(0);
        songNameLabel.setText(songFiles.get(songNumber).getName());
        mediaPlayer.seek(Duration.seconds(0));
    }

    /**
     * <p>This method ({@link MediaPlayerManagement#previousMedia()} ()}) to play previous music
     * <ul>When clicked button {@link project.mediaplayer.UI.MediaPlayerController#previousButton}
     *     <li>This method is used to go to the previous media file.</li>
     *     <li>If the current media file is the first one, it will go to the last media file.</li>
     * </ul>
     * </p>
     */
    public void previousMedia() {
        // Check if the current songNumber is greater than zero (not the first media file)
        if (songNumber > 0) {
            // Decrease the songNumber to go to the previous media file.
            songNumber--;
            // Stop the media player.
            mediaPlayer.stop();
            // If the timer is running, cancel it.
            if (mediaTimer.getIsRunning()) {
                mediaTimer.cancelTimer();
            }
            // Play the new media file.
            playMedia();
        } else {
            // If the current media file is the first one, go to the last media file.
            songNumber = songFiles.size() - 1;
            // Stop the media player.
            mediaPlayer.stop();
            // If the timer is running, cancel it.
            if (mediaTimer.getIsRunning()) {
                mediaTimer.cancelTimer();
            }
            // Play the new media file.
            playMedia();
        }
    }

    /**
     * <p>This method ({@link MediaPlayerManagement#nextMedia()}) to play next music
     * <ul>When clicked button {@link project.mediaplayer.UI.MediaPlayerController#nextButton}
     *     <li>Increments to the next song in the playlist and plays it.</li>
     *     <li>If at the end of the playlist, returns to the beginning (0).</li>
     * </ul>
     * </p>
     */
    public void nextMedia() {
        if (songNumber < songFiles.size() - 1)
            songNumber++;
        else
            songNumber = 0;
        mediaPlayer.stop();
        if (mediaTimer.getIsRunning()) {
            mediaTimer.cancelTimer();
        }
        playMedia();
    }

    /**
     * <p>This method (stopMedia) to called method {@link MediaPlayerManagement#stopMedia()}
     * </p>
     */
    public void stopMedia() {
        mediaPlayer.stop();
    }
}
