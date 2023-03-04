
package project.mediaplayer.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
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

    @FXML
    private Button aboutButton;
    @FXML
    private Button favoriteButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button libraryButton;

//    @FXML
//    private ListView<String> listView;

    @FXML
    private Button openFolder;

    @FXML
    private Button settingButton;

    @FXML
    private Label headerLabel;

    @FXML
    private ToggleButton favoriteSongButton;
//    @FXML
//    private  ToggleButton previousButton;
//    @FXML
//    private ToggleButton playButton;
//    @FXML
//    private ToggleButton nextButton;

//    @FXML
//    private Label songNameLabel;

    private final MainPlaylist mainPlaylist = new MainPlaylist(Playlists.MAIN_PLAYLIST);
    private final FavoritePlaylist favoritePlaylist = new FavoritePlaylist(Playlists.FAVORITE_PLAYLIST);
    private final CurrentPlaylist currentPlaylist = new CurrentPlaylist(Playlists.CURRENT_PLAYLIST);
    private final ObservableList<String> songItems = FXCollections.observableArrayList();
    private SongPlayer songPlayer;
    private int playState;

    @FXML
    private Pane pane;
    @FXML
    private Label songNameLabel;
    @FXML
    private ListView<String> listView;
    @FXML
    private ProgressBar songProgressBar;

    @FXML
    private ToggleButton previousButton;
    @FXML
    private ToggleButton playButton;
    @FXML
    private ToggleButton nextButton;
    @FXML
    private ToggleButton resetButton;
    private Timer timer;
    private TimerTask task;
    private boolean running;
    private File directory;
    private File[] files;
    private final ArrayList<File> songFiles = new ArrayList<>();
    private int songNumber;
    private Media media;
    private MediaPlayer mediaPlayer;

    private final Task<Void> chooseFileTask = new Task<Void>() {
        @Override
        protected Void call() throws Exception {

            return null;
        }
    };

//    Task<Void> initializeMediaTask = new Task<Void>() {
//        @Override
//        protected Void call() throws Exception {
//            if (currentPlaylist.getSongs().size() > 0) {
//                System.out.println(currentPlaylist.getPlaylistName());
//////
//////            songs = new ArrayList<File>();
//////            directory = new File("src/main/resources/music");
//////            files = directory.listFiles();
//////
//////            if (files != null) {
//////                for (File file : files) {
//////                    songs.add(file);
//////                    System.out.println(file);
//////                }
//////            }
////
////
//                media = new Media(songs.get(songNumber).getSongPath());
//                mediaPlayer = new MediaPlayer(media);
//
//                songNameLabel.setText(songs.get(songNumber).getSongName());
//            }
//            return null;
//        }
//    }
//        ;


