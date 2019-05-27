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
 *  - May 16, 2019: Updated ~Evan Zhang
 *  - May 17, 2019: Updated ~Evan Zhang
 *  - May 18, 2019: Updated ~Evan Zhang
 *  - May 23, 2019: Finished ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 */
public class LevelSelect extends BaseScene {
    /**
     * Constructor
     * @param  game The current game
     */
    public LevelSelect(Game game) {
        super(game);
    }

    /**
     * Initializes the scene
     */
    public void initScene() {
        EventHandler[] handlers = {
            event -> game.updateState(State.LEVEL_ONE),
            event -> game.updateState(State.LEVEL_TWO),
            event -> game.updateState(State.LEVEL_THREE),
        };

        BorderPane root = new BorderPane();

        root.setBackground(new Background(new BackgroundImage(
            ResourceLoader.loadImage("background.png"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false, false, false, false)
        )));

        root.setTop(getTitle());

        VBox body = new VBox(10);
        body.setAlignment(Pos.CENTER);

        body.getChildren().add(new ImageView(ResourceLoader.loadImage("level-select-logo.png")));

        for (int x = 0; x < NUM_LEVELS; x++) {
            StackPane button = getMainButton("" + (x+1), handlers[x]);
            if (x == 0)
                button.setDisable(true);
            body.getChildren().add(button);
        }

        ImageButton backButton = new ImageButton();
        backButton.setFitWidth(50);
        backButton.setFitHeight(50);
        backButton.setImages(ResourceLoader.loadImage("back-button.png"),
                             ResourceLoader.loadImage("back-button-hover.png"),
                             ResourceLoader.loadImage("back-button-selected.png"));
        backButton.setOnAction(event -> game.updateState(State.MAIN_MENU));

        ImageButton helpButton = new ImageButton();
        helpButton.setFitWidth(50);
        helpButton.setFitHeight(50);
        helpButton.setImages(ResourceLoader.loadImage("help-button.png"),
                             ResourceLoader.loadImage("help-button-hover.png"),
                             ResourceLoader.loadImage("help-button-selected.png"));
        helpButton.setOnAction(event -> {
            game.setNextState(game.getCurrentState());
            game.updateState(State.HELP);
        });

        root.setCenter(body);
        root.setBottom(getFooter(backButton, helpButton));
        this.game.setScene(new Scene(root));
    }
}
