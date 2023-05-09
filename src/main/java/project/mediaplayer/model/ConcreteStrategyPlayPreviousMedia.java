package project.mediaplayer.model;

/**
 * This class is a Concrete Strategy Class of Class {@link MediaPlayerManagement}
 * <p>
 * The purpose of this class is to Play Previous Media
 * <p>
 * First, the method {@link MediaPlayerManagement#doStrategyAction()} will check the songNumber
 * <li>
 * If songNumber at the head of media file list then the the songNumber = size of media list - 1 (tail of list).
 * </li>
 * <li>
 * If songNumber not at the head of media file list the set songNumber = songNumber - 1.
 * </li>
 * Then prepare new Media with SongNumber with {@link MediaPlayerManagement#prepareMedia()} method
 * and automatic play new media file.
 */
public class ConcreteStrategyPlayPreviousMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {

        // Check if the current songNumber is greater than zero (not the first media file)
        if (mediaPlayerManagement.getSongNumber() > 0)
            // Decrease the songNumber to go to the previous media file.
            mediaPlayerManagement.setSongNumber(mediaPlayerManagement.getSongNumber() - 1);
        else
            // If the current media file is the first one, go to the last media file.
            mediaPlayerManagement.setSongNumber(mediaPlayerManagement.getSongFiles().size() - 1);

        // prepare new media with new songNumber
        mediaPlayerManagement.prepareMedia();
        // automatic play the media file
        mediaPlayerManagement.playPauseMedia();
    }
}
