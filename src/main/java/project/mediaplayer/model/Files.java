package project.mediaplayer.model;

import javafx.stage.DirectoryChooser;

import java.awt.*;
import java.io.*;
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

    public static final String FAVORITE_DATA_FILE_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "previousImportedFavoriteSongsData.dat";
    public static final String PREVIOUS_IMPORTED_SONGS_DATA_FILE_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "previousImportedSongsData.dat";
    private final ArrayList<File> files = new ArrayList<>();

//    public Files(ArrayList<File> files){
//        this.files = files;
//    }

    /**
     * - Get File name (Song name) from file path using {@link File#getName()} method
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

    public static void main(String[] args) throws IOException {
        File file = new File(Files.PREVIOUS_IMPORTED_SONGS_DATA_FILE_PATH);
        Desktop.getDesktop().open(file);
    }

    public void writeSongsDataFile(String dataFilePath, Playlist playlist) {

        try {
            File dataFile = new File(dataFilePath);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            } else {
                FileWriter fileWriter = new FileWriter(dataFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.write("");
                for (Song song : playlist.getSongList()
                ) {
                    System.out.println(song);
                    bufferedWriter.write(song.getSongID() + "\t" + song.getSongName() + "\t" + song.isFavorite() + "\t" + song.getSongPath());
                    bufferedWriter.newLine();
                }

                bufferedWriter.close();
                System.out.println("favorite songs data was wrote success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readSongsFromDataFile(String dataFilePath, Playlist playlist) {
        try {
            File dataFile = new File(dataFilePath);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            } else {
                FileReader fileReader = new FileReader(dataFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) break;
//                    System.out.println("Read Line: " + line);
                    playlist.addSongsFromDataFileToPlaylist(line, playlist);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}