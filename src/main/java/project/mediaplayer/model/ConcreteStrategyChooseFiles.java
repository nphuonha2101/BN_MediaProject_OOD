package project.mediaplayer.model;

import javafx.stage.FileChooser;

import java.io.File;

/**
 * This is a Concrete Strategy Choose Files of {@link Files} class.
 * This can choose one or multiples music files.
 * This strategy using {@link FileChooser} and add music files from it.
 */
public class ConcreteStrategyChooseFiles implements FilesStrategy {
    @Override
    public void chooseFileStrategy(Files files) {
        // clear old files if it exists
        files.getListFiles().clear();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Music Files");
        // set filter of files with extensions
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Music File", "*.mp3", "*.wav", "*.aac"),
                new FileChooser.ExtensionFilter("MP3 Music File", "*.mp3"),
                new FileChooser.ExtensionFilter("WAV Music File", "*.wav"),
                new FileChooser.ExtensionFilter("AAC Music File", "*.aac")
        );
        // add files with for each loop
        // because the return value of method showOpenMultipleDialog(Window window) is Unmodifiable Collections
        for (File musicFile : fileChooser.showOpenMultipleDialog(null)
        ) {
            files.getListFiles().add(musicFile);
        }
    }
}
