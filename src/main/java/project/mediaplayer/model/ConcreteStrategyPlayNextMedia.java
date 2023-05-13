package project.mediaplayer.model;

/**
 * This class is a Concrete Strategy Class of Class {@link MediaPlayerManagement}
 * <p>
 * The purpose of this class is to Play Next Media
 * <p>
 * First, the method {@link MediaPlayerManagement#doStrategyAction()} will check the songIndexOfPlayingList
 * <li>
 * If songIndexOfPlayingList at the tail of media file list then the the songIndexOfPlayingList = 0 (head of list).
 * </li>
 * <li>
 * If songIndexOfPlayingList not at the tail of media file list the set songIndexOfPlayingList = songIndexOfPlayingList + 1.
 * </li>
 * Then prepare new Media with songIndexOfPlayingList with {@link MediaPlayerManagement#prepareMedia()} method
 * and automatic play new media file.
 */
public class ConcreteStrategyPlayNextMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {

        // when songIndexOfPlayingList not at the tail of the media file list of mediaPlayerManagement class
        if (mediaPlayerManagement.getSongIndexOfPlayingList() < mediaPlayerManagement.getSongFiles().size() - 1)
            mediaPlayerManagement.setSongIndexOfPlayingList(mediaPlayerManagement.getSongIndexOfPlayingList() + 1);
        else // when songNumber is at the tail of media file list of
            mediaPlayerManagement.setSongIndexOfPlayingList(0);

        // prepare new media with new songIndexOfPlayingList
        mediaPlayerManagement.prepareMedia();

        // automatic play media file
        mediaPlayerManagement.playPauseMedia();
    }
}
