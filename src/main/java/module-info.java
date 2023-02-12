module project.mediaplayer.mediaproject_ood {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens project.mediaplayer.mediaproject_ood to javafx.fxml;
    exports project.mediaplayer.mediaproject_ood;
}