package project.mediaplayer.model;

public class ConcreteStrategyPlayNextMedia implements MediaPlayerManagementStrategy {
    @Override
    public void doStrategyAction(MediaPlayerManagement mediaPlayerManagement) {


        if (mediaPlayerManagement.getSongNumber() < mediaPlayerManagement.getSongFiles().size() - 1)
            mediaPlayerManagement.setSongNumber(mediaPlayerManagement.getSongNumber() + 1);
        else
            mediaPlayerManagement.setSongNumber(0);
        mediaPlayerManagement.getMediaPlayer().stop();


        if (mediaPlayerManagement.getMediaTimer().getIsRunning())
            mediaPlayerManagement.getMediaTimer().cancelTimer();

        mediaPlayerManagement.prepareMedia();

        mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyPlayPauseMedia());
        mediaPlayerManagement.doStrategyAction();
    }
}
