import java.util.List;
import java.util.Collections;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

/**
 * Revision history:
 *  - May 28, 2019: Created ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
 *  - May 31, 2019: Updated ~Evan Zhang
 * @author Evan Zhang
 * @version 1
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
     * {@inheritDoc}
     */
    public void initScene() {
        VBox body = new VBox(10);
        body.setAlignment(Pos.TOP_CENTER);
        body.getChildren().add(new ImageView(ResourceLoader.loadImage("highscores-logo.png")));

        List<User> users = this.game.getAllUsers();
        Collections.sort(users, Collections.reverseOrder());
        GridPane rankings = new GridPane();
        rankings.setAlignment(Pos.CENTER);
        rankings.setVgap(10);
        rankings.setHgap(10);
        for (int x = 0; x < 10; x++) {
            String username = "-----", score = "--";
            if (x < users.size()) {
                User user = users.get(x);
                username = user.username;
                score = "" + user.score;
            }
            Text text = new Text(username);
            text.setFont(Util.getMainFont(15));
            text.setFill(Color.WHITE);
            Text text2 = new Text(score);
            text2.setFont(Util.getMainFont(15));
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

        ImageButton backButton = Util.getMainImageButton("back", event -> game.updateState(State.MAIN_MENU));

        ImageButton helpButton = Util.getMainImageButton("help", event -> {
            game.setNextState(game.getCurrentState());
            game.updateState(State.HELP);
        });

        this.game.setScene(new Scene(Util.getMainRoot(body, Util.getFooter(backButton, helpButton))));
    }
}
