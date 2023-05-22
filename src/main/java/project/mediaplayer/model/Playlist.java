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

    //-----------------------GETTERS AND SETTERS------------------------//
    public String getPlaylistName() {
        return this.playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<Song> getSongList() {
        return this.songList;
    }

    /**
     * Set song list of this playlist by another song list
     *
     * @param songList is song list that you want to set to this playlist
     */
    public void setSongList(List<Song> songList) {
        this.songList = songList;
        notifyPlaylistObservers();
    }

    /**
     * Set song list of this playlist by other playlist's song list
     *
     * @param otherPlaylist is playlist that you want to set to this playlist
     */
    public void setPlaylistFrom(Playlist otherPlaylist) {
        this.songList = otherPlaylist.songList;

        notifyPlaylistObservers();
    }

    //-----------------------CONSTRUCTOR------------------------//
    public Playlist(String playlistName) {
        this.playlistName = playlistName;
    }

    //-----------------------PLAYLIST'S METHODS------------------------//

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
            String alertTitle = "No song founded";
            String alertMessage = "The song with name: \"" + searchSongName.toUpperCase() + "\" cannot found! \n" +
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

    /**
     * Adds songs from a list of fileManagement to the current playlist. The old list of songs is cleared before adding new songs.
     *
     * @param fileManagement The list of fileManagement containing the songs to add to the playlist.
     */
    public void addSongsFromFilesToPlaylist(FileManagement fileManagement) {
        // clear old list songs if it exists
        this.getSongList().clear();
        int songID = 0;
        // add songs from file list by new Song instance
        for (File file : fileManagement.getListFiles()) {
            if (file != null) {
                String nameSong = FileManagement.getFileNameFromFilePath(file);
                this.getSongList().add(new Song(songID, nameSong, false, file.getPath()));
                songID++;
            }
        }
        // write songs data which just have been imported to data file
        fileManagement.writeSongsDataFile(FileManagement.PREVIOUS_IMPORTED_SONGS_DATA_FILE_PATH, this);
    }

    /**
     * Adds songs from a data file to the current playlist. Only songs whose corresponding fileManagement exist will be added to the playlist.
     *
     * @param fileManagement The FileManagement object used for reading data from the data file.
     * @param dataFilePath   The file path of the data file to read from.
     */
    public void addSongsFromDataFileToPlaylist(FileManagement fileManagement, String dataFilePath) {
        for (String fileRecord : fileManagement.readRecordsFromDataFile(dataFilePath)
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
            }
        }
    }

    /**
     * Add a song to this playlist
     *
     * @param song       is a song that you want to add to this playlist
     * @param isFavorite to define if you want this song was added is favorite
     */
    public void addSongToPlaylist(Song song, boolean isFavorite) {
        if (!this.songList.contains(song)) {
            song.setFavorite(isFavorite);
            this.songList.add(song);
        }
    }

    /**
     * Remove a song to this playlist
     *
     * @param song       is a song that you want to remove to this playlist
     * @param isFavorite to define if you want this song was removed is favorite
     */
    public void removeSongToPlaylist(Song song, boolean isFavorite) {
        if (this.songList.contains(song)) {
            song.setFavorite(isFavorite);
            this.songList.remove(song);
        }
    }

    //-----------------------SUBJECT METHODS------------------------//
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

    //-----------------------OTHER METHODS------------------------//

    /**
     * Set title and message to dialog to notify {@link project.mediaplayer.UI.MediaPlayerController} class to show alert
     * with  {@link project.mediaplayer.UI.MediaPlayerController#showInformationAlert(String, String)} method
     *
     * @param alertTitle   is a title of the information alert
     * @param alertMessage is a message of the information alert
     */
    public void setAlertDialogTitleAndMessage(String alertTitle, String alertMessage) {
        this.alertTitle = alertTitle;
        this.alertMessage = alertMessage;

        notifyPlaylistObservers();
    }

}