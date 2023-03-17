
package project.mediaplayer.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.mediaplayer.model.*;

import java.util.StringTokenizer;

public class MediaPlayerController {

    private final static String HOME_HEADER_TEXT = "Home";
    private final static String FAVORITE_LIST_HEADER_TEXT = "Favorites";
    private final static String PLAYING_QUEUE_HEADER_TEXT = "Playing Queue";
    private final static String SEARCH_HEADER_LABEL = "Search Result";
    private final MainPlaylist mainPlaylist = new MainPlaylist(Playlists.MAIN_PLAYLIST);
    private final FavoritePlaylist favoritePlaylist = new FavoritePlaylist(Playlists.FAVORITE_PLAYLIST);
    private final CurrentPlaylist currentPlaylist = new CurrentPlaylist(Playlists.CURRENT_PLAYLIST);
    private final FoundSongsList foundSongsList = new FoundSongsList(Playlists.FOUND_SONGS_PLAYLIST);
    private MediaPlayerManagement mediaPlayerManagement;

    private final MediaPlayerCommandInvoker mediaPlayerCommandInvoker = new MediaPlayerCommandInvoker();

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


    @FXML
    protected void playMedia() {
        mediaPlayerCommandInvoker.setPlayerCommand(new PlayMediaCommand(mediaPlayerManagement));
        mediaPlayerCommandInvoker.mediaPlayerAction();
    }

    @FXML

    protected void nextMedia() {
        mediaPlayerCommandInvoker.setPlayerCommand(new NextMediaCommand(mediaPlayerManagement));
        mediaPlayerCommandInvoker.mediaPlayerAction();
    }

    @FXML
    protected void previousMedia() {
        mediaPlayerCommandInvoker.setPlayerCommand(new PreviousMediaCommand(mediaPlayerManagement));
        mediaPlayerCommandInvoker.mediaPlayerAction();
    }

    @FXML
    protected void resetMedia() {
        mediaPlayerCommandInvoker.setPlayerCommand(new ResetMediaCommand(mediaPlayerManagement));
        mediaPlayerCommandInvoker.mediaPlayerAction();
    }


