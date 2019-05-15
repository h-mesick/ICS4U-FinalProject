import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
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
 *  - May 15, 2019: Updated ~Evan Zhang
 */
public class LevelTwo extends BasePlatformer {
    public LevelTwo(Game game) {
        super(game);
    }

    protected String getLevelFile() {
        return "../resources/level2.txt";
    }

    protected void handleSpecial(int specialType) {
        System.out.println("Got special " + specialType);
    }
}
