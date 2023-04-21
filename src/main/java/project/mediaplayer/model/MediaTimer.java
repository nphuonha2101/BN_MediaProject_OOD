package project.mediaplayer.model;

import javafx.application.Platform;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MediaTimer implements MediaTimerSubject {
    private java.util.Timer timer;
    private TimerTask task;
    private boolean running;
    private final MediaPlayer mediaPlayer;
    private double songProgress;

    private final List<MediaTimerObserver> mediaTimerObserverList = new ArrayList<>();


    private final MediaPlayerManagement mediaPlayerManagement;

    public MediaTimer(MediaPlayerManagement mediaPlayerManagement, MediaPlayer mediaPlayer) {
        this.mediaPlayerManagement = mediaPlayerManagement;
        this.mediaPlayer = mediaPlayer;
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
     *     <li>This method uses a Timer object to schedule a task that updates the song progress bar
     *     every second.</li>
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
//                    songProgressBar.setProgress(current / end);
                    setSongProgress(current / end);
                    System.out.println(current / end);

                    if (current / end == 1) {
                        cancelTimer();
                        // reset progress bar when timer is finishes
//                        songProgressBar.setProgress(0);
                        setSongProgress(0);
                        // play next media (next song) when the timer is finishes
                        mediaPlayerManagement.setMediaPlayerControlStrategy(new ConcreteStrategyPlayNextMedia());
                        mediaPlayerManagement.doStrategyAction();
                    }
                    notifyMediaTimerObservers();

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

        notifyMediaTimerObservers();
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

    public double getSongProgress() {
        return songProgress;
    }

    public void setSongProgress(double songProgress) {
        this.songProgress = songProgress;
    }

    @Override
    public void registerMediaTimerObserver(MediaTimerObserver mediaTimerObserver) {
        if (!this.mediaTimerObserverList.contains(mediaTimerObserver))
            this.mediaTimerObserverList.add(mediaTimerObserver);
    }

    @Override
    public void unregisterMediaTimerObserver(MediaTimerObserver mediaTimerObserver) {
        this.mediaTimerObserverList.remove(mediaTimerObserver);
    }

    @Override
    public void notifyMediaTimerObservers() {
        for (MediaTimerObserver mediaTimerObserver : mediaTimerObserverList)
            mediaTimerObserver.updateMediaTimerObserver(this.songProgress);
    }


}
