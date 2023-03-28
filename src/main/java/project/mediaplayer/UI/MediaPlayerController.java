
package project.mediaplayer.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.mediaplayer.model.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class MediaPlayerController implements Initializable {

    private final static String HOME_HEADER_TEXT = "Home";
    private final static String FAVORITE_LIST_HEADER_TEXT = "Favorites";
    private final static String PLAYING_QUEUE_HEADER_TEXT = "Playing Queue";
    private final static String SEARCH_HEADER_LABEL = "Search Result";

    private final Playlist homePlaylist = new HomePlaylist(Playlist.HOME_PLAYLIST_NAME, new NonShufflePlaylist());
    private final Playlist favoritePlaylist = new FavoritePlaylist(Playlist.FAVORITE_PLAYLIST_NAME, new NonShufflePlaylist());
    private final Playlist playingPlaylist = new PlayingPlaylist(Playlist.PLAYING_PLAYLIST_NAME, new NonShufflePlaylist());
    private final Playlist foundSongsList = new FoundSongList(Playlist.FOUND_SONGS_PLAYLIST_NAME, new ShufflePlaylist());
    private MediaPlayerManagement mediaPlayerManagement;
    private final MediaPlayerCommandInvoker mediaPlayerCommandInvoker = new MediaPlayerCommandInvoker();
    private final Files files = new Files();

    //----------------------------------------------------------//
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
    @FXML
    private ToggleButton clearSearchesButton;

    //----------------------------------------------------------//


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
        files.readSongsFromDataFile(Files.PREVIOUS_IMPORTED_SONGS_DATA_FILE_PATH, homePlaylist);

        if (!homePlaylist.getSongList().isEmpty()) {
            playingPlaylist.setPlaylistFrom(homePlaylist);
//            System.out.println("Home Playlist: " + homePlaylist.getSongList());

            files.readSongsFromDataFile(Files.FAVORITE_DATA_FILE_PATH, favoritePlaylist);

            // create instance of MediaPlayerManagement
            mediaPlayerManagement = new MediaPlayerManagement(playingPlaylist, listView, songNameLabel,
                    favoriteSongButton, shuffleButton, songProgressBar, volumeSlider);
            mediaPlayerManagement.initializePlayer();
        } else {
            String title = "Add Song!";
            String message = "Hmmm! There is no song in your library, please click \"Open Folder\" button to continue!";
            AlertUtils.showInformationAlert(title, message);
        }
    }

    @FXML
    protected void playMedia() {
        mediaPlayerCommandInvoker.setMediaPlayerCommand(new PlayPauseMediaCommand(mediaPlayerManagement));
        mediaPlayerCommandInvoker.commandAction();
    }

    @FXML
    protected void nextMedia() {
        mediaPlayerCommandInvoker.setMediaPlayerCommand(new NextMediaCommand(mediaPlayerManagement));
        mediaPlayerCommandInvoker.commandAction();
    }

    @FXML
    protected void previousMedia() {
        mediaPlayerCommandInvoker.setMediaPlayerCommand(new PreviousMediaCommand(mediaPlayerManagement));
        mediaPlayerCommandInvoker.commandAction();
    }

    @FXML
    protected void resetMedia() {
        mediaPlayerCommandInvoker.setMediaPlayerCommand(new ResetMediaCommand(mediaPlayerManagement));
        mediaPlayerCommandInvoker.commandAction();
    }


    /**
     * Shuffle music list by shuffle playlist
     */
    @FXML
    protected void shuffleMedia() {
        headerLabel.setText(PLAYING_QUEUE_HEADER_TEXT);
        mediaPlayerCommandInvoker.setMediaPlayerCommand(new ShuffleMediaCommand(mediaPlayerManagement));
        mediaPlayerCommandInvoker.commandAction();
    }


    /**
     * Choose music directory and add songs file from this directory to {@link HomePlaylist},
     * add songs from {@link HomePlaylist} to {@link PlayingPlaylist},
     * and create instance of {@link MediaPlayerController#mediaPlayerManagement}.
     * <p>
     * After create instance of {@link MediaPlayerController#mediaPlayerManagement} then {@link MediaPlayerManagement#initializePlayer()}
     */
    @FXML
    public void chooseFile() {
        Files files = new Files();
        files.chooseFileDir();

        if (files.getListFiles().isEmpty()) {
            String title = "No song added";
            String message = "Seem a directory you chose didn't have any music file (*.mp3, *.aac, *.wav). \n" + "Please import it again!";
            AlertUtils.showInformationAlert(title, message);
        } else {

            homePlaylist.addSongsFromFilesToPlaylist(files);

            // add song from MainPlaylist
            playingPlaylist.setPlaylistFrom(homePlaylist);

            // create instance of MediaPlayerManagement
            mediaPlayerManagement = new MediaPlayerManagement(playingPlaylist, listView, songNameLabel,
                    favoriteSongButton, shuffleButton, songProgressBar, volumeSlider);

//        favoritePlaylist.addSongToFavorite(mainPlaylist);
            System.out.println(homePlaylist.getSongList().size());

            // initialize media player
            mediaPlayerManagement.initializePlayer();
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
        // clear text of label if it exists
        volumeLabel.setText("");
        // get value from volume slider and set to media player volume
        mediaPlayerManagement.setVolume(volumeSlider.getValue() * 0.01);
        // set volume value from slider
        volumeLabel.setText((int) volumeSlider.getValue() + "%");
    }


    /**
     * When selects song item on list view, this method will be call and get index of this selected item,
     * then set the index to songNumber of {@link MediaPlayerManagement} class to play new selected song
     */
    @FXML
    private void selectedListViewItem() {
        // gets Song information selected from list view
        String selectedSongItem = listView.getSelectionModel().getSelectedItem();

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
        mediaPlayerManagement.setMediaPlayerControl(new PlayPauseMedia());
        mediaPlayerManagement.doActionControl();

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
//
        // get text from three buttons
        final String homeButtonText = homeButton.getText();
        final String favoriteListButtonText = favoriteListButton.getText();
        final String playingQueueButtonText = playingQueueButton.getText();

        // stop media player
        mediaPlayerManagement.setMediaPlayerControl(new StopMedia());
        mediaPlayerManagement.doActionControl();

        // get text from button that user clicked
        String buttonText = ((Button) event.getSource()).getText();

        // switching to playlist by button that user clicked
        if (buttonText.equalsIgnoreCase(homeButtonText)) {
            headerLabel.setText(homePlaylist.getPlaylistName());
            playingPlaylist.setPlaylistFrom(homePlaylist);
            System.out.println(homePlaylist.getSongList().size());

        }

        if (buttonText.equalsIgnoreCase(favoriteListButtonText)) {
            headerLabel.setText(favoritePlaylist.getPlaylistName());
            playingPlaylist.setPlaylistFrom(favoritePlaylist);

        }

        if (buttonText.equalsIgnoreCase(playingQueueButtonText)) {
            headerLabel.setText(playingPlaylist.getPlaylistName());
            System.out.println(playingPlaylist.getSongList().size());
        }

        if (playingPlaylist.getSongList().isEmpty()) {
            String dialogTitle = "No song added";
            String dialogMessage = "This playlist has no song, please import its from right folder. " + "The file formats are acceptable are .mp3, .aac, .wav";
            AlertUtils.showInformationAlert(dialogTitle, dialogMessage);
        } else
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
        if (volumeSlider.getValue() > 0) {
            volumeSlider.setValue(0);
        } else {
            volumeSlider.setValue(100);
        }
        changeVolume();
    }

    /**
     * <p>Adds and removes current playing song to {@link FavoritePlaylist} with {@link MediaPlayerController#favoriteSongButton}. </p>
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

        // if favoriteSongButton is selected then adding song
        if (favoriteSongButton.isSelected()) {
            // sets current playing song's attribute isFavorite by true
            currentPlayingSong.setFavorite(true);
            // then adds this song to favorite playlist
            if (!favoritePlaylist.getSongList().contains(currentPlayingSong))
                favoritePlaylist.getSongList().add(currentPlayingSong);
        }
        // if favoriteSongButton is selected then adding song
        else {
            // sets current playing song's attribute isFavorite by false
            currentPlayingSong.setFavorite(false);
            // then removes this song to favorite playlist
            favoritePlaylist.getSongList().remove(currentPlayingSong);
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
     * <p>Searches songs from {@link PlayingPlaylist} with song's name is got from {@link MediaPlayerController#searchBar}.</p>
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
        System.out.println(foundSongsList);

        // then update list view
        foundSongsList.updateListView(listView);
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

        // updates list view with currentPlaylist songs
        playingPlaylist.updateListView(listView);

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
        listView.getFocusModel().focus(mediaPlayerManagement.getSongNumber());
        listView.scrollTo(mediaPlayerManagement.getSongNumber());
    }


}



