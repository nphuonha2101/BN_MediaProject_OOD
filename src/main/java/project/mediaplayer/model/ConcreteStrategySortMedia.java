package project.mediaplayer.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class is a Concrete Strategy Class of Class {@link MediaPlayerManagement}
 * <p>
 * The purpose of this class is to Sort Media List using {@link Collections#sort(List, Comparator)} method
 * then call {@link MediaPlayerManagement#initializePlayer()}
 */
public class ConcreteStrategySortMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {
        // first stop the media player to avoid conflict among threads
        mediaPlayerManagement.stopMedia();

        // sort the media using sort() method of Collections
        Collections.sort(mediaPlayerManagement.getSongList(), new Comparator<Song>() {
            // compare two Song Object by Song name (ascending)
            @Override
            public int compare(Song song1, Song song2) {
                if (song1.getSongName().compareTo(song2.getSongName()) > 0)
                    return 1;
                else return -1;
            }
        });

        mediaPlayerManagement.setShuffleState(false);

        // initialize the media player
        mediaPlayerManagement.initializePlayer();
    }
}
