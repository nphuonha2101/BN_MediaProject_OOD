package project.mediaplayer.model;

import javafx.scene.control.ListView;

import java.util.ArrayList;

/**
 * <p>This list is used for storage songs with was found by song name. </p>
 * <p>This list provides found items when searched to {@link ListView}.</p>
 * <p>But player not playing song from this list, when select song items from found song. The player will get
 * songID from song and find index (songNumber) of its song in {@link CurrentPlaylist} and plays from it</p>
 */
public class FoundSongsList extends Playlists {
    public FoundSongsList(int playlistType) {
        super(playlistType);
    }


    public void addFoundedSongsToList(ArrayList<Song> songs) {
        if (!this.songs.isEmpty())
            this.songs.clear();

        this.songs.addAll(songs);
    }
}
