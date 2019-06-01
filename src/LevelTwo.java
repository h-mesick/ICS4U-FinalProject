import java.io.*;
import java.util.*;

import javax.json.*;

import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 14, 2019: Updated ~Evan Zhang
 *  - May 15, 2019: Updated ~Evan Zhang
 *  - May 17, 2019: Updated ~Evan Zhang
 *  - May 18, 2019: Updated ~Evan Zhang
 *  - May 19, 2019: Updated ~Evan Zhang
 *  - May 21, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 25, 2019: Updated ~Evan Zhang
 *  - May 26, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
*/
public class LevelTwo extends BasePlatformer {
    /** Instance variables */
    private HBox scoreCountOverlay;
    private JsonArray questions;

    /**
     * Constructor
     * @param  game The current game
     */
    public LevelTwo(Game game) {
        super(game);
        try (JsonReader reader = Json.createReader(ResourceLoader.loadLevel(getLevelDataFile()))) {
            questions = reader.readArray();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Get the level number that this class represents
     * @return The level
     */
    protected int getLevel() {
        return 2;
    }

    /**
     * Get the number of scores to save
     * @return The score count
     */
    protected int getScoreCount() {
        return 1;
    }

    /**
     * Initializes the scene
     */
    public void initScene() {
        scoreCountOverlay = new HBox(5);
        scoreCountOverlay.setAlignment(Pos.CENTER_LEFT);
        scoreCountOverlay.setPadding(new Insets(10));

        ImageView coinImage = new ImageView(ResourceLoader.loadImage("coin.png"));
        coinImage.setPreserveRatio(true);
        coinImage.setFitWidth(40);

        scoreCountOverlay.getChildren().addAll(coinImage, scoresText[0]);

        super.initScene();
        root.getChildren().add(scoreCountOverlay);
    }

    /**
     * Gets the question specified by specialType
     * @param  specialType The question number
     * @return             The question
     */
    protected Question getQuestion(int specialType) {
        JsonObject curObj = questions.getJsonObject(questions.size() - specialType);
        String question = curObj.getString("question");
        ArrayList<JsonObject> choices = new ArrayList(Arrays.asList(curObj.getJsonArray("choices")
                                                                          .toArray(new JsonObject[0])));
        Collections.shuffle(choices);

        String[] answers = new String[choices.size()];
        for (int i = 0; i < choices.size(); i++) {
            answers[i] = choices.get(i).getString("choice");
        }

        EventHandler[] handlers = new EventHandler[choices.size()];
        for (int i = 0; i < choices.size(); i++) {
            Integer delta = choices.get(i).getInt("score");
            handlers[i] = (event -> {
                incrementScore(0, (int)delta);
                removeOverlay();
            });
        }
        return new Question(question, answers, handlers);
    }

    /**
     * Handles when the player touches a special block
     * @param specialType The special block's number
     */
    protected void handleSpecial(int specialType) {
        Question question = getQuestion(specialType);
        setOverlay(initBasicOverlay(question.getFormattedQuestion(), question.getFormattedChoices()));
    }

    /**
     * Handle when the player reaches the top of the game
     */
    protected void handleFinish() {
        Text finishText = new Text("Congratulations! You completed level two!");
        finishText.setFont(Util.getMainFont(25));
        finishText.setFill(Color.WHITE);
        finishText.setTextAlignment(TextAlignment.CENTER);
        finishText.setWrappingWidth(Constants.SCREEN_WIDTH / 3 * 2);

        StackPane nextLevel = Util.getMainButton("Continue to the next level", event -> this.game.updateState(State.LEVEL_THREE), 15);

        setOverlay(initBasicOverlay(finishText, nextLevel));
    }
}
