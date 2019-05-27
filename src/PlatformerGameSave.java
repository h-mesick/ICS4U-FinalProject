import java.util.ArrayList;

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
 *  - May 21, 2019: Created ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 */
public class PlatformerGameSave extends GameSave {
    /** Instance variables */
    public double referencePoint;
    public Node player;
    public ArrayList<Node> removedNodes;
    public int[] scores;

    /**
     * Constructor
     * @param  referencePoint The reference point of the screen
     * @param  player         The player object
     * @param  removedNodes   The removed blocks on the screen
     * @param  scores         The scores for the game level
     */
    public PlatformerGameSave(double referencePoint, Node player, ArrayList<Node> removedNodes, int... scores) {
        this.referencePoint = referencePoint;
        this.player = player;
        this.removedNodes = removedNodes;
        this.scores = scores;
    }
}
