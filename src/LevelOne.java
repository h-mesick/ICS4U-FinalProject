import javafx.animation.*;
import javafx.event.*;
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
 *  - May 17, 2019: Updated ~Evan Zhang
 *  - May 21, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 */
public class LevelOne extends BaseLevel {
    /**
     * Constructor
     * @param  game The current game
     */
    public LevelOne(Game game) {
        super(game);
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
        return 0;
    }

    /**
     * Initializes the scene
     */
    public void initScene() {
        root = new Group();
        Canvas c = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(c);
        this.game.setScene(new Scene(root));

        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
        gc.setFont(theFont);
        gc.fillText("Hello, World!", 60, 50);
        gc.strokeText("Hello, World!", 60, 50);
    }

    /**
     * Update method called every game tick
     */
    protected void update() {
    }

    protected void handleFinish() {
    }

    /**
     * Saves the level state
     * @return The GameSave object
     */
    protected GameSave save() {
        return null;
    }

    /**
     * Load the level state from a GameSave
     * @param baseSave The game save to load from
     */
    protected void load(GameSave save) {
    }
}
