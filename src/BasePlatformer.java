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
public abstract class BasePlatformer extends BaseLevel {
    protected Group root;
    protected boolean keyRight = false, keyLeft = false, keyUp = false;
    protected Level level;
    protected double referencePoint;

    public BasePlatformer(Game game) {
        super(game);
    }

    public void drawLevel() {
        for (int y = 0; y < level.screenLength(); y += Constants.PLATFORM_BLOCK_HEIGHT) {
            for (int x = 0; x < Constants.SCREEN_WIDTH; x += Constants.PLATFORM_BLOCK_WIDTH) {
                if (level.isBlocked(x, y)) {
                    root.getChildren().add (
                        new Sprite(x, y - referencePoint,
                                   Constants.PLATFORM_BLOCK_WIDTH,
                                   Constants.PLATFORM_BLOCK_HEIGHT,
                                   Color.RED)
                    );
                }
            }
        }
    }
}
