import javafx.stage.Stage;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 */
public abstract class BaseScene implements Constants {
    protected Stage stage;

    public BaseScene() {}

    public BaseScene(Stage stage) {
        this.stage = stage;
    }

    public abstract void drawScreen();
}
