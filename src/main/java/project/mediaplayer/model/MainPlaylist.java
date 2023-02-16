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

    //     add Songs to Playlist from file
    public void addSongs(Files files) {

        for (File file : files.getFiles()) {
            if (file != null && file.getAbsolutePath().endsWith(".wav")) {
                String nameSong = Files.splitFileName(file.getPath());
                super.getSongs().add(new Song(nameSong, true, file.getPath()));
            }
        }
    }




}
