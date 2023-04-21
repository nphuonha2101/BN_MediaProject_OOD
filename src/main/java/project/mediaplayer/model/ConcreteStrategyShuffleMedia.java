package project.mediaplayer.model;

import java.util.Collections;

public class ConcreteStrategyShuffleMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {

        mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyStopMedia());
        mediaPlayerManagement.doStrategyAction();

        Collections.shuffle(mediaPlayerManagement.getSongList());

        mediaPlayerManagement.initializePlayer();
    }
}
