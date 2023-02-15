package project.mediaplayer.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.html.ListView;
import java.io.File;
import java.util.*;

public abstract class Playlists {
    private String playlistName;
    private boolean isFavoritePlaylist;
    private ArrayList<Song> songs;
//    private String dateCreation;
//    private Time playlistDuration;

    public static final String FAVORITE_PLAYLIST = "FAVORITE_PLAYLIST";

    public Playlists(String playlistName, boolean isFavoritePlaylist, ArrayList<Song> songs) {
        this.playlistName = playlistName;
        this.isFavoritePlaylist = isFavoritePlaylist;
        this.songs = songs;
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

//     add Songs to Playlist from file
    public void addSong(ArrayList<File> files) {
        for (File file : files) {
            if (file != null) {
//                String basePath = new File("").getAbsolutePath();
                String nameSong = Files.splitFileName(file.getPath());
                this.songs.add(new Song(nameSong, false, file.getPath()));
            }
        }
    }

//    public void removeSong(ArrayList<File> files) {
//        for (File file : files) {
//
//        }
//    }

    public void addToListView() {
        ObservableList<String> listViewSongs = FXCollections.observableArrayList();
        ListView listView = new ListView((Element) listViewSongs);
        for (Song song: this.songs
             ) {


        }


    }

    public static void main(String[] args) {

    }

}
