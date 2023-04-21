package project.mediaplayer.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Playlist implements PlaylistSubject {

    public static final String HOME_PLAYLIST_NAME = "Home";
    public static final String FAVORITE_PLAYLIST_NAME = "Favorite";
    public static final String PLAYING_PLAYLIST_NAME = "Playing Queue";
    public static final String FOUND_SONGS_PLAYLIST_NAME = "Search Result";

    private String alertTitle;
    private String alertMessage;

    private List<Song> songList = new ArrayList<>();
    private final List<PlaylistObserver> playlistObserverList = new ArrayList<>();
    private String playlistName;

    public Playlist(String playlistName) {
        this.playlistName = playlistName;
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

        notifyPlaylistObservers();
    }

    public void setPlaylistFrom(Playlist otherPlaylist) {
        this.songList = otherPlaylist.songList;
        notifyPlaylistObservers();
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

        // alert to user because this song that they need to find not found
        if (result.isEmpty()) {
            this.alertTitle = "No song founded";
            this.alertMessage = "The song with name: \"" + searchSongName.toUpperCase() + "\" cannot found! \n" +
                    "Please check song name and try again!";

            setAlertDialogTitleAndMessage(alertTitle, alertMessage);
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

    public void addSongsFromDataFileToPlaylist(Files files, String dataFilePath) {
        for (String fileRecord : files.readRecordsFromDataFile(dataFilePath)
        ) {

            StringTokenizer tokenizer = new StringTokenizer(fileRecord, "\t");
            int songID = Integer.parseInt(tokenizer.nextToken());
            String songName = tokenizer.nextToken();
            boolean isFavorite = Boolean.parseBoolean(tokenizer.nextToken());
            String songPath = tokenizer.nextToken();

            File checkedFile = new File(songPath);

            if (checkedFile.exists()) {
                Song song = new Song(songID, songName, isFavorite, songPath);
                this.getSongList().add(song);
            } else {
                // show information dialog
                // will add in the future
                break;
            }
        }

    }


    @Override
    public void registerPlaylistObserver(PlaylistObserver playlistObserver) {
        if (!this.playlistObserverList.contains(playlistObserver))
            this.playlistObserverList.add(playlistObserver);
    }

    @Override
    public void unregisterPlaylistObserver(PlaylistObserver playlistObserver) {
        this.playlistObserverList.remove(playlistObserver);
    }

    @Override
    public void notifyPlaylistObservers() {
        for (PlaylistObserver playlistObserver :
                this.playlistObserverList) {
            playlistObserver.updatePlaylistObserver(this.songList, alertTitle, alertMessage);
        }
    }

    public void addSongToPlaylist(Song song, boolean isFavorite) {
        if (!this.songList.contains(song)) {
            song.setFavorite(isFavorite);
            this.songList.add(song);
        }
    }

    public void removeSongToPlaylist(Song song, boolean isFavorite) {
        if (this.songList.contains(song)) {
            song.setFavorite(isFavorite);
            this.songList.remove(song);
        }
    }

    public void setAlertDialogTitleAndMessage(String alertTitle, String alertMessage) {
        this.alertTitle = alertTitle;
        this.alertMessage = alertMessage;

        notifyPlaylistObservers();
    }

}