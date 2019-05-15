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
 */
public class LevelThree extends BasePlatformer {
    public LevelThree(Game game) {
        super(game);
    }

    protected String getLevelFile() {
        return "../resources/level3.txt";
    }

    protected void handleSpecial(int specialType) {
        System.out.println("Got special " + specialType);
    }
}
