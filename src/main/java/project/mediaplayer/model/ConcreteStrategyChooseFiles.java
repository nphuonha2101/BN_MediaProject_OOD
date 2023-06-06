package project.mediaplayer.model;

import javafx.stage.FileChooser;

import java.io.File;

/**
 * This is a Concrete Strategy Choose FileManagement of {@link FileManagement} class.
 * This can choose one or multiples music files.
 * This strategy using {@link FileChooser} and add music files from it.
 */
public class ConcreteStrategyChooseFiles implements FileManagementStrategy {
    @Override
    public void chooseFileStrategy(FileManagement fileManagement) {
        // clear old file list if it exists
        fileManagement.getListFiles().clear();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Music FileManagement");
        // set filter of files with extensions
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Music File", "*.mp3", "*.wav", "*.aac"),
                new FileChooser.ExtensionFilter("MP3 Music File", "*.mp3"),
                new FileChooser.ExtensionFilter("WAV Music File", "*.wav"),
                new FileChooser.ExtensionFilter("AAC Music File", "*.aac")
        );
        // add fileManagement with for each loop
        // because the return value of method showOpenMultipleDialog(Window window) is Unmodifiable Collections
        // so this cannot be clear
        for (File musicFile : fileChooser.showOpenMultipleDialog(null)
        ) {
            fileManagement.getListFiles().add(musicFile);
        }
    }
}
