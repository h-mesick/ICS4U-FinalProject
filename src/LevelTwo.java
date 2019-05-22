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
        StackPane overlayBase = new StackPane();
        overlayBase.setAlignment(Pos.CENTER);
        overlayBase.setMinWidth(Constants.SCREEN_WIDTH);

        VBox overlay = new VBox(10);
        overlay.setPadding(new Insets(50, 25, 50, 25));
        overlay.setAlignment(Pos.CENTER);

        overlay.setBackground(new Background(new BackgroundFill(
            new Color(1, 215.0/255, 64.0/255, 0.75),
            new CornerRadii(10),
            new Insets(0)
        )));

        Question question = getQuestion(specialType);

        StackPane questionPane = new StackPane();
        questionPane.setAlignment(Pos.CENTER);
        Text questionText = new Text(question.getQuestion());
        questionText.setFont(new Font("Verdana", 20));
        questionText.setFill(Color.RED);
        questionText.setWrappingWidth(Constants.SCREEN_WIDTH / 3 * 2);
        questionPane.getChildren().add(questionText);

        GridPane answersPane = new GridPane();
        answersPane.setAlignment(Pos.CENTER);
        answersPane.setHgap(10);
        answersPane.setVgap(10);

        for (int x = 0; x < 4; x++) {
            StackPane button = getMainButton(question.getAnswers()[x], question.getHandlers()[x], 15);
            answersPane.add(button, x / 2, x % 2);
        }
        overlay.getChildren().addAll(questionPane, answersPane);

        overlayBase.getChildren().add(overlay);
        overlayBase.setMargin(overlay, new Insets(100, 50, 100, 50));

        setOverlay(overlayBase);
    }

    public PlatformerGameSave save() {
        return new PlatformerGameSave(referencePoint, player, removedNodes, coinCount);
    }

    public void load(GameSave baseSave) {
        if (baseSave == null) {
            return;
        }
        PlatformerGameSave save = (PlatformerGameSave)baseSave;
        super.load(save);
        incrementCoinCount(-coinCount);
        incrementCoinCount(save.scores[0]);
    }
}
