package project.mediaplayer.model;

import java.util.Collections;
import java.util.Comparator;

public class ConcreteStrategySortMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {
        mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyStopMedia());
        mediaPlayerManagement.doStrategyAction();

        Collections.sort(mediaPlayerManagement.getSongList(), new Comparator<Song>() {
            @Override
            public int compare(Song song1, Song song2) {
                if (song1.getSongName().compareTo(song2.getSongName()) > 0)
                    return 1;
                else return -1;
            }
        });

        mediaPlayerManagement.initializePlayer();
    }
}
