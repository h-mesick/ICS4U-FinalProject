import java.util.*;

import javafx.animation.*;
import javafx.beans.property.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
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
 */
public abstract class BaseLevel extends BaseScene {
    protected AnimationTimer mainTimer;
    protected Node currentOverlay;
    protected Set<KeyCode> pressedKeys = new HashSet();
    protected Group root;

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

    public void start() {
        mainTimer.start();
    }

    public void stop() {
        mainTimer.stop();
    }

    protected VBox initEscapeOverlay() {
        VBox overlay = new VBox(10);
        overlay.setPadding(new Insets(100));
        overlay.setAlignment(Pos.CENTER);

        overlay.setMinWidth(Constants.SCREEN_WIDTH);
        overlay.setMinHeight(Constants.SCREEN_HEIGHT / 3);

        String[] buttonNames = {
            "Resume",
            "Level Select",
            "Main Menu",
        };

        EventHandler[] buttonHandlers = {
            event -> removeOverlay(),
            event -> game.updateState(State.LEVEL_SELECT),
            event -> game.updateState(State.MAIN_MENU),
        };

        for (int x = 0; x < buttonNames.length; x++) {
            StackPane b = getMainButton(buttonNames[x], buttonHandlers[x]);
            overlay.getChildren().add(b);
        }
        return overlay;
    }

    protected void setOverlay(Node overlay) {
        if (currentOverlay != null) {
            System.err.println("Warning: overlay is already set.");
            return;
        }
        root.getChildren().add(overlay);
        currentOverlay = overlay;
    }

    protected void removeOverlay() {
        if (currentOverlay == null) {
            System.err.println("Warning: overlay is not set.");
            return;
        }
        root.getChildren().remove(currentOverlay);
        currentOverlay = null;
    }

    protected void setScene(Parent root) {
        Scene scene = new Scene(root);
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
}
