package project.mediaplayer.model;

import java.util.ArrayList;

/**
 * The purposes of {@link FavoritePlaylist}:
 * - Favorite Playlist to storage songs in which user favorite
 */

public class FavoritePlaylist extends Playlists {
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

//    /**
//     * Adding song from {@link CurrentPlaylist} to {@link FavoritePlaylist}
//     * @param song meaning song to add to {@link FavoritePlaylist}
//     */
//    public void addSongToFavoritePlaylist(Song song) {
//        // if this songs list is empty or songs list doesn't contain this song or this song isn't favorite
//        // then adds song
//            this.songs.add(song);
//        // and set attribute isFavorite of song with true
//        song.setFavorite(true);
//    }


//    /**
//     * Removing song from {@link FavoritePlaylist}
//     * @param song meaning song to remove from {@link FavoritePlaylist}
//     */
//    public void removeSongFromFavoritePlaylist(Song song) {
//        // if this songs list isn't empty or songs list contained this song or this song is favorite
//        // then removes song
//        if (!this.songs.isEmpty())
//            this.songs.remove(song);
//        // and set attribute isFavorite of song with false
//        song.setFavorite(false);
//    }

}
