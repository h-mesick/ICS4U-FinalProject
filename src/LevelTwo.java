/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * Level two of the game.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

import javax.json.*;

/**
 * Level two of the game.
 * <pre>
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
 *  - May 31, 2019: Updated ~Evan Zhang
 *  - Jun 2, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Commented ~Evan Zhang
 *  - Jun 6, 2019: Updated ~Evan Zhang
 *  - Jun 6, 2019: Commented ~Evan Zhang
 *  - Jun 7, 2019: Finished ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public class LevelTwo extends BasePlatformer {
    /** The JSONArray of all the questions */
    private JsonArray questions;

    /**
     * Constructor
     * @param game The current game
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
     * {@inheritDoc}
     */
    protected int getLevel() {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    protected int getScoreCount() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public void initScene() {
        super.initScene();

        HBox scoreCountOverlay = new HBox(5);
        scoreCountOverlay.setAlignment(Pos.CENTER_LEFT);
        scoreCountOverlay.setPadding(new Insets(10));

        ImageView coinImage = new ImageView(ResourceLoader.loadImage("coin.png"));
        coinImage.setPreserveRatio(true);
        coinImage.setFitWidth(40);

        scoreCountOverlay.getChildren().addAll(coinImage, scoresText[0]);
        root.getChildren().add(scoreCountOverlay);
    }

    /**
     * Gets the question specified by specialType
     * @param specialType The question number
     * @return The question
     */
    protected Question getQuestion(int specialType) {
        JsonObject curObj = questions.getJsonObject(questions.size() - specialType);
        String question = curObj.getString("question");
        ArrayList<JsonObject> choices = new ArrayList<JsonObject>(Arrays.asList(curObj.getJsonArray("choices")
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
     * {@inheritDoc}
     */
    protected void handleSpecial(int specialType) {
        Question question = getQuestion(specialType);
        setOverlay(initBasicOverlay(question.getFormattedQuestion(), question.getFormattedChoices()));
    }

    /**
     * {@inheritDoc}
     */
    protected void handleFinish() {
        Text finishText = new Text("Congratulations! You completed level two!");
        finishText.setFont(Util.getMainFont(25));
        finishText.setFill(Color.WHITE);
        finishText.setTextAlignment(TextAlignment.CENTER);
        finishText.setWrappingWidth(Constants.SCREEN_WIDTH / 3 * 2);

        StackPane nextLevel = Util.getMainButton("Continue to the next level",
                                                 event -> this.game.updateState(State.LEVEL_THREE),
                                                 15);

        setOverlay(initBasicOverlay(finishText, nextLevel));
    }
}
