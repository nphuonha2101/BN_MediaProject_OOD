package project.mediaplayer.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MediaPlayerManagement implements PlaylistObserver, MediaPlayerManagementSubject, MediaTimerObserver {
    private final List<MediaPlayerManagementObserver> mediaPlayerManagementObserverList = new ArrayList<>();

    private final List<File> songFiles = new ArrayList<>();
    private List<Song> songList = new ArrayList<>();
    private Media media;
    private MediaPlayer mediaPlayer;
    private MediaTimer mediaTimer;
    private MediaPlayerManagementStrategy mediaPlayerManagementStrategy;
    private int songNumber = 0;
    private double songProgress;
    private String songName;
    private boolean isPlayingSongFavorite;
    private double mediaPlayerVolumeValue;
    private final PlaylistSubject playlistSubject;

    //-----------------------CONSTRUCTOR------------------------//
    public MediaPlayerManagement(PlaylistSubject playlistSubject) {
        this.playlistSubject = playlistSubject;
        this.playlistSubject.registerPlaylistObserver(this);
    }

    //-----------------------GETTERS AND SETTERS------------------------//
    public int getSongNumber() {
        return this.songNumber;
    }

    public void setSongNumber(int songNumber) {
        this.songNumber = songNumber;
    }

    public List<File> getSongFiles() {
        return this.songFiles;
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public MediaTimer getMediaTimer() {
        return this.mediaTimer;
    }

    public void setPlayingSongName(String songName) {
        this.songName = songName;
    }

    public void setPlayingSongFavorite(boolean playingSongFavorite) {
        isPlayingSongFavorite = playingSongFavorite;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public double getMediaPlayerVolumeValue() {
        return mediaPlayerVolumeValue;
    }

    public void setMediaPlayerVolumeValue(double mediaPlayerVolumeValue) {
        if (this.mediaPlayer != null) {
            this.mediaPlayerVolumeValue = mediaPlayerVolumeValue;
            this.mediaPlayer.setVolume(this.mediaPlayerVolumeValue);
        }
    }

    public void setSongProgress(double songProgress) {
        this.mediaTimer.setSongProgress(songProgress);
    }

    //-----------------------OBSERVER METHOD------------------------//

    /**
     * Update data from {@link Playlist} subject to its observer
     * When songs was imported from {@link Playlist}
     *
     * @param songList
     * @param alertTitle
     * @param alertMessage
     */
    @Override
    public void updatePlaylistObserver(List<Song> songList, String alertTitle, String alertMessage) {
        this.songList = songList;

        // when playlist has songs then initialize the media player
        initializePlayer();
        // notify to playlist's observers (controller) to request view update list view
        notifyMPManagementObservers();
    }

    /**
     * @param songProgress
     */
    @Override
    public void updateMediaTimerObserver(double songProgress) {
        this.songProgress = songProgress;
        System.out.println("MPM: " + this.songProgress);
        notifyMPManagementObservers();


    }

    //-----------------------SUBJECT METHODS------------------------//
    @Override
    public void registerMPManagementObserver(MediaPlayerManagementObserver mediaPlayerManagementObserver) {
        if (!this.mediaPlayerManagementObserverList.contains(mediaPlayerManagementObserver))
            this.mediaPlayerManagementObserverList.add(mediaPlayerManagementObserver);
    }

    @Override
    public void unregisterMPManagementObserver(MediaPlayerManagementObserver mediaPlayerManagementObserver) {
        this.mediaPlayerManagementObserverList.remove(mediaPlayerManagementObserver);
    }

    @Override
    public void notifyMPManagementObservers() {
        for (MediaPlayerManagementObserver mediaPlayerManagementObserver : mediaPlayerManagementObserverList
        ) {
            mediaPlayerManagementObserver.updateMediaPlayerManagementObserver(this.songName,
                    this.isPlayingSongFavorite,
                    this.getMediaPlayerVolumeValue(),
                    this.songNumber,
                    this.songProgress);
        }
    }

    //-----------------------MEDIA PLAYER MANAGEMENT STRATEGY METHOD------------------------//
    public void setMediaPlayerControlStrategy(MediaPlayerManagementStrategy mediaPlayerManagementStrategy) {
        if (this.mediaPlayer != null)
            this.mediaPlayerManagementStrategy = mediaPlayerManagementStrategy;
    }

    public void doStrategyAction() {
        if (this.mediaPlayer != null)
            this.mediaPlayerManagementStrategy.doStrategyAction(this);
    }

    //-----------------------OTHER MEDIA PLAYER MANAGEMENT METHODS------------------------//

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
        mediaTimer = new MediaTimer(this, mediaPlayer);
        mediaTimer.registerMediaTimerObserver(this);

    }

    /**
     * Initializes the player by adding songs from the current playlist and setting up media and media player.
     * <p>
     * The initializing will work after first run program or after shuffle playlist to update {@link MediaPlayerManagement#songFiles}
     */
    public void initializePlayer() {
        if (!this.songList.isEmpty()) {
            setSongNumber(0);

            // clear songFiles if it already has elements
            songFiles.clear();
            // add songs to songFiles from Current Playlist
            for (Song song : this.songList) {
                File songFile = new File(song.getSongPath());
                songFiles.add(songFile);
            }
            // create media object with the path of the first song in the list
            media = new Media(songFiles.get(songNumber).toURI().toString());
            // create media player with the media object
            mediaPlayer = new MediaPlayer(media);
            // create media timer with the media player and the song progress bar
            mediaTimer = new MediaTimer(this, mediaPlayer);
            mediaTimer.registerMediaTimerObserver(this);

        } else {
            // notify
        }
    }

}
