package project.mediaplayer.model;

/**
 * This playlist to storage songs were added from computer using {@link Playlist#addSongsFromFilesToPlaylist(Files)}
 * or {@link Playlist#addSongsFromDataFileToPlaylist(String, Playlist)}
 */
public class HomePlaylist extends Playlist {
    public HomePlaylist(String playlistName, PlaylistBehavior playlistBehavior) {
        super(playlistName, playlistBehavior);
    }
}
