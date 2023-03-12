package project.mediaplayer.model;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.MediaPlayer;

import java.util.Timer;
import java.util.TimerTask;

public class MediaTimer {
    private java.util.Timer timer;
    private TimerTask task;
    private boolean running;
    private final ProgressBar songProgressBar;
    private final MediaPlayer mediaPlayer;
    private Label songNameLabel;

    private final MediaPlayerManagement mediaPlayerManagement;

    public MediaTimer(MediaPlayerManagement mediaPlayerManagement, MediaPlayer mediaPlayer, ProgressBar songProgressBar) {
        this.mediaPlayerManagement = mediaPlayerManagement;
        this.mediaPlayer = mediaPlayer;
        this.songProgressBar = songProgressBar;
    }

    public boolean getIsRunning() {
        return this.running;
    }

    public void setIsRunning(boolean runningState) {
        this.running = runningState;
    }

    /**
     * <p>This method ({@link MediaTimer#beginTimer()}) to begin count timer music is playing
     * <ul>When method beginTimer called
     *     <li>Starts a timer that updates the song progress bar and automatically switches to the next song when the current song finishes playing.</li>
     *     <li>This method uses a Timer object to schedule a task that updates the song progress bar</li>
     *     <li>every second. The task runs on the JavaFX Application Thread to ensure that it doesn't</li>
     *     <li>interfere with the user interface. If the current song has finished playing, this method</li>
     *     <li>cancels the timer and calls the resetMedia() and nextMedia() methods on the mediaPlayerManagement</li>
     *     <li>object to switch to the next song.</li>
     * </ul>
     * </p>
     */
    public void beginTimer() {

        timer = new Timer();
        task = new TimerTask() {
            public void run() {

                Platform.runLater(() -> {
                    getIsRunning();
                    double current = mediaPlayer.getCurrentTime().toSeconds();
                    double end = mediaPlayer.getTotalDuration().toSeconds();
                    songProgressBar.setProgress(current / end);

                    System.out.println(current / end);

                    if (current / end == 1) {
                        cancelTimer();
                        // reset progress bar when timer is finishes
                        songProgressBar.setProgress(0);
                        // play next media (next song) when the timer is finishes
                        mediaPlayerManagement.nextMedia();
                    }
                });
            }
        };

        // each 1000ms (1s), run code in run() method (TimerTask) to calculate value to update progress bar
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    /**
     * <p>This method ({@link MediaTimer#cancelTimer()}) to end count timer music is playing
     * <ul>When method is called
     *  <li>isRunning will get method {@link MediaTimer#setIsRunning(boolean)}</li>
     *  <li>and called method {@link java.util.Timer#cancel()} </li>
     * </ul>
     * </p>
     */
    public void cancelTimer() {
        setIsRunning(false);
        timer.cancel();
    }

    /**
     * Get timer to define if timer is null in method {@link MediaPlayerManagement#prepareMedia()}.
     * <p>
     * If timer is not null (timer of previous media player exist),
     * then cancels old timer to avoid old timer and new timer runs concurrently.
     */

    public Timer getTimer() {
        return this.timer;
    }


}
