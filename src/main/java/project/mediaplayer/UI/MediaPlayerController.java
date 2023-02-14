package project.mediaplayer.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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


}