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
 *  - May 14, 2019: Updated ~Evan Zhang
 */
public abstract class BaseLevel extends BaseScene {
    protected AnimationTimer mainTimer;

    public BaseLevel(Game game) {
        super(game);
        mainTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
    }

    public void start() {
        mainTimer.start();
    }

    public void stop() {
        mainTimer.stop();
    }

    protected abstract void update();
}
