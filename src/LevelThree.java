/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * Level three of the game.
 */
import java.util.ArrayList;
import java.util.Arrays;

import javax.json.*;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

/**
 * Level three of the game.
 * <pre>
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 15, 2019: Updated ~Evan Zhang
 *  - May 21, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 25, 2019: Updated ~Evan Zhang
 *  - May 26, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
 *  - May 31, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Updated ~Evan Zhang
 *  - Jun 2, 2019: Updated ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public class LevelThree extends BasePlatformer {
    /** Instance variables */
    private JsonArray questions;

    /**
     * Constructor
     * @param  game The current game
     */
    public LevelThree(Game game) {
        super(game);
        try (JsonReader reader = Json.createReader(ResourceLoader.loadLevel(getLevelDataFile()))) {
            questions = reader.readArray();
        } catch (Exception e) {
            throw e;
        }
        this.level.setSpecialImage("star.png");
    }

    /**
     * {@inheritDoc}
     */
    protected int getLevel() {
        return 3;
    }

    /**
     * {@inheritDoc}
     */
    protected int getScoreCount() {
        return 2;
    }

    /**
     * {@inheritDoc}
     */
    public void initScene() {
        HBox coinCountOverlay = new HBox(5);
        coinCountOverlay.setAlignment(Pos.CENTER_LEFT);
        coinCountOverlay.setPadding(new Insets(10, 0, 0, 10));

        ImageView coinImage = new ImageView(ResourceLoader.loadImage("coin.png"));
        coinImage.setPreserveRatio(true);
        coinImage.setFitWidth(40);

        coinCountOverlay.getChildren().addAll(coinImage, scoresText[0]);


        HBox pointCountOverlay = new HBox(5);
        pointCountOverlay.setAlignment(Pos.CENTER_LEFT);
        pointCountOverlay.setPadding(new Insets(0, 0, 0, 10));

        ImageView pointImage = new ImageView(ResourceLoader.loadImage("star.png"));
        pointImage.setPreserveRatio(true);
        pointImage.setFitWidth(40);

        pointCountOverlay.getChildren().addAll(pointImage, scoresText[1]);


        VBox scoreCountOverlay = new VBox(0, coinCountOverlay, pointCountOverlay);

        super.initScene();
        root.getChildren().add(scoreCountOverlay);
    }

    /**
     * Gets the question specified by specialType
     * @param  specialType The question number
     * @return             The question
     */
    protected Question getQuestion(int specialType) {
        JsonObject curObj = questions.getJsonObject(specialType - 1);
        String type = curObj.getString("type");
        String question = curObj.getString("question");
        int cost = curObj.getInt("cost");
        if (type.equals("purchase")) {
            question += " (Cost to purchase: " + cost + ")";
        }
        ArrayList<JsonObject> choices = new ArrayList<JsonObject>(Arrays.asList(curObj.getJsonArray("choices")
                                                                                      .toArray(new JsonObject[0])));
        String[] answers = new String[choices.size()];
        for (int i = 0; i < choices.size(); i++) {
            answers[i] = choices.get(i).getString("choice");
        }

        EventHandler[] handlers = new EventHandler[choices.size()];
        for (int i = 0; i < choices.size(); i++) {
            Integer scoreDelta = choices.get(i).getInt("score");
            Integer costDelta = answers[i].equals("Yes") ? cost : 0;
            handlers[i] = (event -> {
                incrementScore(0, -(int)costDelta);
                incrementScore(1, (int)scoreDelta);
                removeOverlay();
            });
            if (costDelta > 0 && costDelta > scores[0]) {
                handlers[i] = null;
            }
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
        Text finishText = new Text("Congratulations! You completed level three!");
        finishText.setFont(Util.getMainFont(25));
        finishText.setFill(Color.WHITE);
        finishText.setTextAlignment(TextAlignment.CENTER);
        finishText.setWrappingWidth(Constants.SCREEN_WIDTH / 3 * 2);

        StackPane nextLevel = Util.getMainButton("Finish Game", event -> this.game.updateState(State.MAIN_MENU), 15);

        setOverlay(initBasicOverlay(finishText, nextLevel));
    }
}
