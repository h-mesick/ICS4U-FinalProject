import java.util.ArrayList;

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
 *  - May 21, 2019: Created ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 */
public class PlatformerGameSave extends GameSave {
    /** Instance variables */
    public double referencePoint;
    public Point2D player;
    public ArrayList<Point2D> removedNodes;
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
        this.player = new Point2D(player.getTranslateX(), player.getTranslateY());
        this.removedNodes = new ArrayList();
        for (Node n : removedNodes) {
            this.removedNodes.add(new Point2D(n.getTranslateX(), n.getTranslateY()));
        }
        this.scores = scores;
    }

    public String saveToFile() {
        String ret = "";
        ret += referencePoint + "\n";
        ret += player.getX() + " " + player.getY() + "\n";
        for (int i = 0; i < scores.length; i++) {
            ret += "" + scores[i];
            if (i == scores.length - 1) {
                ret += "\n";
            } else {
                ret += " ";
            }
        }
        ret += "-----\n";
        for (Point2D p : removedNodes) {
            ret += p.getX() + " " + p.getY() + "\n";
        }
        return ret;
    }

/*    public static GameSave loadFromFile(String data) {
        try {
            String[] splitData = data.split("-----\n");
            String[] mainData = splitData[0].split("\n");
            String[] removedNodeData = splitData[1].split("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
