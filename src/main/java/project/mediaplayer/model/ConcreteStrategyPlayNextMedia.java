package project.mediaplayer.model;

/**
 * This class is a Concrete Strategy Class of Class {@link MediaPlayerManagement}
 * <p>
 * The purpose of this class is to Play Next Media
 * <p>
 * First, the method {@link MediaPlayerManagement#doStrategyAction()} will check the songNumber
 * <li>
 * If songNumber at the tail of media file list then the the songNumber = 0 (head of list).
 * </li>
 * <li>
 * If songNumber not at the tail of media file list the set songNumber = songNumber + 1.
 * </li>
 * Then prepare new Media with SongNumber with {@link MediaPlayerManagement#prepareMedia()} method
 * and automatic play new media file.
 */
public class ConcreteStrategyPlayNextMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {

        // when songNumber not at the tail of the media file list of mediaPlayerManagement class
        if (mediaPlayerManagement.getSongNumber() < mediaPlayerManagement.getSongFiles().size() - 1)
            mediaPlayerManagement.setSongNumber(mediaPlayerManagement.getSongNumber() + 1);
        else // when songNumber is at the tail of media file list of
            mediaPlayerManagement.setSongNumber(0);

        // stop current media player before play next media
//        mediaPlayerManagement.getMediaPlayer().stop();

        // cancel the timer if previous timer still running
//        if (mediaPlayerManagement.getMediaTimer().getIsRunning())
//            mediaPlayerManagement.getMediaTimer().cancelTimer();

        // prepare new media with new songNumber
        mediaPlayerManagement.prepareMedia();

        // automatic play media file
        mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyPlayPauseMedia());
        mediaPlayerManagement.doStrategyAction();
    }
}
