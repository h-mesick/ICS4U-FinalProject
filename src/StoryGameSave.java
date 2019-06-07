/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * The story game save subclass.
 */
import javax.json.*;

/**
 * The story game save subclass.
 * <pre>
 * Revision history:
 *  - May 30, 2019: Created ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 *  - Jun 3, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Commented ~Evan Zhang
 *  - Jun 6, 2019: Commented ~Evan Zhang
 *  - Jun 7, 2019: Finished ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public class StoryGameSave extends BaseGameSave {
    /** The current position of the dialog */
    public int dialogPosition;

    /**
     * StoryGameSave constructor
     * @param  dialogPosition The position of the dialog
     * @param  scores         The scores for the game
     * @param  levelComplete  Whether the level is completed or not
     */
    public StoryGameSave(int dialogPosition, int[] scores, boolean levelComplete) {
        super(scores, levelComplete);
        this.dialogPosition = dialogPosition;
    }

    /**
     * {@inheritDoc}
     */
    public JsonObject toJson() {
        return baseJsonObjectBuilder()
                   .add("dialogPosition", dialogPosition)
                   .build();
    }

    /**
     * {@inheritDoc}
     */
    public static BaseGameSave fromJson(JsonObject data) {
        boolean levelComplete = data.getBoolean("levelComplete", false);
        JsonArray jsonScores = data.getJsonArray("scores");
        int[] scores = new int[jsonScores.size()];
        for (int i = 0; i < jsonScores.size(); i++) {
            scores[i] = jsonScores.getInt(i);
        }
        int dialogPosition = data.getInt("dialogPosition");
        return new StoryGameSave(dialogPosition, scores, levelComplete);
    }
}
