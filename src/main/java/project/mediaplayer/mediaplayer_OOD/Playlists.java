package project.mediaplayer.mediaplayer_OOD;

import java.util.ArrayList;

public abstract class Playlists {
    private String playlistName;
    private boolean isFavoritePlaylist;
    private ArrayList<Song> songs;
    private String dateCreation;
    private Time playlistDuration;

    public static final String FAVORITE_PLAYLIST = "FAVORITE_PLAYLIST";



    public Playlists(String playlistName, boolean isFavoritePlaylist, ArrayList<Song> songs, String dateCreation, Time playlistDuration) {
        this.playlistName = playlistName;
        this.isFavoritePlaylist = isFavoritePlaylist;
        this.songs = songs;
        this.dateCreation = dateCreation;
        this.playlistDuration = playlistDuration;
    }

}
