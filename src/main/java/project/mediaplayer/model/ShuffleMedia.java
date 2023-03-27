package project.mediaplayer.model;

import javafx.scene.control.ToggleButton;

public class ShuffleMedia implements MediaPlayerControl {
    @Override
    public void playerControl(MediaPlayerManagement mediaPlayerManagement) {
        Playlist playlist = mediaPlayerManagement.getPlaylist();
        ToggleButton shuffleMediaButton = mediaPlayerManagement.getShuffleMediaButton();

        mediaPlayerManagement.setMediaPlayerControl(new StopMedia());
        mediaPlayerManagement.doActionControl();

        if (!shuffleMediaButton.isSelected()) {
            System.out.println(shuffleMediaButton.isSelected());
            playlist.setPlaylistBehavior(new ShufflePlaylist());
            playlist.doPlaylistBehavior();
        } else {
            System.out.println(shuffleMediaButton.isSelected());
            playlist.setPlaylistBehavior(new NonShufflePlaylist());
            playlist.doPlaylistBehavior();
        }

        mediaPlayerManagement.initializePlayer();
    }
}
