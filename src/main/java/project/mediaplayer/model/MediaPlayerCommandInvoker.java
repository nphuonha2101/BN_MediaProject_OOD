package project.mediaplayer.model;

public class MediaPlayerCommandInvoker {
    private IMediaPlayerCommand playerCommand;

    public void setPlayerCommand(IMediaPlayerCommand playerCommand) {
        this.playerCommand = playerCommand;
    }

    public void mediaPlayerAction() {
        playerCommand.excute();
    }

}
