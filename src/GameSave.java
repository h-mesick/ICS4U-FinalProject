/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * Base class for game saves.
 */
import javax.json.*;

/**
 * Base class for game saves.
 * <pre>
 * Revision history:
 *  - May 21, 2019: Created ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public abstract class GameSave {
    /** Instance variables */
    public int scores[];
    public boolean levelComplete;

    /**
     * GameSave constructor
     * @param  scores         The scores for the game
     * @param  levelComplete  Whether the level is completed or not
     */
    public GameSave(int[] scores, boolean levelComplete) {
        this.scores = scores;
        this.levelComplete = levelComplete;
    }

    /**
     * Gets the JsonObjectBuilder for the GameSave, to be used by its subclasses
     * @return The JsonObjectBuilder
     */
    protected JsonObjectBuilder baseJsonObjectBuilder() {
        JsonArrayBuilder jsonScores = Json.createArrayBuilder();
        for (int s : scores) {
            jsonScores.add(s);
        }

        return Json.createObjectBuilder()
                   .add("levelComplete", levelComplete)
                   .add("scores", jsonScores.build());
    }

    /**
     * Converts the gamesave to a JSONObject
     * @return The converted save
     */
    public abstract JsonObject toJson();

    /**
     * Loads the gamesave from a JSONObject
     * @param  data The json object to load from
     * @return      The loaded gamesave
     */
    public static GameSave fromJson(JsonObject data) {
        return null;
    }
}
