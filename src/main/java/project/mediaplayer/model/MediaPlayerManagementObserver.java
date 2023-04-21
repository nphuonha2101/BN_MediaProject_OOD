package project.mediaplayer.model;

public interface MediaPlayerManagementObserver {
    void updateMediaPlayerManagementObserver(String songName,
                                             boolean isFavoriteSong,
                                             double volumeValue,
                                             int songNumber,
                                             double songProgressValue);
}
