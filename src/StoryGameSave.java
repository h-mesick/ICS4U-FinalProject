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
 *  - May 30, 2019: Created ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
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
     * Converts the gamesave to a JSONObject
     * @return The converted save
     */
    public JsonObject toJson() {
        return baseJsonObjectBuilder()
                   .add("dialogPosition", dialogPosition)
                   .build();
    }

    /**
     * Loads the gamesave from a JSONObject
     * @param  data The json object to load from
     * @return      The loaded gamesave
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
