package project.mediaplayer.model;


public class PlayMediaCommand implements IMediaPlayerCommand {
    private final MediaPlayerManagement mediaPlayerManagement;

    public PlayMediaCommand(MediaPlayerManagement mediaPlayerManagement) {
        this.mediaPlayerManagement = mediaPlayerManagement;
    }

    @Override
    public void execute() {
        this.mediaPlayerManagement.playMedia();
    }
}
