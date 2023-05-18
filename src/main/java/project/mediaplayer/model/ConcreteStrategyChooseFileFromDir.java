package project.mediaplayer.model;

import javafx.stage.DirectoryChooser;

import java.io.File;

/**
 * This is a Concrete Strategy Choose File from Directory of {@link Files} class.
 * This strategy using {@link DirectoryChooser} and add music files from it.
 */
public class ConcreteStrategyChooseFileFromDir implements FilesStrategy {

    @Override
    public void chooseFileStrategy(Files files) {
        // clear old files if it exists
        files.getListFiles().clear();

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Music Folder");
        File directory = directoryChooser.showDialog(null);
        // If directory isn't null then adds files from it
        if (directory != null) {
            for (File file : directory.listFiles()
            ) {
                // only add .mp3, .aac, .wav file
                if (file.getPath().endsWith(".mp3") || file.getPath().endsWith(".aac")
                        || file.getPath().endsWith(".wav"))
                    files.getListFiles().add(file);
            }
        }
    }
}
