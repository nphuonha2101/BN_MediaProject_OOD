package project.mediaplayer.model;

public class NonShufflePlaylist implements PlaylistBehavior {
    @Override
    public void playlistBehavior(Playlist playlist) {
//        Collections.sort(playlist.getSongList(), new Comparator<Song>() {
//            @Override
//            public int compare(Song song1, Song song2) {
//                if (song1.getSongName().compareTo(song2.getSongName()) > 0)
//                    return 1;
//                else return -1;
//            }
//        });
    }
}
