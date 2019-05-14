import javafx.stage.Stage;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 */
public abstract class BaseScreen implements Constants {
    protected Stage stage;

    public BaseScreen() {}

    public BaseScreen(Stage stage) {
        this.stage = stage;
    }

    public abstract void drawScreen();
}
