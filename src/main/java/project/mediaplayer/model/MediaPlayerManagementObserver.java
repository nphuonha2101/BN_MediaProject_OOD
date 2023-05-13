package project.mediaplayer.model;

/**
 * This is an interface to represent update method for observer of class {@link MediaPlayerManagement}
 */
public interface MediaPlayerManagementObserver {
    void updateMediaPlayerManagementObserver(String songName,
                                             boolean isFavoriteSong,
                                             int songIndexOfPlayingList,
                                             double songProgressValue);
}
