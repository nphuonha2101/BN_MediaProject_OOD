package project.mediaplayer.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.text.Element;
import javax.swing.text.html.ListView;
import java.util.*;

public abstract class Playlists {
    protected String playlistName;
    protected int playlistType;
    protected ArrayList<Song> songs = new ArrayList<>();
//    private String dateCreation;
//    private Time playlistDuration;

    public static final int MAIN_PLAYLIST = 1;

    public static final int CURRENT_PLAYLIST = 2;
    public static final int FAVORITE_PLAYLIST = 3;

    public Playlists(int playlistType) {
        this.playlistType = playlistType;

//        this.dateCreation = dateCreation;
//        this.playlistDuration = playlistDuration;
    }

    // generate shuffle number without repeat => WORKED
    public ArrayList<Integer> shuffleNumber() {
        ArrayList<Integer> result = new ArrayList<>();

        Random rd = new Random();
        while (true) {
            int number = rd.nextInt(0, 10);
            if (!result.contains(number)) {
                result.add(number);
                if (result.size() >= 10) break;
            }
        }
        return result;
    }



//    public Song findSong(String songName) {
//        Song result;
//        for (Song song: songs
//             ) {
//            if (song.getSongName().equalsIgnoreCase(songName))
//                result = song;
//        }
//        return result;
//    }
//}



//    public void removeSong(ArrayList<File> files) {
//        for (File file : files) {
//
//        }
//    }

   public void addSongFromOtherPlaylist(Playlists other) {
        this.songs.clear();
        this.songs.addAll(other.getSongs());
   }

    // GETTER METHODS
    public String getPlaylistName() {
        return playlistName;
    }

    public int isFavoritePlaylist() {
        return playlistType;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }


    // SETTER METHODS
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public void setFavoritePlaylist(int favoritePlaylist) {
        playlistType = favoritePlaylist;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        String result = "";
        for (Song song: this.getSongs()
        ) {
            result += song.toString() + "\n";
        }
        return result;
    }

    public static void main(String[] args) {

    }
}