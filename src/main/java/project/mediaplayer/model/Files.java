package project.mediaplayer.model;

import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ArrayList;

public class Files {
    private final ArrayList<File> files = new ArrayList<>();

//    public Files(ArrayList<File> files){
//        this.files = files;
//    }

    /*
    get File name (Song name) from file path,
    use String.lastIndexOf() method to find the lash backslash character to get substring from its position + 1 (file name)
    ex: user/music/demo.mp3 => the last backslash index is 10, so we use substring method to substring from index 11 => demo.mp3
    in Java, use \\ to perform \ symbol
     */
    public static String splitFileName(String path) {
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


    // choose directory and get files from its directory
    public void chooseFileDir() {
        // clear old files list if it already exist
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
}