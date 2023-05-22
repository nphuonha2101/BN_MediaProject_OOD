package project.mediaplayer.model;

/**
 * This is an interface abstract for concrete strategy behavior of classes {@link FileManagement}.
 */
public interface FileManagementStrategy {
    void chooseFileStrategy(FileManagement fileManagement);
}
