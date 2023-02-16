package project.mediaplayer.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import project.mediaplayer.model.CurrentPlaylist;
import project.mediaplayer.model.FavoritePlaylist;
import project.mediaplayer.model.Files;

import java.io.File;

public class MediaPlayerController {

    @FXML
    private Button favoriteButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button libraryButton;

    @FXML
    private ImageView nextButton;

    @FXML
    private ImageView playButton;

    @FXML
    private ImageView previousButton;

    @FXML
    private Button settingButton;



//    private String splitFileName(String path) {
//        String result = "";
//        int a = path.lastIndexOf("/");
//        result = path.substring(a + 1);
//        return result;
//    }

    @FXML
    protected void chooseFile() {
        Files files = new Files();
        CurrentPlaylist currentPlaylist = new CurrentPlaylist(false);
        FavoritePlaylist favoritePlaylist = new FavoritePlaylist(true);
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

        for (File file: files.getFiles()
             ) {
            System.out.println(file.getPath());
        }

        currentPlaylist.addSongs(files);

        favoritePlaylist.addSongToFavorite(currentPlaylist);

        System.out.println(currentPlaylist);
        System.out.println(favoritePlaylist);
//
    }

    @FXML
    protected void playMusic() {



    }

}