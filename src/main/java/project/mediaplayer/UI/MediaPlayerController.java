package project.mediaplayer.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;
import project.mediaplayer.model.*;

import java.io.File;

public class MediaPlayerController {

    @FXML
    private Button favoriteButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button libraryButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button openFolder;

    @FXML
    private Button settingButton;

    @FXML
    private Label headerLabel;

    private MainPlaylist mainPlaylist = new MainPlaylist(Playlists.MAIN_PLAYLIST);
    private FavoritePlaylist favoritePlaylist = new FavoritePlaylist(Playlists.FAVORITE_PLAYLIST);
    private CurrentPlaylist currentPlaylist = new CurrentPlaylist(Playlists.CURRENT_PLAYLIST);
    private ObservableList<String> songItems = FXCollections.observableArrayList();

//    private String splitFileName(String path) {
//        String result = "";
//        int a = path.lastIndexOf("/");
//        result = path.substring(a + 1);
//        return result;
//    }

    @FXML
    protected void chooseFile() {
        Files files = new Files();
//        ArrayList<File> files = new ArrayList<>();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Music Folder");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files *.wav", "*.wav"));
        File directory = directoryChooser.showDialog(null);
        if (directory != null) {
            for (File file : directory.listFiles()) {
                files.addFile(file);
            }
        }

//        for (File file : files.getFiles()
//        ) {
//            System.out.println(file.getPath());
//        }

        mainPlaylist.addSongs(files);

        favoritePlaylist.addSongToFavorite(mainPlaylist);

//        System.out.println(mainPlaylist);
//        System.out.println(favoritePlaylist);
        addToListView();


    }

    @FXML
    protected void playMusic() {

    }

    @FXML
    protected void mainPlaylistLView() {
        headerLabel.setText("Home");
        currentPlaylist.clearPlaylist();
        currentPlaylist.addFromOtherPlaylist(mainPlaylist);
        addToListView();
    }

    @FXML
    protected void favoritePlaylistLView() {
        headerLabel.setText("Favorite");
        currentPlaylist.clearPlaylist();
        currentPlaylist.addFromOtherPlaylist(favoritePlaylist);
        addToListView();
    }

    private void addToListView() {
        listView.getItems().clear();
        // set imported Song to list view

//        listView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
//            @Override
//            public ListCell<Song> call(ListView<Song> songListView) {
//                return new ListCell<Song>() {
//                    private Label songName = new Label();
//                    private Button favoriteBtn = new Button("Favorite");
//                    private Label songPath = new Label();
//                    private BorderPane bdPane = new BorderPane(songName, null, favoriteBtn, null, songPath);
//
//                    @Override
//                    protected void updateItem(Song song, boolean empty) {
//                        if (song == null || empty) {
//                            setText(null);
//                            setGraphic(null);
//                        } else {
//                            setText(song.getSongName());
//                            setGraphic(bdPane);
//                        }
//                    }
//                };
//            }
//        });
        for (Song song : mainPlaylist.getSongs()
        ) {
            String item = song.getSongName() + "\n" + song.getSongPath();
            songItems.add(item);
        }
//        songItems.addAll(mainPlaylist.getSongs());
//        System.out.println(mainPlaylist);
        System.out.println(songItems);
        listView.setItems(songItems);
    }

    @FXML
    private void getItemSelected() {
        System.out.println(listView.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void showAboutDialog() {
        Dialog<TextFlow> dialog = new Dialog<>();
        dialog.setTitle("About B&N Media Player");
        // button type for dialog
        ButtonType type = new ButtonType("Close", ButtonType.OK.getButtonData());
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.setContentText("This is a project which was written by Pham Cao Bang and Nguyen Phuong Nha." + "\n" +
        "This project using Java FX technical to building UI and use Clip to play Music");

        dialog.show();


    }
}