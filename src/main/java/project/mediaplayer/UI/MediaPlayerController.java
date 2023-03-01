
package project.mediaplayer.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import project.mediaplayer.model.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class MediaPlayerController implements Initializable {

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

    // Test ----------------------------------------------------------------
    @FXML
    private Pane pane;
    @FXML
    private Label songNameLabel;
    @FXML
    private ListView<String> listView;
    @FXML
    private ToggleButton previousButton;
    @FXML
    private ToggleButton playButton;
    @FXML
    private ToggleButton nextButton;
    //    @FXML
//    private ToggleButton resetButton;
    private File directory;
    private File[] files;
    private ArrayList<File> songs;
    private int songNumber;
    private Media media;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(currentPlaylist.getPlaylistName());

        songs = new ArrayList<File>();
        directory = new File("src/main/resources/music");
        files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                songs.add(file);
                System.out.println(file);
            }
        }

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        songNameLabel.setText(songs.get(songNumber).getName());
    }


    public void playMedia() {
        if (playButton.isSelected()) {
            System.out.println("Checkbox is selected");
            mediaPlayer.play();
        } else if (!playButton.isSelected()) {
            System.out.println("Checkbox is not selected");
            mediaPlayer.pause();
        }
    }

//    public void resetMedia() {
//        mediaPlayer.seek(Duration.seconds(0));
//    }

    public void previousMedia() {
        if (songNumber > 0) {
            songNumber--;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songNameLabel.setText(songs.get(songNumber).getName());
            playMedia();
        } else {
            songNumber = songs.size() - 1;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songNameLabel.setText(songs.get(songNumber).getName());
            playMedia();
        }
    }

    public void nextMedia() {
        if (songNumber < songs.size() - 1) {
            songNumber++;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songNameLabel.setText(songs.get(songNumber).getName());
            playMedia();
        } else {
            songNumber = 0;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songNameLabel.setText(songs.get(songNumber).getName());
            playMedia();
        }
    }

    // End Test ------------------------------------------------------------
    @FXML
    protected void chooseFile() {
        Files files = new Files();
        files.clearAll();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Music Folder");
        File directory = directoryChooser.showDialog(null);
        if (directory != null) {
            for (File file : directory.listFiles()) {
                files.addFile(file);
            }
        }

        mainPlaylist.addSongs(files);
        favoritePlaylist.addSongToFavorite(mainPlaylist);
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

    private void addToListView() {
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
        String result= "";
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




}