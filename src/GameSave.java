import javax.json.*;

import javafx.geometry.*;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 21, 2019: Created ~Evan Zhang
 */
public class GameSave {
    public static JsonArray pointToJson(Point2D p) {
        return Json.createArrayBuilder()
                   .add(p.getX())
                   .add(p.getY())
                   .build();
    }

    public JsonObject toJson() {
        return null;
    }

    public static GameSave loadFromFile(String data) {
        return null;
    }
}
