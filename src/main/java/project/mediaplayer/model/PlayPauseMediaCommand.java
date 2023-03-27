package project.mediaplayer.model;


public class PlayPauseMediaCommand implements IMediaPlayerCommand {
    private final MediaPlayerManagement mediaPlayerManagement;

    public PlayPauseMediaCommand(MediaPlayerManagement mediaPlayerManagement) {
        this.mediaPlayerManagement = mediaPlayerManagement;
    }

    @Override
    public void execute() {
        mediaPlayerManagement.setMediaPlayerControl(new PlayPauseMedia());
        mediaPlayerManagement.doActionControl();
    }
}
