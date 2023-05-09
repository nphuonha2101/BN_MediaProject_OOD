package project.mediaplayer.model;

public interface MediaPlayerManagementSubject {
    void registerMPManagementObserver(MediaPlayerManagementObserver mediaPlayerManagementObserver);
    void unregisterMPManagementObserver(MediaPlayerManagementObserver mediaPlayerManagementObserver);
    void notifyMPManagementObservers();
}
