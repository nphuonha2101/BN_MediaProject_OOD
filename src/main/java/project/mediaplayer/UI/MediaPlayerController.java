package project.mediaplayer.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import project.mediaplayer.model.*;

import java.util.StringTokenizer;

public class MediaPlayerController {

    @FXML
    private Button aboutButton;
    @FXML
    private Button favoriteButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button libraryButton;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button openFolder;

    @FXML
    private Button settingButton;

    @FXML
    private Label headerLabel;

    @FXML
    private Button favoriteSongButton;

    @FXML
    private Button playButton;

    @FXML
    private Label songNameLabel;

    private MainPlaylist mainPlaylist = new MainPlaylist(Playlists.MAIN_PLAYLIST);
    private FavoritePlaylist favoritePlaylist = new FavoritePlaylist(Playlists.FAVORITE_PLAYLIST);
    private CurrentPlaylist currentPlaylist = new CurrentPlaylist(Playlists.CURRENT_PLAYLIST);
    private ObservableList<String> songItems = FXCollections.observableArrayList();
    private SongPlayer songPlayer = new SongPlayer();
    private int playState;
    private Task<Void> task;
  private String currentSong = "";
  private String selectedSong = "";

    @FXML
    protected void chooseFile() {
        Files files = new Files();
        files.chooseFile();
//        files.clearAll();
////        ArrayList<File> files = new ArrayList<>();
//        DirectoryChooser directoryChooser = new DirectoryChooser();
//        directoryChooser.setTitle("Open Music Folder");
////        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files *.wav", "*.wav"));
//        File directory = directoryChooser.showDialog(null);
//        if (directory != null) {
//            for (File file : directory.listFiles()) {
//                files.addFile(file);
//            }
//        }

        mainPlaylist.addSongs(files);
        currentPlaylist.addSongFromOtherPlaylist(mainPlaylist);
        favoritePlaylist.addSongToFavorite(mainPlaylist);
//        System.out.println(currentPlaylist);
        addToListView();
    }


    @FXML
    protected void mainPlaylistLView() {
        currentPlaylist.addSongFromOtherPlaylist(mainPlaylist);
        headerLabel.setText("Home");
        addToListView();
    }

    @FXML
    protected void favoritePlaylistLView() {
        currentPlaylist.addSongFromOtherPlaylist(favoritePlaylist);
        headerLabel.setText("Favorite");
        addToListView();
    }

    private void addToListView() {
        // set imported Song to list view
        listView.getItems().clear();

        for (Song song : mainPlaylist.getSongs()
        ) {
            songItems.add(song.getSongName() + "\n" + song.getSongPath());
        }

        System.out.println(mainPlaylist);
        System.out.println(songItems);
        listView.setItems(songItems);
    }

    @FXML
    private void selectedListItem() {


                String item = listView.getSelectionModel().getSelectedItem();
                int index = listView.getSelectionModel().getSelectedIndex();
        System.out.println(index);
                songPlayer.setPlayingIndex(index);
                songNameLabel.setText(splitSongNameLView(item));
                selectedSong = splitPathLView(item);

//                songPlayer.musicPlayer();
//
//        songPlayer.play(selectedSong, 1);
//        task = new Task<Void>() {
        songPlayer.play( 1, selectedSong);
//            @Override
//            protected Void call() throws Exception {
////                songPlayer.stop(currentSong);
//                songPlayer.play(splitPathLView(selectedSong), songPlayer.PLAY);
//                return null;
//            }
//        };
////        songNameLabel.setText(slitPathLView(item));
//                currentSong = splitSongNameLView(item);
//    new Thread(task).start();
//        if (Thread.activeCount() > 1) {
//            new Thread(task).interrupt();
//        }
    }

    // open About Stage
    @FXML
    private void openAbout() throws Exception {
        Stage stage = new Stage();
        AboutApplication application = new AboutApplication();
        application.start(stage);
    }

    // slit song name from list view item
    private String splitSongNameLView(String str) {
        String result = "";
        StringTokenizer tokenizer = new StringTokenizer(str, "\n");
        result = tokenizer.nextToken();
        return result;
    }

    // split song's path from list view item
    private String splitPathLView(String str) {
        String result = "";
        StringTokenizer tokenizer = new StringTokenizer(str, "\n");
        tokenizer.nextToken();
        result = tokenizer.nextToken();
        return result;
    }

    //    public static Clip PlayMusic(String location) {
//        try {
//            File musicPath = new File(location);
//            if (musicPath.exists()) {
//                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioInput);
//                clip.start();
//                return clip;
//            } else {
//                System.out.println("Can't find file");
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }
    @FXML
    protected void playStop() {
//        for (Song song : currentPlaylist.getSongs()
//        ) {
//            songItems.add(song.getSongName() + "\n" + song.getSongPath());
//        }
//        try {
//            for (int i = 0; i < songItems.size(); i++) {
//                System.out.println("Playing " + songItems.get(i));
//                Clip currentClip = PlayMusic(songItems.get(i));
//                while (currentClip.getMicrosecondLength() != currentClip.getMicrosecondPosition()) {
//
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        if (playButton.isFocused() == true) {
//            playState = 1;
//            currentPlaylist.playInCurrentPlaylist(playState);
//        } else  if (playButton.isFocused() == false){
//            playState = 2;
//        }
//        currentPlaylist.playInCurrentPlaylist(1);
    }


}