package project.mediaplayer.model;

/**
 * This is an interface abstract for concrete strategy behavior of classes {@link MediaPlayerManagement}
 */
public interface MediaPlayerManagementStrategy {
    void doStrategyAction(MediaPlayerManagement mediaPlayerManagement);
}
