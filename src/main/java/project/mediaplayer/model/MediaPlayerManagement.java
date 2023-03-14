package project.mediaplayer.model;

import javafx.scene.control.*;
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

    private final ToggleButton favoriteSongButton;
    private int songNumber = 0;

    private MediaTimer mediaTimer;
    private final ProgressBar songProgressBar;
    private final Slider volumeSlider;

    public MediaPlayerManagement(CurrentPlaylist currentPlaylist,
                                 ListView<String> songsListView,
                                 Label songNameLabel,
                                 ProgressBar songProgressBar,
                                 Slider volumeSlider, ToggleButton favoriteSongButton) {
        this.currentPlaylist = currentPlaylist;
        this.songsListView = songsListView;
        this.songNameLabel = songNameLabel;

        this.songProgressBar = songProgressBar;
        this.volumeSlider = volumeSlider;
        this.favoriteSongButton = favoriteSongButton;

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
    public void initialPlayer() {
        setSongNumber(0);

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
        mediaTimer = new MediaTimer(this, mediaPlayer, songProgressBar);

        // update songs list view
        currentPlaylist.updateListView(songsListView);

    }

    /**
     * <p>This method ({@link MediaPlayerManagement#playMedia()} ()}) is describes event click play button will play music current in ListView
     * <ul> When clicked playButton:
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
        /* update state of favorite button for each time playing new song
         *  the value of method setSelected() is true or false
         *  and this value update from attribute isFavorite of songs
         */
        favoriteSongButton.setSelected(currentPlaylist.getSongs().get(songNumber).isFavorite());

        // open choose directory of music if current playlist have nothing
        songNameLabel.setText(currentPlaylist.getSongs().get(songNumber).getSongName());
        System.out.println("Song Number: " + songNumber);
        // focus to current song is playing in list view
        songsListView.getFocusModel().focus(songNumber);
        // scroll to current playing music on listView
        songsListView.scrollTo(songNumber);

        // set volume for media player when choose a new song with value of volumeSlider
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        System.out.println(mediaPlayer.getVolume());

//        media = new Media(songFiles.get(songNumber).toURI().toString());
//        mediaPlayer = new MediaPlayer(media);

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

    /**
     * <p>This method ({@link MediaPlayerManagement#resetMedia()} ()}) to replay current music is playing</p>
     * <ul>When clicked resetButton:
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
     * <ul>When clicked previousButton:
     *     <li>This method is used to go to the previous media file.</li>
     *     <li>If the current media file is the first one, it will go to the last media file.</li>
     * </ul>
     * </p>
     */
    public void previousMedia() {
        // Check if the current songNumber is greater than zero (not the first media file)
        if (songNumber > 0)
            // Decrease the songNumber to go to the previous media file.
            songNumber--;
        else
            // If the current media file is the first one, go to the last media file.
            songNumber = songFiles.size() - 1;

        // If the timer is running, cancel it.
        if (mediaTimer.getIsRunning())
            mediaTimer.cancelTimer();

        prepareMedia();
        // Play the new media file.
        playMedia();

    }

    /**
     * <p>This method ({@link MediaPlayerManagement#nextMedia()}) to play next music
     * <ul>When clicked nextButton:
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
        if (mediaTimer.getIsRunning())
            mediaTimer.cancelTimer();

        prepareMedia();
        playMedia();
    }

    /**
     * <p>This method (stopMedia) to called method {@link MediaPlayerManagement#stopMedia()}
     * </p>
     */
    public void stopMedia() {
        if (mediaTimer.getTimer() != null)
            mediaTimer.cancelTimer();
        mediaPlayer.stop();
    }

    /**
     * <p>This method shuffleMusic to shuffle list songs of CurrentPlaylist,
     * then add shuffled list songs to ListView,
     * and add song files from shuffled list to {@link MediaPlayerManagement#songFiles} with method {@link MediaPlayerManagement#initialPlayer()}</p>
     */
    public void shuffleMusic() {
        stopMedia();
        currentPlaylist.shufflePlaylist();
        initialPlayer();
    }


}
