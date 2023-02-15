package project.mediaplayer.model;

import java.util.ArrayList;

public class CurrentPlaylist extends Playlists {
    public CurrentPlaylist(String playlistName, boolean isFavoritePlaylist, ArrayList<Song> songs) {
        super(playlistName, isFavoritePlaylist, songs);
    }

}
