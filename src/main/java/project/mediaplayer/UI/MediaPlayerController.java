
package project.mediaplayer.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.mediaplayer.model.*;

public class MediaPlayerController {

    private final static String HOME_HEADER_TEXT = "Home";
    private final static String FAVORITE_LIST_HEADER_TEXT = "Favorites";
    private final static String PLAYING_QUEUE_HEADER_TEXT = "Playing Queue";
    private final MainPlaylist mainPlaylist = new MainPlaylist(Playlists.MAIN_PLAYLIST);
    private final FavoritePlaylist favoritePlaylist = new FavoritePlaylist(Playlists.FAVORITE_PLAYLIST);
    private final CurrentPlaylist currentPlaylist = new CurrentPlaylist(Playlists.CURRENT_PLAYLIST);
    private final ObservableList<String> songItems = FXCollections.observableArrayList();
    private MediaPlayerManagement mediaPlayerManagement;

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
    //----------------------------------------------------------//


    @FXML
    protected void playMedia() {
        mediaPlayerManagement.playMedia();
    }

    @FXML
    protected void nextMedia() {
        mediaPlayerManagement.nextMedia();
    }

    @FXML
    protected void previousMedia() {
        mediaPlayerManagement.previousMedia();
    }

    @FXML
    protected void resetMedia() {
        mediaPlayerManagement.resetMedia();
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
        mediaPlayerManagement = new MediaPlayerManagement(currentPlaylist, listView, songNameLabel, songProgressBar, volumeSlider);

//        favoritePlaylist.addSongToFavorite(mainPlaylist);
        System.out.println(currentPlaylist.getSongs().size());

        // initialize media player
        mediaPlayerManagement.initialPlayer();

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
        // get song number from clicked item index on list view
        int songNumber = listView.getSelectionModel().getSelectedIndex();
        // set song number
        mediaPlayerManagement.setSongNumber(songNumber);
        // prepare media to play
        mediaPlayerManagement.prepareMedia();

        System.out.println(songNumber);
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
        mediaPlayerManagement.shuffleMusic();
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
        mediaPlayerManagement.initialPlayer();
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


    // add songs to favorite playlist
    // developing
    @FXML
    protected void addFavoriteSong() {
        int songNumber = mediaPlayerManagement.getSongNumber();
        Song currentPlayingSong = currentPlaylist.getSongs().get(songNumber);

        favoriteSongButton.setSelected(currentPlayingSong.isFavorite());


        if (favoriteSongButton.isSelected()) {
            currentPlayingSong.setFavorite(true);
            favoritePlaylist.getSongs().add(currentPlayingSong);

        } else {
            currentPlayingSong.setFavorite(false);
            favoritePlaylist.getSongs().remove(currentPlayingSong);
        }
        System.out.println(currentPlayingSong);
        System.out.println(favoritePlaylist.getSongs().size());
    }


}



