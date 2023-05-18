package project.mediaplayer.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> - The {@link Files} class is use for storage files of the directory which was chosen
 * from class {@link javafx.stage.FileChooser} of {@code JavaFX}.
 * </p>
 * <p> - The method {@link #chooseFiles() chooseFileDir()} is use {@link javafx.stage.FileChooser} to choose a directory which has
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
    private List<File> files = new ArrayList<>();
    private FilesStrategy filesStrategy;

    /**
     * - Get File name (Song name) from file path using {@link File#getName()} method
     */
    public static String getFileNameFromFilePath(File mediaFile) {
        return mediaFile.getName();
    }

    public List<File> getListFiles() {
        return this.files;
    }

    public void setListFiles(List<File> files) {
        this.files = files;
    }

    /**
     * <p>Choose one or multiples files with FileChooser</p>
     * <p>This method only add music file with these extension (*.mp3, *.wav, *.aac) </p>
     */
    public void chooseFiles() {
        this.setFilesStrategy(new ConcreteStrategyChooseFiles());
        this.filesStrategy.chooseFileStrategy(this);
    }

    /**
     * <p>Choose directory and get files from its directory.</p>
     * <p>This method only add music file with these extension (*.mp3, *.wav, *.aac) </p>
     */
    public void chooseFilesFromDir() {

        this.setFilesStrategy(new ConcreteStrategyChooseFileFromDir());
        this.filesStrategy.chooseFileStrategy(this);

    }

    /**
     * Set Concrete Strategy of {@link this} to determine the way to choose Media files
     *
     * @param filesStrategy is a Concrete Strategy class of {@link Files} class
     */
    public void setFilesStrategy(FilesStrategy filesStrategy) {
        this.filesStrategy = filesStrategy;
    }

    /**
     * Do choose file strategy from Concrete Strategy class
     */
    public void doStrategyAction() {
        this.filesStrategy.chooseFileStrategy(this);
    }

    /**
     * Writes the data of a playlist containing song information to a file.
     *
     * @param dataFilePath The file path of the file to write to.
     * @param playlist     The playlist containing the song information.
     * @throws IOException If an I/O error occurs.
     */
    public void writeSongsDataFile(String dataFilePath, Playlist playlist) {

        try {
            File dataFile = new File(dataFilePath);
            // create file if file not exist
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
                // close the buffered writer to release the resource
                bufferedWriter.close();
                System.out.println("songs data was wrote success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads records from a data file located at the specified file path.
     *
     * @param dataFilePath The file path of the data file to read from.
     * @return A list of strings containing the records read from the data file.
     * @throws IOException If an I/O error occurs.
     */
    public List<String> readRecordsFromDataFile(String dataFilePath) {
        List<String> result = new ArrayList<>();
        try {
            File dataFile = new File(dataFilePath);
            // create file if file not exist
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            } else {
                FileReader fileReader = new FileReader(dataFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // read line until the line is null
                // file must be ensured the line before near the line after
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) break;

                    result.add(line);
                }
                // close the buffered reader to release the resource
                bufferedReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}