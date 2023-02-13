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
//    public void repeat(boolean loop) {
//        for (int i = 0; i < this.songs.size() - 1; i++) {
//            if (i == this.songs.size() - 1) {
//        }
//
//    }
    // generate shuffle number without repeat => WORKED
    public ArrayList<Integer> shuffleNumber() {
        ArrayList<Integer> result = new ArrayList<>();

        Random rd = new Random();
        while (true) {
            int number = rd.nextInt(0, 10);
            if (!result.contains(number)) {
                result.add(number);
//                System.out.print(number);
                if (result.size() >= 10) break;
            }
        }
        return result;
    }



    public static void main(String[] args) {

    }

}
