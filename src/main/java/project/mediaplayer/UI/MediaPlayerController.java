package project.mediaplayer.UI;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.mediaplayer.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

public class MediaPlayerController {

    private final static String HOME_HEADER_TEXT = "Home";
    private final static String FAVORITE_LIST_HEADER_TEXT = "Favorites";
    private final static String PLAYING_QUEUE_HEADER_TEXT = "Playing Queue";
    private final MainPlaylist mainPlaylist = new MainPlaylist(Playlists.MAIN_PLAYLIST);
    private final FavoritePlaylist favoritePlaylist = new FavoritePlaylist(Playlists.FAVORITE_PLAYLIST);
    private final CurrentPlaylist currentPlaylist = new CurrentPlaylist(Playlists.CURRENT_PLAYLIST);
    private final ObservableList<String> songItems = FXCollections.observableArrayList();

    private final ArrayList<File> songFiles = new ArrayList<>();

    @FXML
    private Button openFolder;
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
    private ToggleButton previousButton;
    @FXML
    private ToggleButton playButton;
    @FXML
    private ToggleButton nextButton;
    @FXML
    private ToggleButton resetButton;
    @FXML
    private Button playingQueueButton;
    @FXML
    private Button aboutButton;
    @FXML
    private ToggleButton shuffleButton;
    @FXML
    private ToggleButton favoriteSongButton;
    @FXML
    private ToggleButton volumeButton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label volumeLabel;

    private Timer timer;
    private TimerTask task;
    private boolean running;
    private int songNumber;
    private Media media;
    private MediaPlayer mediaPlayer;


    public void playMedia() {

        // open choose directory of music if current playlist have nothing
        if (currentPlaylist.getSongs().isEmpty()) chooseFile();

        songNameLabel.setText(currentPlaylist.getSongs().get(songNumber).getSongName());
        // focus to current song is playing in list view
        listView.getFocusModel().focus(songNumber);
        // scroll to current song is playing in list view
        listView.scrollTo(songNumber);

        // set volume for media player when choose a new song with value of volumeSlider
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        System.out.println(mediaPlayer.getVolume());

        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            cancelTimer();

        } else mediaPlayer.play();

        // begin timer for progress bar
        // start from 0 to total duration of song then stop
        beginTimer();

    }

    public void resetMedia() {
        songProgressBar.setProgress(0);
        songNameLabel.setText(songFiles.get(songNumber).getName());
        mediaPlayer.seek(Duration.seconds(0));
    }

    public void previousMedia() {
        if (songNumber > 0) {
            songNumber--;
            mediaPlayer.stop();
            if (running) {
                cancelTimer();
            }
            media = new Media(songFiles.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);


            playMedia();
        } else {
            songNumber = songFiles.size() - 1;
            mediaPlayer.stop();
            if (running) {

                cancelTimer();
            }
            media = new Media(songFiles.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            listView.getFocusModel().focus(songNumber);
            playMedia();
        }
    }

    public void nextMedia() {
        if (songNumber < songFiles.size() - 1)
            songNumber++;
        else
            songNumber = 0;

        mediaPlayer.stop();
        if (running) {
            cancelTimer();
        }
        media = new Media(songFiles.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        playMedia();
    }

    // End Play ------------------------------------------------------------


    public void beginTimer() {

        timer = new Timer();

        task = new TimerTask() {

            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        running = true;
                        double current = mediaPlayer.getCurrentTime().toSeconds();
                        double end = media.getDuration().toSeconds();
                        songProgressBar.setProgress(current / end);

                        if (current / end == 1) {
                            cancelTimer();
                            resetMedia();
                            nextMedia();
                        }
                    }
                });
            }
        };

        // each 1000ms (1s), run code in run() method (TimerTask) to calculate value to update progress bar
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelTimer() {

        running = false;
        timer.cancel();
    }

    @FXML
    protected void chooseFile() {
        Files files = new Files();
        files.chooseFileDir();
        mainPlaylist.addSongs(files);
        prepareMusicList();

        if (files.getListFiles().isEmpty()) {
            String title = "No song added";
            String message = "Seem a directory you chose didn't have any music file (*.mp3, *.aac, *.wav). \n" + "Please import it again!";
            showInfoDialog(title, message);
        }

    }
