package project.mediaplayer.model;

public class ResetMediaCommand implements IMediaPlayerCommand {
    private final MediaPlayerManagement mediaPlayerManagement;

    public ResetMediaCommand(MediaPlayerManagement mediaPlayerManagement) {
        this.mediaPlayerManagement = mediaPlayerManagement;
    }

    @Override
    public void execute() {
        mediaPlayerManagement.setMediaPlayerControl(new ResetMedia());
        mediaPlayerManagement.doActionControl();
    }
}