//        if (currentPlaylist.getSongs().size() > 0) {
//            notify();
//            System.out.println(currentPlaylist.getPlaylistName());
//
//            songs = new ArrayList<File>();
//            directory = new File("src/main/resources/music");
//            files = directory.listFiles();
//
//            if (files != null) {
//                for (File file : files) {
//                    songs.add(file);
//                    System.out.println(file);
//                }
//            }
//7
//            media = new Media(songs.get(songNumber).getSongPath());
//            mediaPlayer = new MediaPlayer(media);
//
//            songNameLabel.setText(songs.get(songNumber).getSongName());
//        }
//
//    }


    public void playMedia() {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            cancelTimer();

        } else
            mediaPlayer.play();
        beginTimer();
    }

    public void resetMedia() {
        songProgressBar.setProgress(0);
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
            songNameLabel.setText(currentPlaylist.getSongs().get(songNumber).getSongName());
            playMedia();
        } else {
            songNumber = songFiles.size() - 1;
            mediaPlayer.stop();
            if (running) {

                cancelTimer();
            }
            media = new Media(songFiles.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songNameLabel.setText(currentPlaylist.getSongs().get(songNumber).getSongName());
            playMedia();
        }
    }

    public void nextMedia() {
        if (songNumber < songFiles.size() - 1) {
            songNumber++;
            mediaPlayer.stop();
            if (running) {
                cancelTimer();
            }
            media = new Media(songFiles.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songNameLabel.setText(currentPlaylist.getSongs().get(songNumber).getSongName());
            playMedia();
        } else {
            songNumber = 0;
            mediaPlayer.stop();
            media = new Media(songFiles.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songNameLabel.setText(currentPlaylist.getSongs().get(songNumber).getSongName());
            playMedia();
        }
    }

    // End Play ------------------------------------------------------------
    public void beginTimer() {

        timer = new Timer();

        task = new TimerTask() {

            public void run() {

                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setProgress(current / end);

                if (current / end == 1) {

                    cancelTimer();
                }
            }
        };

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
        currentPlaylist.addSongFromOtherPlaylist(mainPlaylist);
        favoritePlaylist.addSongToFavorite(mainPlaylist);
        System.out.println(currentPlaylist.getSongs().size());
        for (Song song : currentPlaylist.getSongs()
        ) {
            File songFile = new File(song.getSongPath());
            songFiles.add(songFile);

        }

        if (currentPlaylist.getSongs().isEmpty()) {
            String title = "No song added";
            String message = "Seem a directory you chose didn't have any music file (*.mp3, *.aac, *.wav). \n" +
                    "Please import it again!";
            showInfoDialog(title, message);
        }

        initialPlayer();
        addToListView();
    }

    @FXML
    protected void mainPlaylistLView() {
        currentPlaylist.addSongFromOtherPlaylist(mainPlaylist);
        headerLabel.setText("Home");
        addToListView();
    }

    @FXML
    protected void favoritePlaylistLView() {
        currentPlaylist.addSongFromOtherPlaylist(favoritePlaylist);
        headerLabel.setText("Favorite");
        addToListView();
    }

    public void addToListView() {
        // set imported Song to list view
        listView.getItems().clear();

        for (Song song : mainPlaylist.getSongs()
        ) {
            songItems.add(song.getSongName() + "\n" + song.getSongPath());
        }

//        System.out.println(mainPlaylist);
//        System.out.println(songItems);
        listView.setItems(songItems);
    }

    @FXML
    private void selectedListItem() {
        String item = listView.getSelectionModel().getSelectedItem();
        songNameLabel.setText(splitSongNameLView(item));
        mediaPlayer.stop();
        songNumber = listView.getSelectionModel().getSelectedIndex();
        media = new Media(songFiles.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        System.out.println(songNumber);
        mediaPlayer.play();
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

    // slit song name from list view item
    private String splitSongNameLView(String str) {
        String result = "";
        StringTokenizer tokenizer = new StringTokenizer(str, "\n");
        result = tokenizer.nextToken();
        return result;
    }

    // split song's path from list view item
    private String slitPathLView(String str) {
        String result = "";
        StringTokenizer tokenizer = new StringTokenizer(str, "\n");
        tokenizer.nextToken();
        result = tokenizer.nextToken();
        return result;
    }


//  private MediaPlayer mediaInitialize() {
//        if (currentPlaylist.getSongs().size() > 0) {
//            System.out.println(currentPlaylist.getPlaylistName());
////
////            songs = new ArrayList<File>();
////            directory = new File("src/main/resources/music");
////            files = directory.listFiles();
////
////            if (files != null) {
////                for (File file : files) {
////                    songs.add(file);
////                    System.out.println(file);
////                }
////            }
//
//
//            media = new Media(songs.get(songNumber).getSongPath());
//            mediaPlayer = new MediaPlayer(media);
//
//            songNameLabel.setText(songs.get(songNumber).getSongName());
//
//        }
//        return mediaPlayer;
//
//    }

//    public static void main(String[] args) {
//
//    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        if (currentPlaylist.getSongs().size() > 0) {
//            System.out.println(currentPlaylist.getPlaylistName());
////
////            songs = new ArrayList<File>();
////            directory = new File("src/main/resources/music");
////            files = directory.listFiles();
////
////            if (files != null) {
////                for (File file : files) {
////                    songs.add(file);
////                    System.out.println(file);
////                }
////            }
//
//
//            media = new Media(songs.get(songNumber).getSongPath());
//            mediaPlayer = new MediaPlayer(media);
//
//            songNameLabel.setText(songs.get(songNumber).getSongName());
//        }
//    }

    private void initialPlayer() {
        media = new Media(songFiles.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songNameLabel.setText(currentPlaylist.getSongs().get(songNumber).getSongName());
        songProgressBar.setStyle("-fx-accent: #2446e0;");
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
}



