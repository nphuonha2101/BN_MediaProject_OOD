package project.mediaplayer.model;

/**
 * This class is a Concrete Strategy Class of Class {@link MediaPlayerManagement}
 * <p>
 * The purpose of this class is to Play Previous Media
 * <p>
 * First, the method {@link MediaPlayerManagement#doStrategyAction()} will check the songNumber
 * <li>
 * If songIndexOfPlayingList at the head of media file list then the the songIndexOfPlayingList = size of media list - 1 (tail of list).
 * </li>
 * <li>
 * If songIndexOfPlayingList not at the head of media file list the set songIndexOfPlayingList = songIndexOfPlayingList - 1.
 * </li>
 * Then prepare new Media with songIndexOfPlayingList with {@link MediaPlayerManagement#prepareMedia()} method
 * and automatic play new media file.
 */
public class ConcreteStrategyPlayPreviousMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {

        // Check if the current songIndexOfPlayingList is greater than zero (not the first media file)
        if (mediaPlayerManagement.getSongIndexOfPlayingList() > 0)
            // Decrease the songIndexOfPlayingList to go to the previous media file.
            mediaPlayerManagement.setSongIndexOfPlayingList(mediaPlayerManagement.getSongIndexOfPlayingList() - 1);
        else
            // If the current media file is the first one, go to the last media file.
            mediaPlayerManagement.setSongIndexOfPlayingList(mediaPlayerManagement.getSongFiles().size() - 1);

        // prepare new media with new songIndexOfPlayingList
        mediaPlayerManagement.prepareMedia();
        // automatic play the media file
        mediaPlayerManagement.playPauseMedia();
    }
}
