package project.mediaplayer.model;

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

        // If the timer is running, cancel it.
        if (mediaPlayerManagement.getMediaTimer().getIsRunning())
            mediaPlayerManagement.getMediaTimer().cancelTimer();

        mediaPlayerManagement.prepareMedia();
        // Play the new media file.
        mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyPlayPauseMedia());
        mediaPlayerManagement.doStrategyAction();
    }
}
