import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
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
 *  - May 14, 2019: Updated ~Evan Zhang
 *  - May 15, 2019: Updated ~Evan Zhang
 *  - May 17, 2019: Updated ~Evan Zhang
 */
public class LevelTwo extends BasePlatformer {
    public LevelTwo(Game game) {
        super(game);
    }

    protected String getLevelFile() {
        return "level2.txt";
    }

    protected void handleSpecial(int specialType) {
        StackPane overlayBase = new StackPane();
        overlayBase.setAlignment(Pos.CENTER);
        overlayBase.setMinWidth(Constants.SCREEN_WIDTH);

        VBox overlay = new VBox(10);
        overlay.setPadding(new Insets(50, 25, 50, 25));
        overlay.setAlignment(Pos.CENTER);

        overlay.setBackground(new Background(new BackgroundFill(
            new Color(0, 0, 0, 0.5),
            new CornerRadii(10),
            new Insets(0)
        )));

        //TODO: begin add actual questions
        String question = "" + specialType;
        String[] answers = {
            "a", "b", "c", "dasdasdsadasdasdsadadadsd"
        };
        EventHandler[] handlers = {
            event -> removeOverlay(),
            event -> game.updateState(State.MAIN_MENU),
            event -> game.updateState(State.MAIN_MENU),
            event -> game.updateState(State.MAIN_MENU),
        };
        //end add actual questions

        FlowPane questionPane = new FlowPane();
        questionPane.setAlignment(Pos.CENTER);
        Text questionText = new Text(question);
        questionText.setFill(Color.WHITE);
        questionPane.getChildren().add(questionText);

        GridPane answersPane = new GridPane();
        answersPane.setAlignment(Pos.CENTER);
        answersPane.setHgap(10);
        answersPane.setVgap(10);

        for (int x = 0; x < 4; x++) {
            StackPane button = getMainButton(answers[x], handlers[x], 15);
            answersPane.add(button, x / 2, x % 2);
        }
        overlay.getChildren().addAll(questionPane, answersPane);

        overlayBase.getChildren().add(overlay);
        overlayBase.setMargin(overlay, new Insets(100, 50, 100, 50));

        setOverlay(overlayBase);
    }
}
