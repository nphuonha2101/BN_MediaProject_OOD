package project.mediaplayer.model;

import java.util.ArrayList;

/* The purposes of Favorite Playlist:
    - Favorite playlist to storage songs in which user favorite */

public class FavoritePlaylist extends Playlists {
    private String playlistName;
    private boolean isFavorite;
    private ArrayList<Song> songs;

    public FavoritePlaylist(int playlistType) {
        super(playlistType);
    }
//    private String dateCreation;
//    private Time playlistDuration;


    /* Add songs which is favorite to favorite playlist from main playlist
     * -> Main playlist is uses for storage songs after chooses file from directory */
    public void addSongToFavorite(MainPlaylist playlists) {
        for (Song song : playlists.getSongs()) {
            if (!super.getSongs().contains(song) && song.isFavorite()) {
                super.getSongs().add(song);
            }
        }
    }


}
