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
 */
public class StoryGameSave extends GameSave {
    /** Instance variables */
    public int dialogPosition;

    public StoryGameSave(int dialogPosition, int[] scores, boolean levelComplete) {
        super(scores, levelComplete);
        this.dialogPosition = dialogPosition;
    }

    public JsonObject toJson() {
        return baseJsonObjectBuilder()
                   .add("dialogPosition", dialogPosition)
                   .build();
    }

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
