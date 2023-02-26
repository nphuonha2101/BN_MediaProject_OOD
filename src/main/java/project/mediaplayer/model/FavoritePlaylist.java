package project.mediaplayer.model;

import java.util.ArrayList;

public class FavoritePlaylist extends Playlists {
    private String playlistName;
    private boolean isFavorite;
    private ArrayList<Song> songs;

    public FavoritePlaylist(int playlistType) {
        super(playlistType);
    }
//    private String dateCreation;
//    private Time playlistDuration;




    //
    public void addSongToFavorite(MainPlaylist playlists) {
        for (Song song: playlists.getSongs()
             ) {
            if (!super.getSongs().contains(song) && song.isFavorite())  {
                super.getSongs().add(song);
            }
        }
    }


}
