package project.mediaplayer.model;

import java.io.File;

/**
 * <p>The purposes of {@link MainPlaylist}:
 * </p>
 *
 * <p> - {@link MainPlaylist} is storage songs from list files of {@link Files} class.
 * </p>
 *
 * <p> - While switching to Favorite on UI, {@link CurrentPlaylist} will be clear all and
 * add all songs from Favorite Playlist.
 * </p>
 *
 * <p> - So that we need a playlist to reserve a stock songs without re-add it from files list
 * </p>
 */

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
                String nameSong = Files.getFileNameFromFilePath(file.getPath());
                this.getSongs().add(new Song(nameSong, false, file.getPath()));
            }
        }
    }


}
