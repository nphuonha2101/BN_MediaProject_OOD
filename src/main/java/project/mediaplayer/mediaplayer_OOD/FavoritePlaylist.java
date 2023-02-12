package project.mediaplayer.mediaplayer_OOD;

import java.util.ArrayList;

public class FavoritePlaylist extends Playlists {
    private String playlistName;
    private boolean isFavorite;
    private ArrayList<Song> songs;
    private String dateCreation;
    private Time playlistDuration;


    public FavoritePlaylist(String playlistName, boolean isFavorite, ArrayList<Song> songs, String dateCreation, Time playlistDuration) {
        super(playlistName, isFavorite, songs, dateCreation, playlistDuration);
    }

}
