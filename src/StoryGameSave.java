import javax.json.*;

/**
 * Revision history:
 *  - May 30, 2019: Created ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 * @author Evan Zhang
 * @version 1
 */
public class StoryGameSave extends GameSave {
    /** Instance variables */
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
    public static GameSave fromJson(JsonObject data) {
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
