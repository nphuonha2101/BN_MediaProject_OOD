package project.mediaplayer.model;

import java.util.Collections;

public class ShufflePlaylist implements PlaylistBehavior {
    @Override
    public void playlistBehavior(Playlist playlist) {
        Collections.shuffle(playlist.getSongList());
    }
}
