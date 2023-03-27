package project.mediaplayer.model;

/**
 * The purposes of {@link FavoritePlaylist}:
 * - Favorite Playlist to storage songs in which user favorite
 */

public class FavoritePlaylist extends Playlist {
    public FavoritePlaylist(String playlistName, PlaylistBehavior playlistBehavior) {
        super(playlistName, playlistBehavior);
    }

}
