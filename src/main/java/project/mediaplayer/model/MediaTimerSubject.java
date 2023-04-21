package project.mediaplayer.model;

public interface MediaTimerSubject {
    void registerMediaTimerObserver(MediaTimerObserver mediaTimerObserver);

    void unregisterMediaTimerObserver(MediaTimerObserver mediaTimerObserver);

    void notifyMediaTimerObservers();
}
