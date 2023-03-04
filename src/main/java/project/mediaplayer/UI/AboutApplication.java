package project.mediaplayer.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AboutApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AboutApplication.class.getResource("about.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);

        stage.setTitle("About B&N Media Player");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
