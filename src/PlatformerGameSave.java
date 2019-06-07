/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * The platformer game save subclass.
 */
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.*;

import javax.json.*;

/**
 * The platformer game save subclass.
 * <pre>
 * Revision history:
 *  - May 21, 2019: Created ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 *  - Jun 2, 2019: Commented ~Evan Zhang
 *  - Jun 2, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Commented ~Evan Zhang
 *  - Jun 6, 2019: Commented ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public class PlatformerGameSave extends BaseGameSave {
    /** The reference point of the screen */
    public double referencePoint;
    /** The position of the player */
    public Point2D player;
    /** The list of the positions of the removed nodes */
    public List<Point2D> removedNodes;

    /**
     * Constructor
     * @param  referencePoint The reference point of the screen
     * @param  player         The player location
     * @param  removedNodes   The location of the removed blocks on the screen
     * @param  scores         The scores for the game level
     * @param  levelComplete  Whether the level is completed or not
     */
    public PlatformerGameSave(double referencePoint, Point2D player, List<Point2D> removedNodes,
                              int[] scores, boolean levelComplete) {
        super(scores, levelComplete);
        this.referencePoint = referencePoint;
        this.player = player;
        this.removedNodes = removedNodes;
        this.scores = scores;
    }

    /**
     * Converts a point to a JSONObject
     * @param  p The specified point
     * @return   The JsonObject of the specified point
     */
    public static JsonObject pointToJson(Point2D p) {
        return Json.createObjectBuilder()
                   .add("x", p.getX())
                   .add("y", p.getY())
                   .build();
    }

    /**
     * Converts a JSONObject to a point
     * @param  p The specified JSONObject
     * @return   The Point of the specified JSONObject
     */
    public static Point2D jsonToPoint(JsonObject p) {
        return new Point2D(p.getJsonNumber("x").doubleValue(), p.getJsonNumber("y").doubleValue());
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    public static BaseGameSave fromJson(JsonObject data) {
        boolean levelComplete = data.getBoolean("levelComplete", false);
        double referencePoint = data.getJsonNumber("referencePoint").doubleValue();
        Point2D player = jsonToPoint(data.getJsonObject("player"));
        JsonArray jsonScores = data.getJsonArray("scores");
        int[] scores = new int[jsonScores.size()];
        for (int i = 0; i < jsonScores.size(); i++) {
            scores[i] = jsonScores.getInt(i);
        }
        List<Point2D> removedNodes = new ArrayList<Point2D>();
        for (JsonValue obj : data.getJsonArray("removedNodes")) {
            removedNodes.add(jsonToPoint((JsonObject)obj));
        }
        return new PlatformerGameSave(referencePoint, player, removedNodes, scores, levelComplete);
    }
}
