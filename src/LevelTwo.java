import java.io.*;

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
            JsonObject obj = reader.readObject();
            questions = obj.getJsonArray("questions");
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
        String[] answers = {
            "A", "B", "C", "D"
        };
        EventHandler[] handlers = new EventHandler[4];
        for (int i = 0; i < 4; i++) {
            Integer delta = curObj.getJsonArray("choices").getInt(i);
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

        StackPane questionPane = new StackPane();
        questionPane.setAlignment(Pos.CENTER);
        Text questionText = new Text(question.getQuestion());
        questionText.setFont(new Font("Verdana", 18));
        questionText.setFill(Color.WHITE);
        questionText.setWrappingWidth(Constants.SCREEN_WIDTH - 250);
        questionPane.getChildren().add(questionText);

        GridPane answersPane = new GridPane();
        answersPane.setAlignment(Pos.CENTER);
        answersPane.setHgap(10);
        answersPane.setVgap(10);

        for (int x = 0; x < 4; x++) {
            StackPane button = getMainButton(question.getAnswers()[x], question.getHandlers()[x], 15);
            answersPane.add(button, x / 2, x % 2);
        }

        setOverlay(initBasicOverlay(questionPane, answersPane));
    }

    /**
     * Handle when the player reaches the top of the game
     */
    protected void handleFinish() {
        Text finishText = new Text("Congratulations! You completed level two!");
        finishText.setFont(new Font("Verdana", 25));
        finishText.setFill(Color.RED);
        finishText.setTextAlignment(TextAlignment.CENTER);
        finishText.setWrappingWidth(Constants.SCREEN_WIDTH / 3 * 2);

        StackPane nextLevel = getMainButton("Continue to the next level", event -> this.game.updateState(State.LEVEL_THREE), 15);

        setOverlay(initBasicOverlay(finishText, nextLevel));
    }
}
