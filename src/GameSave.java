import javax.json.*;

import javafx.geometry.*;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 21, 2019: Created ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 */
public abstract class GameSave {
    public int scores[];
    public boolean levelComplete;

    public GameSave(int[] scores, boolean levelComplete) {
        this.scores = scores;
        this.levelComplete = levelComplete;
    }

    protected JsonObjectBuilder baseJsonObjectBuilder() {
        JsonArrayBuilder jsonScores = Json.createArrayBuilder();
        for (int s : scores) {
            jsonScores.add(s);
        }

        return Json.createObjectBuilder()
                   .add("levelComplete", levelComplete)
                   .add("scores", jsonScores.build());
    }

    public abstract JsonObject toJson();
    public static GameSave fromJson() {
        return null;
    }
}
