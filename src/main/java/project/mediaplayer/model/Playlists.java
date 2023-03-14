package project.mediaplayer.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Playlists {
    protected String playlistName;
    protected int playlistType;
    protected ArrayList<Song> songs = new ArrayList<>();

    public static final int MAIN_PLAYLIST = 1;

    public static final int CURRENT_PLAYLIST = 2;
    public static final int FAVORITE_PLAYLIST = 3;
    public static final int FOUND_SONGS_PLAYLIST = 4;

    public Playlists(int playlistType) {
        this.playlistType = playlistType;

//        this.dateCreation = dateCreation;
//        this.playlistDuration = playlistDuration;
    }

    // generate shuffle number without repeat => WORKED
//    public ArrayList<Integer> shuffleNumber() {
//        ArrayList<Integer> result = new ArrayList<>();
//
//        Random rd = new Random();
//        while (true) {
//            int number = rd.nextInt(0, 10);
//            if (!result.contains(number)) {
//                result.add(number);
//                if (result.size() >= 10) break;
//            }
//        }
//        return result;
//    }

    // shuffle playlist method for shuffle function
    public void shufflePlaylist() {
        Collections.shuffle(this.songs);
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
        for (Song song : this.getSongs()
        ) {
            result += song.toString() + "\n";
        }
        return result;
    }

    /**
     * Add songs from Playlists to UI using {@link ListView}
     *
     * @param listView a list to display song list on UI, user can interact with it to choose music to play
     */
    public void updateListView(ListView<String> listView) {
        ObservableList<String> observableListSongs = FXCollections.observableArrayList();

        // if list view has elements, clear its
        listView.getItems().clear();

        // adding items to list view
        for (Song song : this.getSongs()) {
            observableListSongs.add("SongID: " + "\t" + song.getSongID() + "\t\t" + song.getSongName() + "\n\t\t\t\t" + song.getSongPath());
        }

//        System.out.println(mainPlaylist);
//        System.out.println(songItems);
        listView.setItems(observableListSongs);
        listView.scrollTo(0);
    }


    /**
     * <p>Find list of songs which has name start with {@param searchSongName}.</p>
     * <p>Because the built-in method startsWith(String) don't allow to find with ignore case
     * then uses subString songName in list and compares with {@param searchSongName}.</p>
     *
     * @param searchSongName
     * @return {@link ArrayList} of songs with have name start with searchSongName
     */
    public ArrayList<Song> findSongs(String searchSongName) {
        ArrayList<Song> result = new ArrayList<>();

        int songNameLength = searchSongName.length();
        for (Song song : this.songs) {
            String substringSongName = song.getSongName().substring(0, songNameLength);

            if (substringSongName.equalsIgnoreCase(searchSongName))
                result.add(song);
        }
        if (result.isEmpty())
            throw new RuntimeException("No result for " + searchSongName);

        return result;
    }

    public static void main(String[] args) {

    }
}