import java.util.ArrayList;
import java.util.Collections;

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
 *  - May 28, 2019: Created ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 */
public class Highscores extends BaseScene {
    /**
     * Constructor
     * @param game The current game that is running.
     */
    public Highscores(Game game) {
        super(game);
    }

    /**
     * Initializes the scene
     */
    public void initScene() {
        VBox body = new VBox(10);
        body.setAlignment(Pos.TOP_CENTER);
        body.getChildren().add(new ImageView(ResourceLoader.loadImage("highscores-logo.png")));

        ArrayList<User> users = this.game.getAllUsers();
        Collections.sort(users, Collections.reverseOrder());
        GridPane rankings = new GridPane();
        rankings.setAlignment(Pos.CENTER);
        rankings.setVgap(10);
        rankings.setHgap(10);
        for (int x = 0; x < 10; x++) {
            String username = "Empty", score = "0";
            try {
                User user = users.get(x);
                username = user.username;
                score = "" + user.score;
            } catch (IndexOutOfBoundsException e) {
            }
            Text text = new Text(username);
            text.setFont(new Font("Verdana", 15));
            text.setFill(Color.WHITE);
            Text text2 = new Text(score);
            text2.setFont(new Font("Verdana", 15));
            text2.setFill(Color.WHITE);

            StackPane usernamePane = new StackPane();
            usernamePane.setAlignment(Pos.TOP_LEFT);
            usernamePane.setMinWidth(200);
            usernamePane.getChildren().add(text);
            StackPane scorePane = new StackPane();
            scorePane.setAlignment(Pos.TOP_RIGHT);
            scorePane.setMinWidth(200);
            scorePane.getChildren().add(text2);
            rankings.add(usernamePane, 0, x);
            rankings.add(scorePane, 1, x);
        }
        body.getChildren().add(rankings);

        ImageButton backButton = getMainImageButton("back", event -> game.updateState(State.MAIN_MENU));

        ImageButton helpButton = getMainImageButton("help", event -> {
            game.setNextState(game.getCurrentState());
            game.updateState(State.HELP);
        });

        this.game.setScene(new Scene(getMainRoot(body, getFooter(backButton, helpButton))));
    }
}
