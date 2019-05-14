import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

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

        Button btn = new Button("Next screen");
        btn.setMinWidth(SCREEN_WIDTH / 2);
        btn.setMinHeight(SCREEN_HEIGHT / 2);
        btn.setOnAction(event -> {
            this.game.updateState(State.MAIN_MENU);
        });
        root.getChildren().add(btn);
        this.game.setScene(new Scene(root));
    }
}
