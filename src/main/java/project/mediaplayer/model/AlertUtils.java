package project.mediaplayer.model;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * <p> This class provides some alert utilities to show alert on the screen with user's define title and message using {@link Alert}. </p>
 * <p> When run methods in this class, this process isn't a JavaFX thread, so we use {@link Platform#runLater(Runnable)} to run it as JavaFX
 * thread.</p>
 */
public class AlertUtils {
    public static final String INFORMATION_HEADER_TEXT = "INFORMATION";
    public static final String WARNING_HEADER_TEXT = "WARNING";
    public static final String CONFIRMATION_HEADER_TEXT = "CONFIRMATION";
    public static final String ERROR_HEADER_TEXT = "ERROR";

    public static void showInformationAlert(String title, String message) {
        // run thread without JavaFX thread with Platform.runLater()
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // sets type of alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                // sets title of alert
                alert.setTitle(title);
                // sets header text for alert
                alert.setHeaderText(INFORMATION_HEADER_TEXT);
                // sets context text (message) for alert
                alert.setContentText(message);
                // to show alert
                alert.show();
            }
        });
    }

    public static void showWarningAlert(String title, String message) {
        // run thread without JavaFX thread with Platform.runLater()
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // sets type of alert
                Alert alert = new Alert(Alert.AlertType.WARNING);

                // sets title of alert
                alert.setTitle(title);
                // sets header text for alert
                alert.setHeaderText(WARNING_HEADER_TEXT);
                // sets context text (message) for alert
                alert.setContentText(message);
                // to show alert
                alert.show();
            }
        });
    }

    public static void showErrorAlert(String title, String message) {
        // run thread without JavaFX thread with Platform.runLater()
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // sets type of alert
                Alert alert = new Alert(Alert.AlertType.ERROR);

                // sets title of alert
                alert.setTitle(title);
                // sets header text for alert
                alert.setHeaderText(ERROR_HEADER_TEXT);
                // sets context text (message) for alert
                alert.setContentText(message);
                // to show alert
                alert.show();
            }
        });
    }

    public static void showConfirmationAlert(String title, String message) {
        // run thread without JavaFX thread with Platform.runLater()
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // sets type of alert
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                // sets title of alert
                alert.setTitle(title);
                // sets header text for alert
                alert.setHeaderText(CONFIRMATION_HEADER_TEXT);
                // sets context text (message) for alert
                alert.setContentText(message);
                // to show alert
                alert.show();
            }
        });
    }

}
