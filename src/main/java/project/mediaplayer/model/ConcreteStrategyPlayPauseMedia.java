package project.mediaplayer.model;

import javafx.scene.media.MediaPlayer;

public class ConcreteStrategyPlayPauseMedia implements MediaPlayerManagementStrategy {


    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {

        /* update state of favorite button for each time playing new song
         *  the value of method setSelected() is true or false
         *  and this value update from attribute isFavorite of songs
         */
        mediaPlayerManagement.setMediaPlayerVolumeValue(mediaPlayerManagement.getMediaPlayerVolumeValue());
        System.out.println(mediaPlayerManagement.getMediaPlayerVolumeValue());

        mediaPlayerManagement.setPlayingSongName(mediaPlayerManagement.getSongList().get(mediaPlayerManagement.getSongNumber()).getSongName());
        mediaPlayerManagement.setPlayingSongFavorite(mediaPlayerManagement.getSongList().get(mediaPlayerManagement.getSongNumber()).isFavorite());


        if (mediaPlayerManagement.getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayerManagement.getMediaPlayer().pause();
            mediaPlayerManagement.getMediaTimer().cancelTimer();

        } else {
            mediaPlayerManagement.setMediaPlayerVolumeValue(mediaPlayerManagement.getMediaPlayerVolumeValue());

            mediaPlayerManagement.getMediaPlayer().play();
            // begin timer for progress bar
            // start from 0 to total duration of song then stop
            mediaPlayerManagement.getMediaTimer().beginTimer();
        }
    }
}
