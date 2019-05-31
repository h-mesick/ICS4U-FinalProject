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
 *  - May 15, 2019: Updated ~Evan Zhang
 *  - May 21, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 25, 2019: Updated ~Evan Zhang
 *  - May 26, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
 */
public class LevelThree extends BasePlatformer {
    /** Instance variables */
    private VBox scoreCountOverlay;

    /**
     * Constructor
     * @param  game The current game
     */
    public LevelThree(Game game) {
        super(game);
    }

    /**
     * Get the level number that this class represents
     * @return The level
     */
    protected int getLevel() {
        return 3;
    }

    /**
     * Get the number of scores to save
     * @return The score count
     */
    protected int getScoreCount() {
        return 2;
    }

    /**
     * Gets the question specified by specialType
     * @param  specialType The question number
     * @return             The question
     */
    protected Question getQuestion(int specialType) {
        String question = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + specialType;
        String[] answers = {
            "a", "b", "c", "dasdasdsadasdasdsadadadsd"
        };
        EventHandler[] handlers = {
            event -> {
                incrementScore(0, -1);
                incrementScore(1, 2);
                removeOverlay();
            },
            event -> {
                incrementScore(0, -3);
                incrementScore(1, 6);
                removeOverlay();
            },
            event -> {
                incrementScore(0, -10);
                incrementScore(1, 20);
                removeOverlay();
            },
            event -> {
                incrementScore(0, -1);
                incrementScore(1, 2);
                removeOverlay();
            },
        };
        return new Question(question, answers, handlers);
    }

    /**
     * Initializes the scene
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


        scoreCountOverlay = new VBox(0, coinCountOverlay, pointCountOverlay);

        super.initScene();
        root.getChildren().add(scoreCountOverlay);
    }

    /**
     * Handles when the player touches a special block
     * @param specialType The special block's number
     */
    protected void handleSpecial(int specialType) {
        //TODO
        Question question = getQuestion(specialType);

        StackPane questionPane = new StackPane();
        questionPane.setAlignment(Pos.CENTER);
        Text questionText = new Text(question.getQuestion());
        questionText.setFont(new Font("Verdana", 20));
        questionText.setFill(Color.RED);
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
        Text finishText = new Text("Congratulations! You completed level three!");
        finishText.setFont(new Font("Verdana", 25));
        finishText.setFill(Color.RED);
        finishText.setTextAlignment(TextAlignment.CENTER);
        finishText.setWrappingWidth(Constants.SCREEN_WIDTH / 3 * 2);

        StackPane nextLevel = getMainButton("Finish Game", event -> this.game.updateState(State.MAIN_MENU), 15);

        setOverlay(initBasicOverlay(finishText, nextLevel));
    }
}
