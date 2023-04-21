package project.mediaplayer.model;

import java.util.List;

public interface PlaylistObserver {
    void updatePlaylistObserver(List<Song> songList, String dialogTitle, String dialogMessage);
}
