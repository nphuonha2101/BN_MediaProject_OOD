package project.mediaplayer.model;

import javafx.scene.control.ToggleButton;

import java.util.Collections;
import java.util.Comparator;

public class ShuffleMedia implements MediaPlayerControl {
    @Override
    public void playerControl(MediaPlayerManagement mediaPlayerManagement) {
        Playlist playlist = mediaPlayerManagement.getPlaylist();
        ToggleButton shuffleMediaButton = mediaPlayerManagement.getShuffleMediaButton();

        mediaPlayerManagement.setMediaPlayerControl(new StopMedia());
        mediaPlayerManagement.doActionControl();

        if (shuffleMediaButton.isSelected()) {
            System.out.println(shuffleMediaButton.isSelected());
            Collections.shuffle(mediaPlayerManagement.getPlaylist().getSongList());
        } else {
            System.out.println(shuffleMediaButton.isSelected());
            Collections.sort(mediaPlayerManagement.getPlaylist().getSongList(), new Comparator<Song>() {
                @Override
                public int compare(Song song1, Song song2) {
                    if (song1.getSongName().compareTo(song2.getSongName()) < 0)
                        return -1;
                    else return 1;
                }
            });
        }

        mediaPlayerManagement.initializePlayer();
    }
}
