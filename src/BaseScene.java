import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.*;

/**
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 16, 2019: Updated ~Evan Zhang
 *  - May 17, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
 *  - May 31, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 * @author Evan Zhang
 * @version 1
 */
public abstract class BaseScene {
    /** Instance variables */
    protected Game game;

    /**
     * Default constructor
     */
    public BaseScene() {}

    /**
     * Constructor
     * @param  game The game to link with
     */
    public BaseScene(Game game) {
        this.game = game;
    }

    /**
     * Initializes the scene
     */
    public abstract void initScene();

    /** Called on enter to the scene */
    public void onExit() {}
    /** Called on exit from the scene */
    public void onEnter() {}
}
