import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Climb to Grace");

        new LevelSelect(primaryStage).drawScreen();

        primaryStage.setMinHeight(Constants.SCREEN_HEIGHT);
        primaryStage.setMinWidth(Constants.SCREEN_WIDTH);
        primaryStage.show();
    }
}
