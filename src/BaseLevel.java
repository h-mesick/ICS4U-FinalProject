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

    private class Time {
        public long time;
        public Time(long time) {
            set(time);
        }
        public void set(long time) {
            this.time = time;
        }
    }

    public BaseLevel(Game game) {
        super(game);

        Time prev = new Time(0);
        mainTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (prev.time == 0) {
                    update();
                } else {
                    long iterations = (now - prev.time) / 5000000;
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

    protected abstract void update();
}
