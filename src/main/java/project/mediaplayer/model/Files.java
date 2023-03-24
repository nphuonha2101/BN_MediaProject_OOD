package project.mediaplayer.model;

import javafx.stage.DirectoryChooser;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private static final String FAVORITE_DATA_FILE_PATH = "src/main/resources/data/favoriteSongData.txt";
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
    public static String getFileNameFromFilePath(File mediaFile) {
//        String result = "";
//        int a = path.lastIndexOf("\\");
//        result = path.substring(a + 1);
        return mediaFile.getName();
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
     * <p>Choose directory and get files from its directory.</p>
     * <p>This method only add music file with these extension (*.mp3, *.wav, *.aac) </p>
     */

    public void chooseFileDir() {
        // clear old files list if it already existence
        this.files.clear();
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
                    files.add(file);
            }
        }
    }

//    public String readLastMusicDirectoryPath() {
//
//    }

    public static void main(String[] args) throws IOException {
        File file = new File(FAVORITE_DATA_FILE_PATH);
        Desktop.getDesktop().open(file);
//        Files files1 = new Files();
//        files1.writeFavoriteSongsData(null);
    }

//    public List<Song> readSongsFromFavoriteFile() {
//        try {
//            File dataFile = new File(FAVORITE_DATA_FILE_PATH);
//            if (!dataFile.exists()) {
//                dataFile.createNewFile();
//            } else {
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void writeFavoriteSongsData(List<Song> songList) {
        try {
            File dataFile = new File(FAVORITE_DATA_FILE_PATH);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            } else {
                FileWriter fileWriter = new FileWriter(dataFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.write("");
                for (Song song : songList
                ) {
                    System.out.println(song);
                    bufferedWriter.write(song.getSongID() + "\t" + song.getSongName() + "\t" + song.getSongPath());
                    bufferedWriter.newLine();
                }

                bufferedWriter.close();
                System.out.println("favorite songs data was wrote success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}