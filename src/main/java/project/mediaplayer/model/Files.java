package project.mediaplayer.model;

import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ArrayList;


/**
 * <p> - The {@link Files} class is use for storage files of the directory which was chosen
 * from class {@link javafx.stage.FileChooser} of {@code JavaFX}.
 * </p>
 * <p> - The method {@link #chooseFileDir() chooseFileDir()} is use {@link javafx.stage.FileChooser} to choose a directory which has
 * music files and only add music files from it (like *.mp3, *.aac, *.wav formats).
 * </p>
 * <p> - List of music files is use for adding {@link Song} object to Playlist.
 * </p>
 * <p>
 * - {@link Song} object contains song name (file name from path), isFavorite to define if this song is favorite,
 * and path of the song.
 * </p>
 */
public class Files {
    private final ArrayList<File> files = new ArrayList<>();

//    public Files(ArrayList<File> files){
//        this.files = files;
//    }

    /**
     * - Get File name (Song name) from file path and
     * use {@code String.lastIndexOf()} method to find the last backslash character to get substring from its position + 1 (file name)
     * <br>
     * - Ex: user/music/demo.mp3 => the last backslash index is 10, so we use substring method to substring from index 11 => demo.mp3
     * <br>
     * - In Java, use \\ to perform \ symbol
     */
    public static String getFileNameFromFilePath(String path) {
        String result = "";
        int a = path.lastIndexOf("\\");
        result = path.substring(a + 1);
        return result;
    }

    // get files list
    public ArrayList<File> getListFiles() {
        return this.files;
    }

    // add file to files list
    public void addFile(File file) {
        this.files.add(file);
    }

    // remove file from files list
    public void removeFile(File file) {
        this.files.remove(file);
    }

    // clear all files list
    public void clearAll() {
        this.files.clear();
    }


    /**
     * choose directory and get files from its directory
     */

    public void chooseFileDir() {
        // clear old files list if it already existence
        this.files.clear();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Music Folder");
        File directory = directoryChooser.showDialog(null);
        if (directory != null) {
            for (File file : directory.listFiles()
            ) {
                // only add .mp3, .aac, .wav file
                if (file.getPath().endsWith(".mp3") || file.getPath().endsWith(".aac")
                        || file.getPath().endsWith(".wav"))
                    files.add(file);
            }
        }
    }

//    public String readLastMusicDirectoryPath() {
//
//    }

}