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
 *  - May 16, 2019: Created ~Evan Zhang
 *  - May 18, 2019: Updated ~Evan Zhang
 */
public class Help extends BaseScene {
    public Help(Game game) {
        super(game);
    }

    public void initScene() {
        VBox root = new VBox();

        Button btn = new Button("Go Back");
        btn.setMinWidth(SCREEN_WIDTH);
        btn.setMinHeight(SCREEN_HEIGHT);
        btn.setOnAction(event -> {
            if (this.game.hasNextState()) {
                this.game.nextState();
            } else {
                this.game.updateState(State.MAIN_MENU);
            }
        });
        root.getChildren().add(btn);
        this.game.setScene(new Scene(root));
    }
}
