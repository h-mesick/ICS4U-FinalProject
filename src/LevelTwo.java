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
 */
public class LevelTwo extends BasePlatformer {
    /** Instance variables */
    private HBox scoreCountOverlay;
    private Text coinText;
    private int coinCount = 0;

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
     * Increments the coin count
     * @param delta The amount to increase the coin count
     */
    private void incrementCoinCount(int delta) {
        coinCount += delta;
        coinText.setText("" + coinCount);
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
                incrementCoinCount(1);
                removeOverlay();
            },
            event -> {
                incrementCoinCount(3);
                removeOverlay();
            },
            event -> {
                incrementCoinCount(10);
                removeOverlay();
            },
            event -> {
                incrementCoinCount(-1);
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

        coinText = new Text("0");
        coinText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        scoreCountOverlay.getChildren().addAll(coinImage, coinText);

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

    /**
     * Saves the level state
     * @return The PlatformerGameSave object
     */
    protected PlatformerGameSave save() {
        return new PlatformerGameSave(referencePoint, player, removedNodes, coinCount);
    }

    /**
     * Load the level state from a GameSave
     * @param baseSave The game save to load from
     */
    protected void load(GameSave baseSave) {
        PlatformerGameSave save = (PlatformerGameSave)baseSave;
        super.load(save);
        incrementCoinCount(-coinCount);
        incrementCoinCount(save.scores[0]);
    }
}
