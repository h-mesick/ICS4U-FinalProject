import javafx.animation.*;
import javafx.application.Platform;
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
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 22, 2019: Finished ~Max Li
 *  - May 23, 2019: Finishing touches ~Evan Zhang
 *  - May 27, 2019: Commented ~Max Li
 *  - May 28, 2019: Updated ~Evan Zhang
 * @version 1
 */
public class MainMenu extends BaseScene {
    /**
     * Constructor for the MainMenu class.
     *
     * @param game The current game that is running.
     */
    public MainMenu(Game game) {
        super(game);
    }

    /**
     * Initializes the scene to the main menu window.
     */
    public void initScene() {
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

        body.getChildren().add(new ImageView(ResourceLoader.loadImage("main-menu-logo.png")));
        body.getChildren().add(getMainButton("Play", event -> game.updateState(State.LEVEL_SELECT)));
        body.getChildren().add(getMainButton("Help", event -> game.updateState(State.HELP)));
        body.getChildren().add(getMainButton("High Scores", event -> game.updateState(State.HIGH_SCORES)));
        body.getChildren().add(getMainButton("Quit", event -> Platform.exit()));

        root.setCenter(body);

        this.game.setScene(new Scene(root));
    }
}
