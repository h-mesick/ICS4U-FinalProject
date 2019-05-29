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
 *  - May 15, 2019: Updated ~Evan Zhang
 *  - May 21, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 25, 2019: Updated ~Evan Zhang
 *  - May 26, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 */
public class LevelThree extends BasePlatformer {
    /** Instance variables */
    private VBox scoreCountOverlay;
    private Text coinText, pointText;
    private int coinCount = 0, pointCount = 0;

    /**
     * Constructor
     * @param  game The current game
     */
    public LevelThree(Game game) {
        super(game);
    }

    /**
     * Get the level number that this class represents
     * @return The level
     */
    protected int getLevel() {
        return 3;
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
     * Increments the score count
     * @param delta The amount to increase the score count
     */
    private void incrementPointCount(int delta) {
        pointCount += delta;
        pointText.setText("" + pointCount);
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
                incrementPointCount(2);
                incrementCoinCount(-1);
                removeOverlay();
            },
            event -> {
                incrementPointCount(6);
                incrementCoinCount(-3);
                removeOverlay();
            },
            event -> {
                incrementPointCount(20);
                incrementCoinCount(-10);
                removeOverlay();
            },
            event -> {
                incrementPointCount(2);
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
        HBox coinCountOverlay = new HBox(5);
        coinCountOverlay.setAlignment(Pos.CENTER);
        coinCountOverlay.setPadding(new Insets(10, 0, 0, 10));

        ImageView coinImage = new ImageView(ResourceLoader.loadImage("coin.png"));
        coinImage.setPreserveRatio(true);
        coinImage.setFitWidth(40);

        coinText = new Text("0");
        coinText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        coinCountOverlay.getChildren().addAll(coinImage, coinText);


        HBox pointCountOverlay = new HBox(5);
        pointCountOverlay.setAlignment(Pos.CENTER);
        pointCountOverlay.setPadding(new Insets(0, 0, 0, 10));

        ImageView pointImage = new ImageView(ResourceLoader.loadImage("star.png"));
        pointImage.setPreserveRatio(true);
        pointImage.setFitWidth(40);

        pointText = new Text("0");
        pointText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        pointCountOverlay.getChildren().addAll(pointImage, pointText);


        scoreCountOverlay = new VBox(0, coinCountOverlay, pointCountOverlay);

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
        Text finishText = new Text("Congratulations! You completed level three!");
        finishText.setFont(new Font("Verdana", 25));
        finishText.setFill(Color.RED);
        finishText.setTextAlignment(TextAlignment.CENTER);
        finishText.setWrappingWidth(Constants.SCREEN_WIDTH / 3 * 2);

        StackPane nextLevel = getMainButton("Finish Game", event -> this.game.updateState(State.MAIN_MENU), 15);

        setOverlay(initBasicOverlay(finishText, nextLevel));
    }

    /**
     * Saves the level state
     * @return The PlatformerGameSave object
     */
    protected PlatformerGameSave save() {
        return super.save(coinCount, pointCount);
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
        incrementPointCount(-pointCount);
        incrementPointCount(save.scores[1]);
    }
}
