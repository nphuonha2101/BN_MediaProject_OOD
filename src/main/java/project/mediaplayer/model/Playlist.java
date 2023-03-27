package project.mediaplayer.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Playlist {

    public static final String HOME_PLAYLIST_NAME = "Home";
    public static final String FAVORITE_PLAYLIST_NAME = "Favorite";
    public static final String PLAYING_PLAYLIST_NAME = "Playing Queue";
    public static final String FOUND_SONGS_PLAYLIST_NAME = "Search Result";

    private List<Song> songList = new ArrayList<>();
    private String playlistName;
    private PlaylistBehavior playlistBehavior;

    public Playlist(String playlistName, PlaylistBehavior playlistBehavior) {
        this.playlistName = playlistName;
        this.playlistBehavior = playlistBehavior;
        playlistBehavior.playlistBehavior(this);
    }

    public static void main(String[] args) {

    }

    public String getPlaylistName() {
        return this.playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<Song> getSongList() {
        return this.songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public void setPlaylistFrom(Playlist otherPlaylist) {
        this.songList = otherPlaylist.songList;
    }

    public void setPlaylistBehavior(PlaylistBehavior otherPlaylistBehavior) {
        this.playlistBehavior = playlistBehavior;
    }

    public void doPlaylistBehavior() {
        playlistBehavior.playlistBehavior(this);
    }

    /**
     * Add songs from Playlist to UI using {@link ListView}
     *
     * @param listView a list to display song list on UI, user can interact with it to choose music to play
     */
    public void updateListView(ListView<String> listView) {
        ObservableList<String> observableListSongs = FXCollections.observableArrayList();

        // if list view has elements, clear its
        listView.getItems().clear();

        // adding items to list view
        for (Song song : this.songList) {
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
        for (Song song : this.getSongList()) {
            String substringSongName = song.getSongName().substring(0, songNameLength);

            if (substringSongName.equalsIgnoreCase(searchSongName))
                result.add(song);
        }

        if (result.isEmpty()) {
            String title = "No song founded";
            String message = "The song with name: \"" + searchSongName.toUpperCase() + "\" cannot found! \n" +
                    "Please check song name and try again!";

            AlertUtils.showInformationAlert(title, message);
        }

        return result;
    }

    /**
     * Use songID was got from selected listView item
     * and return index of song has this songID in CurrentPlaylist
     */
    public int getSongIndexWithSongID(int songID) {
        int result = 0;
        for (int i = 0; i < this.getSongList().size(); i++) {
            Song song = this.getSongList().get(i);

            if (song.getSongID() == songID) {
                result = i;
                break;
            }
        }
        return result;
    }

    public void addSongsFromFilesToPlaylist(Files files) {

        // clear old list songs if it exists
        this.getSongList().clear();
        int songID = 0;
        // add songs from file list by new Song instance
        for (File file : files.getListFiles()) {
            if (file != null) {
                String nameSong = Files.getFileNameFromFilePath(file);
                this.getSongList().add(new Song(songID, nameSong, false, file.getPath()));
                songID++;
            }
        }

        // write songs data which just have been imported to data file
        files.writeSongsDataFile(Files.PREVIOUS_IMPORTED_SONGS_DATA_FILE_PATH, this);
    }

    public void addSongsFromDataFileToPlaylist(String fileRecord, Playlist playlist) {
        StringTokenizer tokenizer = new StringTokenizer(fileRecord, "\t");
        int songID = Integer.parseInt(tokenizer.nextToken());
        String songName = tokenizer.nextToken();
        boolean isFavorite = Boolean.parseBoolean(tokenizer.nextToken());
        String songPath = tokenizer.nextToken();

        Song song = new Song(songID, songName, isFavorite, songPath);
        playlist.getSongList().add(song);
    }
}