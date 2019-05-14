import javafx.stage.Stage;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 */
public abstract class BaseScene implements Constants {
    protected Game game;

    public BaseScene() {}

    public BaseScene(Game game) {
        this.game = game;
    }

    public abstract void initScene();
}
