import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
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

        VBox root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundImage(
            ResourceLoader.loadImage("platform.png"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false, false, false, false)
        )));

        root.getChildren().add(new ImageView(ResourceLoader.loadImage("game-logo.png")));

        Button[] btns = new Button[NUM_LEVELS];
        for (int x = 0; x < NUM_LEVELS; x++) {
            btns[x] = new Button(LEVEL_NAMES[x]);
            btns[x].setAlignment(Pos.CENTER);
            btns[x].setMinWidth(SCREEN_WIDTH / 2);
            btns[x].setMinHeight(SCREEN_HEIGHT / 5);
            btns[x].setGraphic(new Sprite(0, 0, 100, 100, ResourceLoader.loadImage("platform.png")));
            btns[x].setOnAction(handlers[x]);
        }
        root.getChildren().addAll(btns);
        this.game.setScene(new Scene(root));
    }
}
