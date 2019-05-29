import java.io.File;

import javafx.application.Application;
import javafx.animation.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 14, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Max Li
 *  - May 28, 2019: Updated ~Evan Zhang
 * @version 1
 */
public class Main extends Application {
    /**
     * Calls to launch() method, which launches the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new File(Constants.DATA_DIRECTORY).mkdirs();
        launch(args);
    }

    /**
     * Entry point in the JavaFX application.
     * A new game is initialized, and primaryStage is preset to constraints.
     *
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Climb to Grace");

        new Game(primaryStage);

        primaryStage.setResizable(false);
        primaryStage.setHeight(Constants.SCREEN_HEIGHT);
        primaryStage.setWidth(Constants.SCREEN_WIDTH);

        primaryStage.show();
    }
}
