package project.mediaplayer.model;

import java.util.List;

/**
 * This is a interface to represent update method for observer of class {@link Playlist}
 */
public interface PlaylistObserver {
    void updatePlaylistObserver(List<Song> songList, String dialogTitle, String dialogMessage);
}
