package project.mediaplayer.mediaplayer_OOD;

import java.util.*;

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


    // generate shuffle number without repeat
    public static ArrayList<Integer> shuffleNumber() {
        ArrayList<Integer> result = new ArrayList<>();

        Random rd = new Random();
        while (result.size() <= 10) {
            int number = rd.nextInt(0, 10);
            if (!result.contains(number)) {
                result.add(number);
                System.out.print(number);
            }
            else {
                number = rd.nextInt(0, 10);;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = shuffleNumber();
        System.out.println(arrayList);
    }

}
