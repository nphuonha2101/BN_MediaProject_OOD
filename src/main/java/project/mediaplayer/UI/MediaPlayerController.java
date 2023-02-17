package project.mediaplayer.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;
import project.mediaplayer.model.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MediaPlayerController {

    @FXML
    private Button favoriteButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button libraryButton;

    @FXML
    private ListView<Song> listView;

    @FXML
    private Button openFolder;

    @FXML
    private Button settingButton;

    @FXML
    private Label headerLabel;

    private MainPlaylist mainPlaylist = new MainPlaylist(Playlists.MAIN_PLAYLIST);
    private FavoritePlaylist favoritePlaylist = new FavoritePlaylist(Playlists.FAVORITE_PLAYLIST);
    private ObservableList<Song> songItems = FXCollections.observableArrayList();

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
    protected void mainPlaylistLView() {

    }

    private void addToListView() {
        // set imported Song to list view

        listView.setCellFactory(new Callback<ListView<Song>, ListCell<Song>>() {
            @Override
            public ListCell<Song> call(ListView<Song> songListView) {
                return new ListCell<Song>() {
                    private Label songName = new Label();
                    private Button favoriteBtn = new Button("Favorite");
                    private Label songPath = new Label();
                    private BorderPane bdPane = new BorderPane(songName, null, favoriteBtn, null, songPath);

                    @Override
                    protected void updateItem(Song song, boolean empty) {
                        if (song == null || empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(song.getSongName());
                            setGraphic(bdPane);
                        }
                    }
                };
            }
        });
        //        for (Song song : mainPlaylist.getSongs()
        //        ) {
        //            songItems.add(song.getSongName());
        //        }
        songItems.addAll(mainPlaylist.getSongs());
        System.out.println(mainPlaylist);
        System.out.println(songItems);
        listView.setItems(songItems);
    }

}