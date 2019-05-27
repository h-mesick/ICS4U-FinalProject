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
 */
public class LevelTwo extends BasePlatformer {
    private HBox scoreCountOverlay;
    private Text coinText;
    private int coinCount = 0;
    public LevelTwo(Game game) {
        super(game);
    }

    protected int getLevel() {
        return 2;
    }

    private void incrementCoinCount(int delta) {
        coinCount += delta;
        coinText.setText("" + coinCount);
    }

    protected Question getQuestion(int specialType) {
        String question = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + specialType;
        String[] answers = {
            "a", "b", "c", "dasdasdsadasdasdsadadadsd"
        };
        EventHandler[] handlers = {
            event -> {
                incrementCoinCount(1);
                removeOverlay();
            },
            event -> {
                incrementCoinCount(3);
                removeOverlay();
            },
            event -> {
                incrementCoinCount(10);
                removeOverlay();
            },
            event -> {
                incrementCoinCount(-1);
                removeOverlay();
            },
        };
        return new Question(question, answers, handlers);
    }

    public void initScene() {
        scoreCountOverlay = new HBox(5);
        scoreCountOverlay.setAlignment(Pos.CENTER);
        scoreCountOverlay.setPadding(new Insets(10));

        ImageView coinImage = new ImageView(ResourceLoader.loadImage("coin.png"));
        coinImage.setPreserveRatio(true);
        coinImage.setFitWidth(40);

        coinText = new Text("0");
        coinText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        scoreCountOverlay.getChildren().addAll(coinImage, coinText);

        super.initScene();
        root.getChildren().add(scoreCountOverlay);
    }

    protected void handleSpecial(int specialType) {
        handleDefaultSpecial(specialType);
    }

    protected void handleFinish() {
        Text finishText = new Text("Congratulations! You completed level two!");
        finishText.setFont(new Font("Verdana", 25));
        finishText.setFill(Color.RED);
        finishText.setTextAlignment(TextAlignment.CENTER);
        finishText.setWrappingWidth(Constants.SCREEN_WIDTH / 3 * 2);

        StackPane nextLevel = getMainButton("Continue to the next level", event -> this.game.updateState(State.LEVEL_THREE), 15);

        setOverlay(initBasicOverlay(finishText, nextLevel));
    }

    protected PlatformerGameSave save() {
        return new PlatformerGameSave(referencePoint, player, removedNodes, coinCount);
    }

    protected void load(GameSave baseSave) {
        PlatformerGameSave save = (PlatformerGameSave)baseSave;
        super.load(save);
        incrementCoinCount(-coinCount);
        incrementCoinCount(save.scores[0]);
    }
}
