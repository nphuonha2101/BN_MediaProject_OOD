package project.mediaplayer.model;

/**
 * This class is used for invoke media player behavior using {@link #setMediaPlayerCommand(IMediaPlayerCommand)}
 * to set command class to invoke and {@link #commandAction()} to invoke command
 */
public class MediaPlayerCommandInvoker {
    private IMediaPlayerCommand mediaPlayerCommand;

    public void setMediaPlayerCommand(IMediaPlayerCommand mediaPlayerCommand) {
        this.mediaPlayerCommand = mediaPlayerCommand;
    }

    public void commandAction() {
        mediaPlayerCommand.execute();
    }
}
