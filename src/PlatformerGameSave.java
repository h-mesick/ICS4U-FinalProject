import java.util.ArrayList;

import javax.json.*;

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
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 */
public class PlatformerGameSave extends GameSave {
    /** Instance variables */
    public double referencePoint;
    public Point2D player;
    public ArrayList<Point2D> removedNodes;

    /**
     * Constructor
     * @param  referencePoint The reference point of the screen
     * @param  player         The player location
     * @param  removedNodes   The location of the removed blocks on the screen
     * @param  scores         The scores for the game level
     */
    public PlatformerGameSave(double referencePoint, Point2D player, ArrayList<Point2D> removedNodes,
                              int[] scores, boolean levelComplete) {
        super(scores, levelComplete);
        this.referencePoint = referencePoint;
        this.player = player;
        this.removedNodes = removedNodes;
        this.scores = scores;
    }

    public static JsonObject pointToJson(Point2D p) {
        return Json.createObjectBuilder()
                   .add("x", p.getX())
                   .add("y", p.getY())
                   .build();
    }

    public static Point2D jsonToPoint(JsonObject p) {
        return new Point2D(p.getJsonNumber("x").doubleValue(), p.getJsonNumber("y").doubleValue());
    }

    public JsonObject toJson() {
        JsonArrayBuilder jsonNodes = Json.createArrayBuilder();
        for (Point2D p : removedNodes) {
            jsonNodes = jsonNodes.add(pointToJson(p));
        }
        return baseJsonObjectBuilder()
                   .add("referencePoint", referencePoint)
                   .add("player", pointToJson(player))
                   .add("removedNodes", jsonNodes.build())
                   .build();
    }

    public static GameSave fromJson(JsonObject data) {
        boolean levelComplete = data.getBoolean("levelComplete", false);
        double referencePoint = data.getJsonNumber("referencePoint").doubleValue();
        Point2D player = jsonToPoint(data.getJsonObject("player"));
        JsonArray jsonScores = data.getJsonArray("scores");
        int[] scores = new int[jsonScores.size()];
        for (int i = 0; i < jsonScores.size(); i++) {
            scores[i] = jsonScores.getInt(i);
        }
        ArrayList<Point2D> removedNodes = new ArrayList();
        for (JsonValue obj : data.getJsonArray("removedNodes")) {
            removedNodes.add(jsonToPoint((JsonObject)obj));
        }
        return new PlatformerGameSave(referencePoint, player, removedNodes, scores, levelComplete);
    }
}
