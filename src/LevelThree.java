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
 */
public class LevelThree extends BasePlatformer {
    private VBox scoreCountOverlay;
    private Text coinText, pointText;
    private int coinCount = 0, pointCount = 0;

    public LevelThree(Game game) {
        super(game);
    }

    protected int getLevel() {
        return 3;
    }

    private void incrementCoinCount(int delta) {
        coinCount += delta;
        coinText.setText("" + coinCount);
    }

    private void incrementPointCount(int delta) {
        pointCount += delta;
        pointText.setText("" + pointCount);
    }

    protected Question getQuestion(int specialType) {
        String question = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + specialType;
        String[] answers = {
            "a", "b", "c", "dasdasdsadasdasdsadadadsd"
        };
        EventHandler[] handlers = {
            event -> {
                incrementPointCount(2);
                incrementCoinCount(-1);
                removeOverlay();
            },
            event -> {
                incrementPointCount(6);
                incrementCoinCount(-3);
                removeOverlay();
            },
            event -> {
                incrementPointCount(20);
                incrementCoinCount(-10);
                removeOverlay();
            },
            event -> {
                incrementPointCount(2);
                incrementCoinCount(-1);
                removeOverlay();
            },
        };
        return new Question(question, answers, handlers);
    }

    public void initScene() {
        HBox coinCountOverlay = new HBox(5);
        coinCountOverlay.setAlignment(Pos.CENTER);
        coinCountOverlay.setPadding(new Insets(10, 0, 0, 10));

        ImageView coinImage = new ImageView(ResourceLoader.loadImage("coin.png"));
        coinImage.setPreserveRatio(true);
        coinImage.setFitWidth(40);

        coinText = new Text("0");
        coinText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        coinCountOverlay.getChildren().addAll(coinImage, coinText);


        HBox pointCountOverlay = new HBox(5);
        pointCountOverlay.setAlignment(Pos.CENTER);
        pointCountOverlay.setPadding(new Insets(0, 0, 0, 10));

        ImageView pointImage = new ImageView(ResourceLoader.loadImage("star.png"));
        pointImage.setPreserveRatio(true);
        pointImage.setFitWidth(40);

        pointText = new Text("0");
        pointText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        pointCountOverlay.getChildren().addAll(pointImage, pointText);


        scoreCountOverlay = new VBox(0, coinCountOverlay, pointCountOverlay);

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
        return new PlatformerGameSave(referencePoint, player, removedNodes, coinCount, pointCount);
    }

    public void load(GameSave baseSave) {
        if (baseSave == null) {
            return;
        }
        PlatformerGameSave save = (PlatformerGameSave)baseSave;
        super.load(save);
        incrementCoinCount(-coinCount);
        incrementCoinCount(save.scores[0]);
        incrementPointCount(-pointCount);
        incrementPointCount(save.scores[1]);
    }
}