//
//    @FXML
//    protected void mainPlaylistLView() {
//        currentPlaylist.addSongFromOtherPlaylist(mainPlaylist);
//        headerLabel.setText("Home");
//        addToListView();
//    }
//
//    @FXML
//    protected void favoritePlaylistLView() {
//        currentPlaylist.addSongFromOtherPlaylist(favoritePlaylist);
//        headerLabel.setText("Favorite");
//        addToListView();
//    }

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
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        // set volume value from slider
        volumeLabel.setText((int) volumeSlider.getValue() + "");
    }


    // set imported Song to list view
    public void addToListView() {
        // if list view has elements, clear its
        listView.getItems().clear();

        // adding items to list view
        for (Song song : currentPlaylist.getSongs()) {
            songItems.add(song.getSongName() + "\n" + song.getSongPath());
        }

//        System.out.println(mainPlaylist);
//        System.out.println(songItems);
        listView.setItems(songItems);
    }

    @FXML
    private void selectedListViewItem() {

        mediaPlayer.stop();

        songNumber = listView.getSelectionModel().getSelectedIndex();
        media = new Media(songFiles.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        System.out.println(songNumber);
        playMedia();
//        songPlayer = new SongPlayer(slitPathLView(item), 1);
//        songPlayer.play();
//        songNameLabel.setText(slitPathLView(item));
    }

    // open About Stage
    @FXML
    private void openAbout() throws Exception {
        Stage stage = new Stage();
        AboutApplication application = new AboutApplication();
        application.start(stage);
    }

    // get song name from list view item
    private String getSongNameFromLView(String str) {
        String result = "";
        StringTokenizer tokenizer = new StringTokenizer(str, "\n");
        result = tokenizer.nextToken();
        return result;
    }

    // get song's path from list view item
    private String getSongPathFromLView(String str) {
        String result = "";
        StringTokenizer tokenizer = new StringTokenizer(str, "\n");
        tokenizer.nextToken();
        result = tokenizer.nextToken();
        return result;
    }

    @FXML
    protected void shuffleMusic() {
        mediaPlayer.stop();
        songNumber = 0;
        currentPlaylist.shufflePlaylist();
        // add songs to List View from Current Playlist
        addToListView();
        //
        initialPlayer();
        mediaPlayer.play();
    }

    private void initialPlayer() {
        // clear songFiles if it already has elements
        songFiles.clear();
        // add songs to songFiles from Current Playlist
        for (Song song : currentPlaylist.getSongs()) {
            File songFile = new File(song.getSongPath());
            songFiles.add(songFile);
        }

        media = new Media(songFiles.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songNameLabel.setText(currentPlaylist.getSongs().get(songNumber).getSongName());
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

    private void prepareMusicList() {

        currentPlaylist.addSongFromOtherPlaylist(mainPlaylist);
//        favoritePlaylist.addSongToFavorite(mainPlaylist);
        System.out.println(currentPlaylist.getSongs().size());

        initialPlayer();
        addToListView();
    }

    @FXML
    protected void switchPlaylist(ActionEvent event) {
        final String homeButtonText = homeButton.getText();
        final String favoriteListButtonText = favoriteListButton.getText();
        final String playingQueueButtonText = playingQueueButton.getText();

        mediaPlayer.stop();
        String buttonText = ((Button) event.getSource()).getText();

        if (buttonText.equalsIgnoreCase(homeButtonText)) {
            headerLabel.setText(HOME_HEADER_TEXT);
            currentPlaylist.addSongFromOtherPlaylist(mainPlaylist);
            addToListView();
            System.out.println(currentPlaylist.getSongs().size());
        }

        if (buttonText.equalsIgnoreCase(favoriteListButtonText)) {
            headerLabel.setText(FAVORITE_LIST_HEADER_TEXT);
            currentPlaylist.addSongFromOtherPlaylist(favoritePlaylist);
            addToListView();
            System.out.println(currentPlaylist.getSongs().size());
        }

        if (buttonText.equalsIgnoreCase(playingQueueButtonText)) {
            headerLabel.setText(PLAYING_QUEUE_HEADER_TEXT);
//            currentPlaylist.addSongFromOtherPlaylist();
            addToListView();
            System.out.println(currentPlaylist.getSongs().size());
        }

        if (currentPlaylist.getSongs().isEmpty()) {
            String dialogTitle = "No song added";
            String dialogMessage = "This playlist has no song, please import its from right folder. " + "The file formats are acceptable are .mp3, .aac, .wav";
            showInfoDialog(dialogTitle, dialogMessage);
        }

        // initial player after switched between playlists
        initialPlayer();
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

    @FXML
    protected void addFavoriteSong() {
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



