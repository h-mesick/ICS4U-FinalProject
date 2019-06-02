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
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 22, 2019: Finished ~Max Li
 *  - May 23, 2019: Finishing touches ~Evan Zhang
 *  - May 27, 2019: Commented ~Max Li
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 *  - May 31, 2019: Updated ~Evan Zhang
 * @author Evan Zhang
 * @version 1
 */
public class MainMenu extends BaseScene {
    /**
     * Constructor for the MainMenu class.
     * @param game The current game that is running.
     */
    public MainMenu(Game game) {
        super(game);
    }

    /**
     * {@inheritDoc}
     */
    public void initScene() {
        VBox body = new VBox(10);
        body.setAlignment(Pos.TOP_CENTER);

        body.getChildren().add(new ImageView(ResourceLoader.loadImage("main-menu-logo.png")));
        body.getChildren().add(Util.getMainButton("Play", event -> game.updateState(State.LEVEL_SELECT)));
        body.getChildren().add(Util.getMainButton("Help", event -> game.updateState(State.HELP)));
        body.getChildren().add(Util.getMainButton("High Scores", event -> game.updateState(State.HIGH_SCORES)));
        body.getChildren().add(Util.getMainButton("Quit", event -> Platform.exit()));

        Text userGreeting = new Text("Hello, " + this.game.currentUser.username + "!");
        userGreeting.setFont(Util.getMainFont(14));
        userGreeting.setFill(Color.WHITE);
        Text about = new Text("Created by Evan Zhang and Max Li");
        about.setFont(Util.getMainFont(14));
        about.setFill(Color.WHITE);

        this.game.setScene(new Scene(Util.getMainRoot(body, Util.getFooter(userGreeting, about))));
    }
}
