package project.mediaplayer.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.mediaplayer.model.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class MediaPlayerController implements Initializable, PlaylistObserver, MediaPlayerManagementObserver {

    //-----------------------HEADER LABEL TEXT CONSTANTS------------------------//
    private final static String HOME_HEADER_TEXT = "Home";
    private final static String FAVORITE_LIST_HEADER_TEXT = "Favorites";
    private final static String PLAYING_QUEUE_HEADER_TEXT = "Playing Queue";
    private final static String SEARCH_HEADER_LABEL = "Search Result";

    //-----------------------MODEL COMPONENTS------------------------//
    private final Playlist homePlaylist = new Playlist(Playlist.HOME_PLAYLIST_NAME);
    private final Playlist favoritePlaylist = new Playlist(Playlist.FAVORITE_PLAYLIST_NAME);
    private final Playlist playingPlaylist = new Playlist(Playlist.PLAYING_PLAYLIST_NAME);
    private final Playlist foundSongsList = new Playlist(Playlist.FOUND_SONGS_PLAYLIST_NAME);
    private final Files files = new Files();
    private MediaPlayerManagement mediaPlayerManagement;

    //-----------------------VIEW COMPONENTS------------------------//
    @FXML
    private Button homeButton;
    @FXML
    private Label headerLabel;
    @FXML
    private Label songNameLabel;
    @FXML
    private ListView<String> listView;
    @FXML
    private ProgressBar songProgressBar;
    @FXML
    private Button favoriteListButton;
    @FXML
    private ToggleButton shuffleButton;
    @FXML
    private Button playingQueueButton;
    @FXML
    private ToggleButton favoriteSongButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label volumeLabel;
    @FXML
    private TextField searchBar;

    //-----------------------OBSERVER METHODS------------------------//
    private void registerSubjects(PlaylistSubject playlistSubject,
                                  MediaPlayerManagementSubject mediaPlayerManagementSubject) {
        playlistSubject.registerPlaylistObserver(this);
        mediaPlayerManagementSubject.registerMPManagementObserver(this);
    }

    @Override
    public void updateMediaPlayerManagementObserver(String songName, boolean isFavoriteSong, double volumeValue, int songNumber, double songProgressValue) {
        this.songNameLabel.setText(songName);
        this.favoriteSongButton.setSelected(isFavoriteSong);
        this.listView.getFocusModel().focus(songNumber);
        this.songProgressBar.setProgress(songProgressValue);
        System.out.println("Controller: " + songProgressValue);

        // because the volume will set on PlayPause Strategy
        // if not change volume when update observer then first click on the list view the media player will play
        // with no sound
        changeVolume();
    }

    @Override
    public void updatePlaylistObserver(List<Song> songList, String alertTitle, String alertMessage) {
        updateListView(songList);
        showInformationAlert(alertTitle, alertMessage);
    }

    //-----------------------FXML METHODS (VIEW METHODS)------------------------//
    @FXML
    protected void playMedia() {
        mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyPlayPauseMedia());
        mediaPlayerManagement.doStrategyAction();

        mediaPlayerManagement.setMediaPlayerVolumeValue(volumeSlider.getValue() * 0.01);
    }

    @FXML
    protected void nextMedia() {
        mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyPlayNextMedia());
        mediaPlayerManagement.doStrategyAction();
    }

    @FXML
    protected void previousMedia() {
        mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyPlayPreviousMedia());
        mediaPlayerManagement.doStrategyAction();
    }

    @FXML
    protected void resetMedia() {
        mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyResetMedia());
        mediaPlayerManagement.doStrategyAction();
    }

    /**
     * Shuffle music list by shuffle playlist
     */
    @FXML
    protected void shuffleMedia() {
        headerLabel.setText(PLAYING_QUEUE_HEADER_TEXT);
        if (shuffleButton.isSelected()) {
            mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyShuffleMedia());
            mediaPlayerManagement.doStrategyAction();
        } else {
            mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategySortMedia());
            mediaPlayerManagement.doStrategyAction();
        }

        updateListView(mediaPlayerManagement.getSongList());
    }

    /**
     * Choose music directory and add songs file from this directory to {@link MediaPlayerController#homePlaylist},
     * add songs from {@link MediaPlayerController#homePlaylist} to {@link MediaPlayerController#playingPlaylist},
     * and create instance of {@link MediaPlayerController#mediaPlayerManagement}.
     * <p>
     * After create instance of {@link MediaPlayerController#mediaPlayerManagement} then {@link MediaPlayerManagement#initializePlayer()}
     */
    @FXML
    public void chooseFile() {
        files.chooseFileDir();

        if (files.getListFiles().isEmpty()) {
            String title = "No song added";
            String message = "Seem a directory you chose didn't have any music file (*.mp3, *.aac, *.wav). \n" + "Please import it again!";
            showInformationAlert(title, message);
        } else {
            mediaPlayerManagement = new MediaPlayerManagement(playingPlaylist);
            registerSubjects(playingPlaylist, mediaPlayerManagement);

            homePlaylist.addSongsFromFilesToPlaylist(files);
            // add song from homePlaylist
            playingPlaylist.setPlaylistFrom(homePlaylist);

        }
    }

    /**
     * Use two events are mouse click and mouse drag to get value of slider (0 -> 100, step: 1).
     * <p>
     * Because value of slider is from 0 -> 100 and value of the media player volume is from 0 -> 1,
     * then must be multiplied volumeSlider value to 0.01 to fit with the media player volume.
     */
    @FXML
    protected void changeVolume() {
        // the volume cannot be changed without media player
        // we must check if media player exists then changeVolume() method can work
        if (mediaPlayerManagement.getMediaPlayer() != null) {
            // get value from volume slider and set to media player volume
            mediaPlayerManagement.setMediaPlayerVolumeValue(volumeSlider.getValue() * 0.01);
            // set volume value from slider
            volumeLabel.setText((int) volumeSlider.getValue() + "%");
        }
    }

    /**
     * When selects song item on list view, this method will be call and get index of this selected item,
     * then set the index to songNumber of {@link MediaPlayerManagement} class to play new selected song
     */
    @FXML
    private void selectedListViewItem() {
        // gets Song information selected from list view
        String selectedSongItem = listView.getSelectionModel().getSelectedItem();

        listView.getFocusModel().focus(listView.getSelectionModel().getSelectedIndex());

        // uses StringTokenizer to get songID with delim "\t"
        StringTokenizer stringTokenizer = new StringTokenizer(selectedSongItem, "\t");
        // result: "SongID:"
        stringTokenizer.nextToken();
        // result is songID
        int songID = Integer.parseInt(stringTokenizer.nextToken());
        System.out.println("songID: " + songID);

        // gets songNumber in currentPlaylist with songID
        int songNumber = playingPlaylist.getSongIndexWithSongID(songID);

        // sets song number which is recently found
        mediaPlayerManagement.setSongNumber(songNumber);
        // prepares media to play
        mediaPlayerManagement.prepareMedia();
        // play media with prepared media
        mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyPlayPauseMedia());
        mediaPlayerManagement.doStrategyAction();
    }

    /**
     * Open About stage when click aboutButton
     * by call {@link AboutApplication#start(Stage)} method
     *
     * @throws Exception if {@link AboutApplication#start(Stage)} method has exceptions
     */
    @FXML
    private void openAbout() throws Exception {
        Stage stage = new Stage();
        AboutApplication aboutApplication = new AboutApplication();
        aboutApplication.start(stage);
    }

    /**
     * <p>This method is to use switch between playlist with buttons on left-side-bar.
     *  <ul>
     *      <li> First, get text of three buttons using getText() method.</li>
     *      <li> Second, get text from button (buttonText) that user clicked.</li>
     *      <li> Last, compare text of buttonText to three texts of button then switch to this playlist.</li>
     *  </ul>
     * </p>
     *
     * @param event get source of component and cast it to {@link Button} type then get text from it
     */
    @FXML
    protected void switchPlaylist(ActionEvent event) {
        // get text from three buttons
        final String homeButtonText = homeButton.getText();
        final String favoriteListButtonText = favoriteListButton.getText();
        final String playingQueueButtonText = playingQueueButton.getText();

        // stop media player
        mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyStopMedia());
        mediaPlayerManagement.doStrategyAction();

        // get text from button that user clicked
        String buttonText = ((Button) event.getSource()).getText();

        // switching to playlist by button that user clicked
        if (buttonText.equalsIgnoreCase(homeButtonText)) {
            headerLabel.setText(homePlaylist.getPlaylistName());
            playingPlaylist.setPlaylistFrom(homePlaylist);
            System.out.println("Home Playlist Size: " + homePlaylist.getSongList().size());

        }

        if (buttonText.equalsIgnoreCase(favoriteListButtonText)) {
            headerLabel.setText(favoritePlaylist.getPlaylistName());
            playingPlaylist.setPlaylistFrom(favoritePlaylist);
            System.out.println("Favorite Playlist Size: " + favoritePlaylist.getSongList().size());
        }

        if (buttonText.equalsIgnoreCase(playingQueueButtonText)) {
            headerLabel.setText(playingPlaylist.getPlaylistName());
            System.out.println(playingPlaylist.getSongList().size());
        }

        if (playingPlaylist.getSongList().isEmpty()) {
            String title = "No song added";
            String message = "This playlist has no song, please import its from right folder. " + "The file formats are acceptable are .mp3, .aac, .wav";
            showInformationAlert(title, message);
        }
        // initial player after switched between playlists
        mediaPlayerManagement.initializePlayer();
    }

    /**
     * If current volume of the media player is larger than 0,
     * then click on speaker button, the media player's volume is set to 0
     * else, the media player's volume set to max value 1.
     * <p>
     * This method changes the value of the volume slider first, then call
     * method changeVolume to set the media player's volume with value of volume slider was set before.
     * <p>
     * The media player's volume range is 0 to 1.0 so the media player's volume value = 0.01 * volumeSlider's value
     */
    @FXML
    protected void volumeChangeWithButton() {
        if (volumeSlider.getValue() > 0)
            volumeSlider.setValue(0);
        else
            volumeSlider.setValue(100);

        changeVolume();
    }

    /**
     * <p>Adds and removes current playing song to {@link MediaPlayerController#favoritePlaylist} with {@link MediaPlayerController#favoriteSongButton}. </p>
     * <p>After added then sets current playing song's attribute isFavorite by true.</p>
     * <p>After removed then sets current playing song's attribute isFavorite by false.</p>
     * <p>The selected state of {@link MediaPlayerController#favoriteSongButton} is set with current playing song's
     * attribute isFavorite (true or false). And this state will be update when playing new song.</p>
     */
    @FXML
    protected void addAndRemoveFavoriteSong() {

        // gets current playing song number and get current playing song from it
        int currentSongNumber = mediaPlayerManagement.getSongNumber();
        Song currentPlayingSong = playingPlaylist.getSongList().get(currentSongNumber);

        if (favoriteSongButton.isSelected()) {
            favoriteSongButton.setSelected(true);
            favoritePlaylist.addSongToPlaylist(currentPlayingSong, true);
        } else {
            favoriteSongButton.setSelected(false);
            favoritePlaylist.removeSongToPlaylist(currentPlayingSong, false);
        }


        // write favorite songs to favorite data file after add or remove song from favorite playlist
        files.writeSongsDataFile(Files.FAVORITE_DATA_FILE_PATH, favoritePlaylist);

        // TEST
        System.out.println(favoritePlaylist);
        System.out.println(currentPlayingSong);
        System.out.println(favoritePlaylist.getSongList().size());
    }

    /**
     * Scroll to current playing song on list view when click on {@link MediaPlayerController#songNameLabel}
     */
    @FXML
    protected void scrollToMusicOnListView() {
        // gets current playing song number
        int currentSongNumber = mediaPlayerManagement.getSongNumber();
        // scrolls to current playing song
        listView.scrollTo(currentSongNumber);
    }

    /**
     * <p>Searches songs from {@link MediaPlayerController#playingPlaylist} with song's name is got from {@link MediaPlayerController#searchBar}.</p>
     * <p>After searched then update songs list view {@link MediaPlayerController#listView}</p>
     */
    @FXML
    protected void searchSongs() {
        // sets text for header label
        headerLabel.setText(foundSongsList.getPlaylistName());

        // gets data (song's name) from this search bar
        String searchBarData = searchBar.getText();
        System.out.println(searchBarData);

        // add songs which was found to foundSongList
        foundSongsList.setSongList(playingPlaylist.findSongs(searchBarData));
        System.out.println(foundSongsList.getSongList());

        // then update list view
        updateListView(foundSongsList.getSongList());
    }

    /**
     * <p>Clears the {@link MediaPlayerController#foundSongsList} and {@link MediaPlayerController#listView} after searched </p>
     */
    @FXML
    protected void clearSearches() {
        // clears text in search bar
        searchBar.clear();
        // clears foundSongsList to free memory
        foundSongsList.getSongList().clear();

        // sets text for header label with header label of playing queue
        // if we don't set it, the label with display content of search header
        headerLabel.setText(playingPlaylist.getPlaylistName());
        /* Gets focus to current playing song:
         * Because when click to song item on search list view, the media will play and set focus to current playing song.
         * But this current list view only display searched song not songs on current playlist
         * Ex: - if search list view have three songIDs: 1, 9, 5
         * but songNumbers of its songID on current playlist are: 20, 9, 8 (shuffled);
         *     - if we selected song which has songID = 1, so songNumber of its is 20 (because of this list view only have three elements,
         * so cannot focus to song which has songNumber = 20 on list view, and if it can focus, the song was focused not like with selected song).
         *
         * So we need update focus and scroll to current playing song after clear searches.
         */
        updateListView(playingPlaylist.getSongList());
        listView.getFocusModel().focus(mediaPlayerManagement.getSongNumber());
        listView.scrollTo(mediaPlayerManagement.getSongNumber());
    }

    //-----------------------OTHER CONTROLLER'S METHODS------------------------//

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // create instance of MediaPlayerManagement
        mediaPlayerManagement = new MediaPlayerManagement(playingPlaylist);
        registerSubjects(playingPlaylist, mediaPlayerManagement);

        homePlaylist.addSongsFromDataFileToPlaylist(files, Files.PREVIOUS_IMPORTED_SONGS_DATA_FILE_PATH);

        if (!homePlaylist.getSongList().isEmpty()) {
            favoritePlaylist.addSongsFromDataFileToPlaylist(files, Files.FAVORITE_DATA_FILE_PATH);

            // set favorite for home playlist songs from previous favorite songs data
            for (Song song : favoritePlaylist.getSongList()
            ) {
                // get index of song in home playlist by song id in favorite playlist
                int index = homePlaylist.getSongIndexWithSongID(song.getSongID());
                // set favorite for this song in home playlist with isFavorite value of this song in favorite playlist
                homePlaylist.getSongList().get(index).setFavorite(song.isFavorite());
            }

            playingPlaylist.setPlaylistFrom(homePlaylist);
//            mediaPlayerManagement.getMediaTimer().registerMediaTimerObserver(this);

        } else {
            String title = "Add Song!";
            String message = "Hmmm! There is no song in your library, please click \"Open Folder\" button to continue!";
            showInformationAlert(title, message);
        }
    }

    /**
     * Add songs from Playlist to UI using {@link ListView}
     *
     * @param songList a list to display song list on UI, user can interact with it to choose music to play
     */
    public void updateListView(List<Song> songList) {
        ObservableList<String> observableListSongs = FXCollections.observableArrayList();

        // if list view has elements, clear its
        listView.getItems().clear();

        // adding items to list view
        for (Song song : songList) {
            observableListSongs.add("SongID: " + "\t" + song.getSongID() + "\t\t" + song.getSongName() + "\n\t\t\t\t" + song.getSongPath());
        }

        listView.setItems(observableListSongs);
        listView.scrollTo(0);
    }

    public void showInformationAlert(String alertTitle, String alertMessage) {

        if (alertTitle != null && alertMessage != null) {
            // sets type of alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            // sets title of alert
            alert.setTitle(alertTitle);
            // sets header text for alert
            alert.setHeaderText("INFORMATION");
            // sets context text (message) for alert
            alert.setContentText(alertMessage);
            // to show alert
            alert.show();
        }
    }
}