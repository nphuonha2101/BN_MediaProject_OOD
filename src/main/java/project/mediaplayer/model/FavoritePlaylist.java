package project.mediaplayer.model;

import java.util.ArrayList;

public class FavoritePlaylist extends Playlists {
    private String playlistName;
    private boolean isFavorite;
    private ArrayList<Song> songs;
//    private String dateCreation;
//    private Time playlistDuration;


    public FavoritePlaylist(String playlistName, boolean isFavorite, ArrayList<Song> songs) {
        super(playlistName, isFavorite, songs);
    }

}
