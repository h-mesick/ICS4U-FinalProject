/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * Base class for levels.
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javafx.animation.*;
import javafx.beans.property.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.effect.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

/**
 * Base class for levels.
 * <pre>
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 14, 2019: Updated ~Evan Zhang
 *  - May 17, 2019: Updated ~Evan Zhang
 *  - May 18, 2019: Updated ~Evan Zhang
 *  - May 19, 2019: Updated ~Evan Zhang
 *  - May 21, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 26, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Updated ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
 *  - May 31, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 *  - Jun 2, 2019: Commented ~Evan Zhang
 *  - Jun 2, 2019: Updated ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public abstract class BaseLevel extends BaseScene {
    /** The base pane of the level */
    private Group baseRoot = new Group();
    /** The main pane of the level */
    protected Group root;
    /** The timer to run the game */
    protected AnimationTimer mainTimer;
    /** The current set overlay */
    protected Node currentOverlay;
    /** The escape overlay */
    protected VBox escapeOverlay;
    /** The set of pressed keys */
    protected Set<KeyCode> pressedKeys = new HashSet<KeyCode>();

    /** The scores that are kept track of */
    protected int[] scores;
    /** The text that displays the scores */
    protected Text[] scoresText;
    /** Whether the level is completed */
    protected boolean levelComplete;

    /**
     * Constructor for the BaseLevel class.
     * @param game The current game that is running.
     */
    public BaseLevel(Game game) {
        super(game);

        SimpleLongProperty prev = new SimpleLongProperty();
        SimpleLongProperty prevExtra = new SimpleLongProperty();
        mainTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (prev.get() == 0) {
                    update();
                } else {
                    long TPS = 7000000;
                    long diff = now - prev.get() + prevExtra.get();
                    long iterations = diff / TPS;
                    prevExtra.set(diff % TPS);
                    for (int x = 0; x < iterations; x++) {
                        update();
                    }
                }
                prev.set(now);
            }
        };

        escapeOverlay = initEscapeOverlay();

        scores = new int[getScoreCount()];
        scoresText = new Text[getScoreCount()];

        for (int i = 0; i < getScoreCount(); i++) {
            scoresText[i] = new Text("0");
            scoresText[i].setFont(Util.getDefaultFont(22));
            scoresText[i].setStyle("-fx-font-weight: bold");
        }
    }

    /**
     * Increments the score for the user given the index
     * @param index The index to increment
     * @param delta The amount to increment (can be negative)
     */
    protected void incrementScore(int index, int delta) {
        scores[index] += delta;
        scoresText[index].setText("" + scores[index]);
    }

    /**
     * Reload the user's scores
     * @param newScores The new scores
     */
    protected void loadScores(int[] newScores) {
        newScores = Arrays.copyOf(newScores, getScoreCount());
        for (int i = 0; i < getScoreCount(); i++) {
            scores[i] = 0;
            incrementScore(i, newScores[i]);
        }
    }

    /**
     * Saves the game on exit.
     */
    public void onExit() {
        this.game.currentUser.levelSaves[getLevel() - 1] = save();
    }

    /**
     * Loads a game on enter.
     */
    public void onEnter() {
        BaseGameSave save = this.game.currentUser.levelSaves[getLevel() - 1];
        if (save != null) {
            load(save);
        } else {
            onFirstEnter();
        }
    }

    /**
     * Called when it is the user's first time entering the level in order to setup the necessary BaseGameSave
     * as well as initialize the tutorial.
     */
    protected void onFirstEnter() {
        Tutorial tutorial = new Tutorial(this, getLevel());
        root.setOnMouseClicked(event -> tutorial.nextDialog());
        if (getLevel() > 1) {
            BaseGameSave prevSave = this.game.currentUser.levelSaves[getLevel() - 2];
            if (prevSave != null) {
                loadScores(prevSave.scores);
                return;
            }
        }
        loadScores(new int[getScoreCount()]);
    }

    /**
     * Starts the timer.
     */
    public void start() {
        mainTimer.start();
    }

    /**
     * Stops the timer.
     */
    public void stop() {
        mainTimer.stop();
    }

    /**
     * Creates the pause menu.
     * @return The pause menu to be overlaid.
     */
    protected VBox initEscapeOverlay() {
        VBox overlay = new VBox(10);
        overlay.setPadding(new Insets(100));
        overlay.setAlignment(Pos.CENTER);

        overlay.setMinWidth(Constants.SCREEN_WIDTH);
        overlay.setMinHeight(Constants.SCREEN_HEIGHT / 3);

        String[] buttonNames = {
            "Resume",
            "Help",
            "Level Select",
            "Main Menu",
        };

        EventHandler[] buttonHandlers = {
            event -> removeOverlay(),
            event -> {
                game.setNextState(game.getCurrentState());
                game.updateState(State.HELP);
            },
            event -> game.updateState(State.LEVEL_SELECT),
            event -> game.updateState(State.MAIN_MENU),
        };

        for (int x = 0; x < buttonNames.length; x++) {
            StackPane b = Util.getMainButton(buttonNames[x], buttonHandlers[x]);
            overlay.getChildren().add(b);
        }
        return overlay;
    }

    /**
     * Initializes a barebone overlay
     * @param nodes The nodes to place on the overlay
     * @return The overlay as a StackPane
     */
    protected StackPane initBasicOverlay(Node... nodes) {
        StackPane overlayBase = new StackPane();
        overlayBase.setAlignment(Pos.CENTER);
        overlayBase.setMinWidth(Constants.SCREEN_WIDTH);

        VBox overlay = new VBox(10);
        overlay.setPadding(new Insets(50, 25, 50, 25));
        overlay.setAlignment(Pos.CENTER);
        overlay.getChildren().addAll(nodes);

        overlayBase.getChildren().add(overlay);
        overlayBase.setMargin(overlay, new Insets(100, 50, 100, 50));

        return overlayBase;
    }

    /**
     * Sets the overlay to the current overlay
     * @param overlay The overlay to set as the current overlay
     */
    protected void setOverlay(Node overlay) {
        if (currentOverlay != null) {
            System.err.println("Warning: overlay is already set.");
            return;
        }
        ColorAdjust adj = new ColorAdjust(0, -0.1, -0.6, 0);
        root.setEffect(adj);
        baseRoot.getChildren().add(overlay);
        currentOverlay = overlay;
    }

    /**
     * Removes the set overlay, if present
     */
    protected void removeOverlay() {
        if (currentOverlay == null) {
            System.err.println("Warning: overlay is not set.");
            return;
        }
        root.setEffect(null);
        baseRoot.getChildren().remove(currentOverlay);
        currentOverlay = null;
    }

    /**
     * Sets the scene for the current level
     * @param root The root to use for the current scene
     */
    protected void setScene(Parent root) {
        baseRoot.getChildren().add(root);
        Scene scene = new Scene(baseRoot);
        scene.setOnKeyPressed(e -> {
            pressedKeys.add(e.getCode());
            handleKeyPressed(e.getCode());
        });
        scene.setOnKeyReleased(e -> {
            pressedKeys.remove(e.getCode());
            handleKeyReleased(e.getCode());
        });
        this.game.setScene(scene);
    }

    /**
     * Gets the level file for the current level
     * @return The level filename
     */
    protected String getLevelFile() {
        return "level-" + getLevel() + ".txt";
    }

    /**
     * Gets the level data file for the current level
     * @return The level data filename
     */
    protected String getLevelDataFile() {
        return "level-data-" + getLevel() + ".txt";
    }

    /**
     * Called when the level is completed
     */
    protected void onFinish() {
        this.levelComplete = true;
        handleFinish();
        stop();
    }

    /**
     * Returns whether there is an overlay set or not
     * @return Whether there is an overlay set or not
     */
    protected boolean overlayVisible() {
        return currentOverlay != null;
    }

    /**
     * Called whenever a key is pressed
     * @param key The key that is pressed
     */
    protected void handleKeyPressed(KeyCode key) {
        switch (key) {
            case ESCAPE:
                if (overlayVisible()) {
                    if (currentOverlay != null && currentOverlay.equals(escapeOverlay)) {
                        removeOverlay();
                    }
                } else {
                    setOverlay(escapeOverlay);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Called whenever a key is released
     * @param key The key that was released
     */
    protected void handleKeyReleased(KeyCode key) {
        /** No key needs to be handled by default */
    }

    /**
     * Called on every game tick
     */
    protected abstract void update();

    /**
     * Gets the level number for the current level
     * @return The level number of the current level
     */
    protected abstract int getLevel();

    /**
     * Gets the number of scores to keep track of
     * @return The number of scores to keep track of
     */
    protected abstract int getScoreCount();

    /**
     * Called when the game is completed
     */
    protected abstract void handleFinish();

    /**
     * Saves the current game to a BaseGameSave object
     * @return The BaseGameSave object representing this level
     */
    protected abstract BaseGameSave save();

    /**
     * Loads the game from a BaseGameSave object
     * @param baseSave The BaseGameSave object
     */
    protected void load(BaseGameSave baseSave) {
        this.levelComplete = baseSave.levelComplete;
        loadScores(baseSave.scores);
    }
}
