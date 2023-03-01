package project.mediaplayer.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.mediaplayer.model.*;

import java.util.StringTokenizer;

public class MediaPlayerController {

    @FXML
    private Button aboutButton;
    @FXML
    private Button favoriteButton;

    @FXML
    private Button homeButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button openFolder;

    @FXML
    private Button settingButton;

    @FXML
    private Label headerLabel;

    @FXML
    private ToggleButton favoriteSongButton;
    private final MainPlaylist mainPlaylist = new MainPlaylist(Playlists.MAIN_PLAYLIST);
    @FXML
    private ToggleButton playButton;
    @FXML
    private ToggleButton nextButton;

    @FXML
    private Label songNameLabel;
    private final FavoritePlaylist favoritePlaylist = new FavoritePlaylist(Playlists.FAVORITE_PLAYLIST);
    private final CurrentPlaylist currentPlaylist = new CurrentPlaylist(Playlists.CURRENT_PLAYLIST);
    private final ObservableList<String> songItems = FXCollections.observableArrayList();
    @FXML
    private ToggleButton previousButton;
    private SongPlayer songPlayer;
    private int playState;

    @FXML
    protected void chooseFile() {
        Files files = new Files();
//        files.clearAll();
////        ArrayList<File> files = new ArrayList<>();
//        DirectoryChooser directoryChooser = new DirectoryChooser();
//        directoryChooser.setTitle("Open Music Folder");
////        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files *.wav", "*.wav"));
//        File directory = directoryChooser.showDialog(null);
//        if (directory != null) {
//            for (File file : directory.listFiles()) {
//                files.addFile(file);
//            }
//        }
        files.chooseFileDir();
        if (files.getListFiles().size() == 0) {
            String title = "No song added";
            String message = "Seem you were choosed a directory with have no music file or empty directory." +
                    " Please chooses an appropriate directory!";
            showWarningDialog(title, message);
        } else {
            mainPlaylist.addSongs(files);
            favoritePlaylist.addSongToFavorite(mainPlaylist);
            addToListView();
        }
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

        System.out.println(mainPlaylist);
        System.out.println(songItems);
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

//    public static Clip PlayMusic(String location) {
//        try {
//            File musicPath = new File(location);
//            if (musicPath.exists()) {
//                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioInput);
//                clip.start();
//                return clip;
//            } else {
//                System.out.println("Can't find file");
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }
//    @FXML
//    protected void playStop() {
//        for (Song song : currentPlaylist.getSongs()
//        ) {
//            songItems.add(song.getSongName() + "\n" + song.getSongPath());
//        }
//        try {
//            for (int i = 0; i < songItems.size(); i++) {
//                System.out.println("Playing " + songItems.get(i));
//                Clip currentClip = PlayMusic(songItems.get(i));
//                while (currentClip.getMicrosecondLength() != currentClip.getMicrosecondPosition()) {
//
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        if (playButton.isFocused() == true) {
//            playState = 1;
//            currentPlaylist.playInCurrentPlaylist(playState);
//        } else  if (playButton.isFocused() == false){
//            playState = 2;
//        }
//        currentPlaylist.playInCurrentPlaylist(1);
//    }

    // this method is to show a dialog with title and message
    private void showWarningDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText("Warning");
        alert.setContentText(message);
        alert.show();
    }


}