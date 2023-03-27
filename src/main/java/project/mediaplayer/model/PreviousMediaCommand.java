package project.mediaplayer.model;


public class PreviousMediaCommand implements IMediaPlayerCommand {
    private final MediaPlayerManagement mediaPlayerManagement;

    public PreviousMediaCommand(MediaPlayerManagement mediaPlayerManagement) {
        this.mediaPlayerManagement = mediaPlayerManagement;
    }

    @Override
    public void execute() {
        mediaPlayerManagement.setMediaPlayerControl(new PlayPreviousMedia());
        mediaPlayerManagement.doActionControl();
    }
}
