package project.mediaplayer.mediaplayer_OOD;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class FileImport {
  FileChooser fileChooser = new FileChooser();
  ArrayList<File> fileList = new ArrayList<>();

  public void chooseFile() {
    Stage stage = new Stage();
    fileChooser.setTitle("Open Music");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"));
    fileChooser.showOpenDialog(stage);
  }


  public static void main(String[] args) {
    FileImport fileImport = new FileImport();
  fileImport.chooseFile();
  }
}