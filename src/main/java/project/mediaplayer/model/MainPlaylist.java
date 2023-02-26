package project.mediaplayer.model;

import java.io.File;

public class MainPlaylist extends Playlists {


    public MainPlaylist(int playlistType) {
        super(playlistType);
    }

    public void addSongsFromFile(Playlists otherPlaylist) {
        super.getSongs().clear();
        super.getSongs().addAll(otherPlaylist.getSongs());

    }

    //     add Songs to Playlist from files list
    public void addSongs(Files files) {

        for (File file : files.getListFiles()) {
            if (file != null && file.getAbsolutePath().endsWith(".mp3")) {
                String nameSong = Files.splitFileName(file.getPath());
                super.getSongs().add(new Song(nameSong, true, file.getPath()));
            }
        }
    }




}
