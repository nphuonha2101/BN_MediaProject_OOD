package project.mediaplayer.model;

/**
 * This is an interface abstract for concrete strategy behavior of classes {@link Files}.
 */
public interface FilesStrategy {
    void chooseFileStrategy(Files files);
}
