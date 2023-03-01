package project.mediaplayer.model;

/* The purposes of Current Playlist:
 *   - Current Playlist is a playlist which Media Player read songs from it and play
 *   - The Media Player only read songs from this playlist
 *   - Main Playlist is used for storage songs after add songs file from a directory (reserve songs)
 *   - When switch to Favorite button on UI, Current Playlist will clear all songs
 *   then adds all songs from Favorite Playlist
 */
public class CurrentPlaylist extends Playlists {

    public CurrentPlaylist(int playlistType) {
        super(playlistType);
    }

//    public void playInCurrentPlaylist(int playingState) {
//        for (Song song: super.songs
//             ) {
//            SongPlayer songPlayer = new SongPlayer(song.getSongPath(), playingState);
//            songPlayer.play();
//        }
//    }
}
