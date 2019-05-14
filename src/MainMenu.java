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
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 */
public class MainMenu extends BaseScene {
    public MainMenu(Game game) {
        super(game);
    }

    public void initScene() {
        VBox root = new VBox();

        Button btn = new Button("Next screen");
        btn.setMinWidth(SCREEN_WIDTH / 2);
        btn.setMinHeight(SCREEN_HEIGHT / 2);
        btn.setOnAction(event -> {
            this.game.updateState(State.LEVEL_SELECT);
        });
        root.getChildren().add(btn);
        this.game.setScene(new Scene(root));
    }
}
