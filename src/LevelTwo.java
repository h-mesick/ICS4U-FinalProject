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
 *  - May 15, 2019: Updated ~Evan Zhang
 *  - May 17, 2019: Updated ~Evan Zhang
 *  - May 18, 2019: Updated ~Evan Zhang
 *  - May 19, 2019: Updated ~Evan Zhang
 *  - May 21, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 25, 2019: Updated ~Evan Zhang
 *  - May 26, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 */
public class LevelTwo extends BasePlatformer {
    /** Instance variables */
    private HBox scoreCountOverlay;
    private Text coinText;

    /**
     * Constructor
     * @param  game The current game
     */
    public LevelTwo(Game game) {
        super(game);
    }

    /**
     * Get the level number that this class represents
     * @return The level
     */
    protected int getLevel() {
        return 2;
    }

    /**
     * Get the number of scores to save
     * @return The score count
     */
    protected int getScoreCount() {
        return 1;
    }

    /**
     * Gets the question specified by specialType
     * @param  specialType The question number
     * @return             The question
     */
    protected Question getQuestion(int specialType) {
        String question = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + specialType;
        String[] answers = {
            "a", "b", "c", "dasdasdsadasdasdsadadadsd"
        };
        EventHandler[] handlers = {
            event -> {
                incrementScore(0, 1);
                removeOverlay();
            },
            event -> {
                incrementScore(0, 3);
                removeOverlay();
            },
            event -> {
                incrementScore(0, 10);
                removeOverlay();
            },
            event -> {
                incrementScore(0, -1);
                removeOverlay();
            },
        };
        return new Question(question, answers, handlers);
    }

    /**
     * Initializes the scene
     */
    public void initScene() {
        scoreCountOverlay = new HBox(5);
        scoreCountOverlay.setAlignment(Pos.CENTER);
        scoreCountOverlay.setPadding(new Insets(10));

        ImageView coinImage = new ImageView(ResourceLoader.loadImage("coin.png"));
        coinImage.setPreserveRatio(true);
        coinImage.setFitWidth(40);

        scoreCountOverlay.getChildren().addAll(coinImage, scoresText[0]);

        super.initScene();
        root.getChildren().add(scoreCountOverlay);
    }

    /**
     * Handles when the player touches a special block
     * @param specialType The special block's number
     */
    protected void handleSpecial(int specialType) {
        handleDefaultSpecial(specialType);
    }

    /**
     * Handle when the player reaches the top of the game
     */
    protected void handleFinish() {
        Text finishText = new Text("Congratulations! You completed level two!");
        finishText.setFont(new Font("Verdana", 25));
        finishText.setFill(Color.RED);
        finishText.setTextAlignment(TextAlignment.CENTER);
        finishText.setWrappingWidth(Constants.SCREEN_WIDTH / 3 * 2);

        StackPane nextLevel = getMainButton("Continue to the next level", event -> this.game.updateState(State.LEVEL_THREE), 15);

        setOverlay(initBasicOverlay(finishText, nextLevel));
    }
}
