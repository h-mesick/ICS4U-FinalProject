import java.util.*;

import javafx.animation.*;
import javafx.beans.property.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.input.*;
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
 *  - May 17, 2019: Updated ~Evan Zhang
 *  - May 18, 2019: Updated ~Evan Zhang
 *  - May 19, 2019: Updated ~Evan Zhang
 *  - May 21, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 26, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Updated ~Evan Zhang
 */
public abstract class BaseLevel extends BaseScene {
    /** Instance variables */
    private Group baseRoot = new Group();
    protected AnimationTimer mainTimer;
    protected Node currentOverlay;
    protected Set<KeyCode> pressedKeys = new HashSet();
    protected Group root;

    /**
     * Constructor for the BaseLevel class.
     *
     * @param game The current game that is running.
     */
    public BaseLevel(Game game) {
        super(game);

        SimpleLongProperty prev = new SimpleLongProperty();
        mainTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (prev.get() == 0) {
                    update();
                } else {
                    long iterations = (now - prev.get()) / 5000000;
                    for (int x = 0; x < iterations; x++) {
                        update();
                    }
                }
                prev.set(now);
            }
        };
    }

    /**
     * Saves the game on exit.
     */
    public void onExit() {
        this.game.levelSave[getLevel() - 1] = save();
    }

    /**
     * Loads a game on enter.
     */
    public void onEnter() {
        if (this.game.levelSave[getLevel() - 1] != null) {
            load(this.game.levelSave[getLevel() - 1]);
        }
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
            StackPane b = getMainButton(buttonNames[x], buttonHandlers[x]);
            overlay.getChildren().add(b);
        }
        return overlay;
    }


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


    protected void setOverlay(Node overlay) {
        if (currentOverlay != null) {
            System.err.println("Warning: overlay is already set.");
            return;
        }
        root.setEffect(new GaussianBlur());
        baseRoot.getChildren().add(overlay);
        currentOverlay = overlay;
    }

    protected void removeOverlay() {
        if (currentOverlay == null) {
            System.err.println("Warning: overlay is not set.");
            return;
        }
        root.setEffect(null);
        baseRoot.getChildren().remove(currentOverlay);
        currentOverlay = null;
    }

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

    protected void handleKeyPressed(KeyCode key) {}
    protected void handleKeyReleased(KeyCode key) {}

    protected abstract void update();
    protected abstract int getLevel();
    protected abstract GameSave save();
    protected abstract void load(GameSave save);
}
