module project.mediaplayer.mediaproject_ood {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires javafx.graphics;

    opens project.mediaplayer.UI to javafx.fxml;
    exports project.mediaplayer.UI;
}