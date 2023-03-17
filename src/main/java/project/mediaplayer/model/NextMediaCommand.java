package project.mediaplayer.model;

public class NextMediaCommand implements IMediaPlayerCommand {

    private final MediaPlayerManagement mediaPlayerManagement;

    public NextMediaCommand(MediaPlayerManagement mediaPlayerManagement) {
        this.mediaPlayerManagement = mediaPlayerManagement;
    }

    @Override
    public void excute() {
        mediaPlayerManagement.nextMedia();
    }
}
