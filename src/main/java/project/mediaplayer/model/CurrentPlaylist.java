package project.mediaplayer.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * <p>The purposes of {@link CurrentPlaylist}:
 *  <ul>
 *      <li> {@link CurrentPlaylist} is a playlist which Media Player read songs from it and play. </li>
 *      <li> The media player only read songs from this playlist. </li>
 *      <li> {@link MainPlaylist} is used for storage songs after add songs file from a directory (reserve songs). </li>
 *      <li> When switch to Favorite button on UI, {@link CurrentPlaylist} will clear all songs,
 *          then adds all songs from {@link FavoritePlaylist}.</li>
 *  </ul>
 * </p>
 */
public class CurrentPlaylist extends Playlists {

    public CurrentPlaylist(int playlistType) {
        super(playlistType);
    }

//    public void playInCurrentPlaylist(int playingState) {
//        for (Song song: super.songs
//             ) {
//            SongPlayer songPlayer = new SongPlayer(song.getSongPath(), playingState);
//            songPlayer.play();
//        }
//    }


    /**
     * Add songs from {@link CurrentPlaylist} to UI using {@link ListView}
     *
     * @param listView a list to display song list on UI, user can interact with it to choose music to play
     */
    public void addToListView(ListView<String> listView) {
        ObservableList<String> observableListSongs = FXCollections.observableArrayList();

        // if list view has elements, clear its
        listView.getItems().clear();

        // adding items to list view
        for (Song song : this.getSongs()) {
            observableListSongs.add(song.getSongName() + "\n" + song.getSongPath());
        }

//        System.out.println(mainPlaylist);
//        System.out.println(songItems);
        listView.setItems(observableListSongs);
    }
}
