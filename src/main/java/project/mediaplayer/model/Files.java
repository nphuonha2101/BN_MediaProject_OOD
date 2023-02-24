package project.mediaplayer.model;

import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ArrayList;

public class Files {
    ArrayList<File> files = new ArrayList<>();

//    public Files(ArrayList<File> files){
//        this.files = files;
//    }

    public ArrayList<File> getFiles() {
        return this.files;
    }



    public void removeFile(File file) {
        this.files.remove(file);
    }




    public static String splitFileName(String path) {
        String result = "";
        int a = path.lastIndexOf("\\");
        result = path.substring(a + 1);
        return result;
    }

    // choose directory and get files from it
    public void fileChooser() {
        this.files.clear();
//        ArrayList<File> files = new ArrayList<>();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Music Folder");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files *.wav", "*.wav"));
        File directory = directoryChooser.showDialog(null);
        if (directory != null) {
            for (File file : directory.listFiles()) {
                files.add(file);
            }
        }
    }
}