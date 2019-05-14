import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 14, 2019: Updated ~Evan Zhang
 */
public class LevelSelect extends BaseScene {
    public LevelSelect(Game game) {
        super(game);
    }

    public void initScene() {
        EventHandler[] handlers = {
            event -> game.updateState(State.LEVEL_ONE),
            event -> game.updateState(State.LEVEL_TWO),
            event -> game.updateState(State.LEVEL_THREE),
        };

        VBox root = new VBox();

        Button[] btns = new Button[NUM_LEVELS];
        for (int x = 0; x < NUM_LEVELS; x++) {
            btns[x] = new Button(LEVEL_NAMES[x]);
            btns[x].setMinWidth(SCREEN_WIDTH);
            btns[x].setMinHeight(SCREEN_HEIGHT / 3);
            btns[x].setOnAction(handlers[x]);
        }
        root.getChildren().addAll(btns);
        this.game.setScene(new Scene(root));
    }
}
