package project.mediaplayer.model;

import java.util.Collections;
import java.util.List;

/**
 * This class is a Concrete Strategy Class of Class {@link MediaPlayerManagement}
 * <p>
 * The purpose of this class is to Shuffle Media List using {@link Collections#shuffle(List)} method
 * then call {@link MediaPlayerManagement#initializePlayer()}
 */

public class ConcreteStrategyShuffleMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {
        // first stop the media player to avoid conflict among threads
        mediaPlayerManagement.stopMedia();

        // shuffle media list of the mediaPlayerManagement
        Collections.shuffle(mediaPlayerManagement.getSongList());

        mediaPlayerManagement.setShuffleState(true);

        // initialize the media player
        mediaPlayerManagement.initializePlayer();
    }
}
