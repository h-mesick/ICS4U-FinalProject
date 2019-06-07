/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * The main driver class that runs the entire game.
 */
import java.io.File;

import javafx.application.Application;
import javafx.stage.*;

/**
 * The main driver class that runs the entire game.
 * <pre>
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 14, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Max Li
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Commented ~Evan Zhang
 *  - Jun 4, 2019: Updated ~Evan Zhang
 *  - Jun 6, 2019: Updated ~Evan Zhang
 *  - Jun 6, 2019: Commented ~Evan Zhang
 *  - Jun 7, 2019: Finished ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public class Main extends Application {
    /**
     * Calls to launch() method, which launches the application.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new File(Constants.DATA_DIRECTORY).mkdirs();
        launch(args);
    }

    /**
     * Entry point in the JavaFX application.
     * A new game is initialized, and primaryStage is preset to constraints.
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Climb to Grace");
        primaryStage.getIcons().add(ResourceLoader.loadImage("company-logo.png"));

        primaryStage.setResizable(false);
        primaryStage.setHeight(Constants.SCREEN_HEIGHT);
        primaryStage.setWidth(Constants.SCREEN_WIDTH);
        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> primaryStage.setWidth(Constants.SCREEN_WIDTH));
        primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> primaryStage.setHeight(Constants.SCREEN_HEIGHT));

        new Game(primaryStage);
        primaryStage.show();
    }
}
