import javafx.stage.Stage;


/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created
 */
public abstract class BaseScreen {
    protected Stage stage;

    public BaseScreen() {}

    public BaseScreen(Stage stage) {
        this.stage = stage;
    }

    protected abstract void drawScreen();
}
