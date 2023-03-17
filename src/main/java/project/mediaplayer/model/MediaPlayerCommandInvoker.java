package project.mediaplayer.model;

public class MediaPlayerCommandInvoker {
    IMediaPlayerCommand mediaPlayerCommand;

    public void setMediaPlayerCommand(IMediaPlayerCommand mediaPlayerCommand) {
        this.mediaPlayerCommand = mediaPlayerCommand;
    }

    public void commandAction() {
        mediaPlayerCommand.execute();
    }
}
