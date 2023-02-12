module project.mediaplayer.mediaproject_ood {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.mediaplayer.UI to javafx.fxml;
    exports project.mediaplayer.UI;
}