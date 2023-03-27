package project.mediaplayer.model;

public class ShuffleMediaCommand implements IMediaPlayerCommand {
    private final MediaPlayerManagement mediaPlayerManagement;

    public ShuffleMediaCommand(MediaPlayerManagement mediaPlayerManagement) {
        this.mediaPlayerManagement = mediaPlayerManagement;
    }

    @Override
    public void execute() {
        mediaPlayerManagement.setMediaPlayerControl(new ShuffleMedia());
        mediaPlayerManagement.doActionControl();
    }
}
