package project.mediaplayer.model;

/**
 * This is a interface to represent update method for observer of class {@link MediaPlayerManagement}
 */
public interface MediaPlayerManagementObserver {
    void updateMediaPlayerManagementObserver(String songName,
                                             boolean isFavoriteSong,
                                             double volumeValue,
                                             int songNumber,
                                             double songProgressValue);
}
