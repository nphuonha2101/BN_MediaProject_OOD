package project.mediaplayer.model;

import java.util.ArrayList;

/* The purposes of Favorite Playlist:
    - Favorite playlist to storage songs in which user favorite */

public class FavoritePlaylist extends Playlists {
    private String playlistName;
    private boolean isFavorite;
    private final ArrayList<Song> songs = new ArrayList<>();

    public FavoritePlaylist(int playlistType) {
        super(playlistType);
    }
//    private String dateCreation;
//    private Time playlistDuration;


    /* Add songs which is favorite to favorite playlist from main playlist
     * -> Main playlist is uses for storage songs after chooses file from directory */
    public void addSongToFavorite(MainPlaylist playlists) {
        for (Song song : playlists.getSongs()) {
            if (!super.getSongs().contains(song) && song.isFavorite())
                super.getSongs().add(song);
        }
    }

    public void addSongToFavoritePlaylist(Song song) {
        if (this.songs.isEmpty() || !this.songs.contains(song) || !song.isFavorite())
            this.songs.add(song);
        song.setFavorite(true);
    }

    public void removeSongFromFavoritePlaylist(Song song) {
        if (!this.songs.isEmpty() || this.songs.contains(song) || song.isFavorite())
            this.songs.remove(song);
        song.setFavorite(false);
    }

}
