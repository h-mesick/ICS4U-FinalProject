import javafx.scene.*;
import javafx.scene.control.Button;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 */
public class LoadingScreen extends BaseScene {
    public LoadingScreen(Game game) {
        super(game);
    }

    public void initScene() {
        VBox root = new VBox();

        Button[] btns = new Button[NUM_LEVELS];
        for (int x = 0; x < NUM_LEVELS; x++) {
            btns[x] = new Button(LEVEL_NAMES[x]);
            btns[x].setMinWidth(SCREEN_WIDTH);
            btns[x].setMinHeight(SCREEN_HEIGHT / 3);
            btns[x].setOnAction(handlers[x]);
        }
        root.getChildren().addAll(btns);        
    }
}
