import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 * @author Evan Zhang
 * Revision history:
 * - May 13, 2019: Created ~Evan Zhang
 * - May 22, 2019: Finished - Max Li
 * @version 1
 */
public class MainMenu extends BaseScene {
    public MainMenu(Game game) {
        super(game);
    }

    public void initScene() {
        BorderPane root = new BorderPane();

        VBox body = new VBox(10);
        body.setAlignment(Pos.CENTER);
        body.getChildren().add(new ImageView(ResourceLoader.loadImage("game-logo.png")));
        body.getChildren().add(getMainButton("Play Game", event -> game.updateState(State.LEVEL_SELECT)));
        body.getChildren().add(getMainButton("How To Play", event -> game.updateState(State.TUTORIAL)));
        body.getChildren().add(getMainButton("Help", event -> game.updateState(State.HELP)));
        body.getChildren().add(getMainButton("High Scores", event -> game.updateState(State.HIGH_SCORES)));
        body.getChildren().add(getMainButton("Quit", event -> Platform.exit()));

        root.setCenter(body);

        this.game.setScene(new Scene(root));
    }
}