    /**
     * Choose music directory and add songs file from this directory to {@link MainPlaylist},
     * add songs from {@link MainPlaylist} to {@link CurrentPlaylist},
     * and create instance of {@link MediaPlayerController#mediaPlayerManagement}.
     * <p>
     * After create instance of {@link MediaPlayerController#mediaPlayerManagement} then {@link MediaPlayerManagement#initialPlayer()}
     */
    @FXML
    public void chooseFile() {
        Files files = new Files();
        files.chooseFileDir();

        mainPlaylist.addSongs(files);

        // add song from MainPlaylist
        currentPlaylist.addSongFromOtherPlaylist(mainPlaylist);

        // create instance of MediaPlayerManagement
        mediaPlayerManagement = new MediaPlayerManagement(currentPlaylist, listView, songNameLabel, songProgressBar, volumeSlider, favoriteSongButton);

//        favoritePlaylist.addSongToFavorite(mainPlaylist);
        System.out.println(currentPlaylist.getSongs().size());

        // initialize media player
        mediaPlayerManagement.initializePlayer();

        if (files.getListFiles().isEmpty()) {
            String title = "No song added";
            String message = "Seem a directory you chose didn't have any music file (*.mp3, *.aac, *.wav). \n" + "Please import it again!";
            showInfoDialog(title, message);
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
        volumeLabel.setText((int) volumeSlider.getValue() + "");
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
        int songNumber = currentPlaylist.getSongIndexWithSongID(songID);

        // sets song number which is recently found
        mediaPlayerManagement.setSongNumber(songNumber);
        // prepares media to play
        mediaPlayerManagement.prepareMedia();
        // play media with prepared media
        mediaPlayerManagement.playMedia();

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
     * Shuffle music list by shuffle {@link CurrentPlaylist}
     */
    @FXML
    protected void shuffleMusic() {
        headerLabel.setText(PLAYING_QUEUE_HEADER_TEXT);
        mediaPlayerManagement.shuffleMedia();
    }


    // show info dialog with title and message
    private void showInfoDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // set title for dialog title bar
        alert.setTitle(title);
        // set text for dialog header
        alert.setHeaderText("INFORMATION");
        // set text for dialog content
        alert.setContentText(message);
        alert.show();

    }


    /**
     * <p>This method is to use switch between playlist with buttons on left-side-bar.
     *  <ul>
     *      <li> First, get text of three buttons using getText() method.</li>
     *
     *      <li> Second, get text from button (buttonText) that user clicked.</li>
     *
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
        mediaPlayerManagement.stopMedia();

        // get text from button that user clicked
        String buttonText = ((Button) event.getSource()).getText();

        // switching to playlist by button that user clicked
        if (buttonText.equalsIgnoreCase(homeButtonText)) {
            headerLabel.setText(HOME_HEADER_TEXT);
            currentPlaylist.addSongFromOtherPlaylist(mainPlaylist);
            System.out.println(currentPlaylist.getSongs().size());

        }

        if (buttonText.equalsIgnoreCase(favoriteListButtonText)) {
            headerLabel.setText(FAVORITE_LIST_HEADER_TEXT);
            currentPlaylist.addSongFromOtherPlaylist(favoritePlaylist);
            System.out.println(currentPlaylist.getSongs().size());

        }

        if (buttonText.equalsIgnoreCase(playingQueueButtonText)) {
            headerLabel.setText(PLAYING_QUEUE_HEADER_TEXT);
            System.out.println(currentPlaylist.getSongs().size());
        }

        if (currentPlaylist.getSongs().isEmpty()) {
            String dialogTitle = "No song added";
            String dialogMessage = "This playlist has no song, please import its from right folder. " + "The file formats are acceptable are .mp3, .aac, .wav";
            showInfoDialog(dialogTitle, dialogMessage);
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
     * attribute isFavorite (true or false). And this state will be update when playing new song. See more at {@link MediaPlayerManagement#playMedia()} </p>
     */
    @FXML
    protected void addAndRemoveFavoriteSong() {

        // gets current playing song number and get current playing song from it
        int currentSongNumber = mediaPlayerManagement.getSongNumber();
        Song currentPlayingSong = currentPlaylist.getSongs().get(currentSongNumber);

        // if favoriteSongButton is selected then adding song
        if (favoriteSongButton.isSelected()) {
            // sets current playing song's attribute isFavorite by true
            currentPlayingSong.setFavorite(true);
            // then adds this song to favorite playlist
            favoritePlaylist.getSongs().add(currentPlayingSong);


        }
        // if favoriteSongButton is selected then adding song
        else {
            // sets current playing song's attribute isFavorite by false
            currentPlayingSong.setFavorite(false);
            // then removes this song to favorite playlist
            favoritePlaylist.getSongs().remove(currentPlayingSong);

        }

        System.out.println(favoritePlaylist);
        System.out.println(currentPlayingSong);
        System.out.println(favoritePlaylist.getSongs().size());
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
        System.out.println("hi");
    }


    /**
     * <p>Searches songs from {@link CurrentPlaylist} with song's name is got from {@link MediaPlayerController#searchBar}.</p>
     * <p>After searched then update songs list view {@link MediaPlayerController#listView}</p>
     */
    @FXML
    protected void searchSongs() {
        // sets text for header label
        headerLabel.setText(SEARCH_HEADER_LABEL);

        // gets data (song's name) from this search bar
        String searchBarData = searchBar.getText();
        System.out.println(searchBarData);

        // add songs which was found to foundSongList
        foundSongsList.addFoundedSongsToList(currentPlaylist.findSongs(searchBarData));
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
        foundSongsList.getSongs().clear();

        // updates list view with currentPlaylist songs
        currentPlaylist.updateListView(listView);

        // sets text for header label with header label of playing queue
        // if we don't set it, the label with display content of search header
        headerLabel.setText(PLAYING_QUEUE_HEADER_TEXT);
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



