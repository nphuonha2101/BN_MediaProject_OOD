package project.mediaplayer.model;

/**
 * This class is a Concrete Strategy Class of Class {@link MediaPlayerManagement}
 * <p>
 * The purpose of this class is to Stop Playing Media
 */
public class ConcreteStrategyStopMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {
        // cancel the timer if the timer still running
        if (mediaPlayerManagement.getMediaTimer().getTimer() != null)
            mediaPlayerManagement.getMediaTimer().cancelTimer();
        // stop the media player
        mediaPlayerManagement.getMediaPlayer().stop();
    }
}
