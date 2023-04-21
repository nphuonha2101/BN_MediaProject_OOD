package project.mediaplayer.model;

import javafx.util.Duration;

public class ConcreteStrategyResetMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {

        mediaPlayerManagement.setSongProgress(0);
        mediaPlayerManagement.setPlayingSongName(mediaPlayerManagement.getSongList().get(mediaPlayerManagement.getSongNumber()).getSongName());


        mediaPlayerManagement.getMediaPlayer().seek(Duration.seconds(0));

        mediaPlayerManagement.notifyMPManagementObservers();
    }
}
