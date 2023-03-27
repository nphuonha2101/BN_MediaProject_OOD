package project.mediaplayer.model;

/**
 * This playlist to storage list of songs found with name in {@link Playlist#findSongs(String)}
 */
public class FoundSongList extends Playlist {
    public FoundSongList(String playlistName, PlaylistBehavior playlistBehavior) {
        super(playlistName, playlistBehavior);
    }
}
