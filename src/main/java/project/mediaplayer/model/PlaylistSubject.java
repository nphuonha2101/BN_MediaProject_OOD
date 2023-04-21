package project.mediaplayer.model;

public interface PlaylistSubject {
    void registerPlaylistObserver(PlaylistObserver playlistObserver);

    void unregisterPlaylistObserver(PlaylistObserver playlistObserver);

    void notifyPlaylistObservers();
}
