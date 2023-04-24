package project.mediaplayer.model;

import javafx.scene.media.MediaPlayer;

/**
 * This class is a Concrete Strategy Class of Class {@link MediaPlayerManagement}
 * <p>
 * The purpose of this class is to Play Pause Media with the media of the songNumber
 * was prepared with {@link MediaPlayerManagement#prepareMedia()} method
 */

public class ConcreteStrategyPlayPauseMedia implements MediaPlayerManagementStrategy {

    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {

//        mediaPlayerManagement.setMediaPlayerVolumeValue(mediaPlayerManagement.getMediaPlayerVolumeValue());
        System.out.println(mediaPlayerManagement.getMediaPlayerVolumeValue());

        // set playing song name to notify to the observer (Controller) to update playing song name to the View
        mediaPlayerManagement.setPlayingSongName(mediaPlayerManagement.getSongList().get(mediaPlayerManagement.getSongNumber()).getSongName());
        // set playing song favorite state if this song is favorite to notify to the observer (Controller)
        // to set selected state of the favorite song button on the view
        mediaPlayerManagement.setPlayingSongFavorite(mediaPlayerManagement.getSongList().get(mediaPlayerManagement.getSongNumber()).isFavorite());

        // if the media player is playing then pause the media player
        // and cancel the timer (Pause function)
        if (mediaPlayerManagement.getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayerManagement.getMediaPlayer().pause();
            mediaPlayerManagement.getMediaTimer().cancelTimer();

        } else { // if media player already paused then play the media player
            // set media volume value for each time play
            // the value of volume was set from changeVolume() method in the Controller class
            mediaPlayerManagement.setMediaPlayerVolumeValue(mediaPlayerManagement.getMediaPlayerVolumeValue());
            // play the media with play() method of JavaFX MediaPlayer class
            mediaPlayerManagement.getMediaPlayer().play();
            // begin timer for progress bar
            // start from 0 to total duration of song then stop
            mediaPlayerManagement.getMediaTimer().beginTimer();
        }
    }
}
