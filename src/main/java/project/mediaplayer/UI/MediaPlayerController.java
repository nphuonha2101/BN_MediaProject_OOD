package project.mediaplayer.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import project.mediaplayer.model.CurrentPlaylist;
import project.mediaplayer.model.Files;
import project.mediaplayer.model.Playlists;

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
//        ArrayList<File> files = new ArrayList<>();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Music");
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
//
    }

//    @FXML
//    protected void playMusic() {
//        Playlists playlists = new CurrentPlaylist();
//
//    }

}