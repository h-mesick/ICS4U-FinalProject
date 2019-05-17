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

        BorderPane root = new BorderPane();


        root.setBackground(new Background(new BackgroundImage(
            ResourceLoader.loadImage("platform.png"),
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
            ImageButton lvlButton = new ImageButton();
            lvlButton.setImages(ResourceLoader.loadImage("level-button.png"),
                                ResourceLoader.loadImage("level-button-hover.png"),
                                ResourceLoader.loadImage("level-button-selected.png"));
            lvlButton.setFitWidth(SCREEN_WIDTH / 5 * 2);
            lvlButton.setFitHeight(SCREEN_HEIGHT / 8);
            lvlButton.setOnAction(handlers[x]);

            StackPane button = new StackPane();
            button.setAlignment(Pos.CENTER);
            button.getChildren().add(lvlButton);
            button.getChildren().add(new ImageView(ResourceLoader.loadImage("level-" + (x + 1) + ".png")));
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

        root.setCenter(body);
        root.setBottom(getFooter(backButton, helpButton));
        this.game.setScene(new Scene(root));
    }
}
