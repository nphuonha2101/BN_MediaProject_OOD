package project.mediaplayer.model;


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
