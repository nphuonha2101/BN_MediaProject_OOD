package project.mediaplayer.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import java.lang.Object;
import javafx.scene.media.Media;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MediaPlayerController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button button;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    private ArrayList<File> listFile = new ArrayList<>();
    private String path;
    private MediaPlayer mediaPlayer;
    @FXML
    protected void setButton() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(null);
        path = directory.toURI().toString();
        System.out.println(path);
//        for (File file : directory.listFiles()
//             ) {
//            System.out.println(splitFileName(file.toURI().toString()));
//        }
        if (path != null) {
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
        }

    }
    private String splitFileName(String path) {
        String result = "";
        int a = path.lastIndexOf("/");
        result = path.substring(a + 1);
        return result;
    }


}