package project.mediaplayer.model;

import java.io.File;

/* The purposes of Main Playlist:
 *   - Main Playlist is storage songs from list files of Files class
 *   - While switching to Favorite on UI, Current Playlist will be clear all and
 *   add all songs from Favorite Playlist
 *   - So that we need a playlist to reserve a stock songs without re-add it from files list
 * */

public class MainPlaylist extends Playlists {


    public MainPlaylist(int playlistType) {
        super(playlistType);
    }

//    public void addSongsFromFile(Playlists otherPlaylist) {
//        super.getSongs().clear();
//        super.getSongs().addAll(otherPlaylist.getSongs());
//
//    }

    //     add Songs to Playlist from files list
    public void addSongs(Files files) {
        this.getSongs().clear();
        for (File file : files.getListFiles()) {
            if (file != null && file.getAbsolutePath().endsWith(".mp3")) {
                String nameSong = Files.splitFileName(file.getPath());
                super.getSongs().add(new Song(nameSong, true, file.getPath()));
            }
        }
    }


}
