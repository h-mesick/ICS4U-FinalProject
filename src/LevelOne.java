import javafx.animation.*;
import javafx.event.*;
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
 *  - May 17, 2019: Updated ~Evan Zhang
 *  - May 21, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 */
public class LevelOne extends BaseLevel {
    /**
     * Constructor
     * @param  game The current game
     */
    public LevelOne(Game game) {
        super(game);
        root = new Group();
    }

    /**
     * Get the level number that this class represents
     * @return The level
     */
    protected int getLevel() {
        return 1;
    }
    /**
     * Get the number of scores to save
     * @return The score count
     */
    protected int getScoreCount() {
        return 1;
    }

    /**
     * Initializes the scene
     */
    public void initScene() {
        ImageView background = new ImageView(ResourceLoader.loadImage("background.png"));
        background.setFitWidth(Constants.SCREEN_WIDTH);
        background.setFitHeight(Constants.SCREEN_HEIGHT);
        root.getChildren().add(background);
        setScene(root);
        start();
    }

    /**
     * Update method called every game tick
     */
    protected void update() {
        if (Math.random() > 0.99) {
            onFinish();
        }
    }

    protected void handleFinish() {
        StackPane nextLevel = initBasicOverlay(getMainButton("Proceed to the next level",
                                                             event -> this.game.updateState(State.LEVEL_TWO), 15));
        setOverlay(nextLevel);
    }

    /**
     * Saves the level state
     * @return The GameSave object
     */
    protected GameSave save() {
        return new GameSave(scores, levelComplete);
    }

    /**
     * Load the level state from a GameSave
     * @param baseSave The game save to load from
     */
    protected void load(GameSave save) {
        loadScores(save.scores);
        this.levelComplete = levelComplete;
    }
}
