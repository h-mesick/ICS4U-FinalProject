import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 * @author Evan Zhang
 * Revision history:
 * - May 28, 2019: Created ~Evan Zhang
 * @version 1
 */
public class EnterUsername extends BaseScene {
    /**
     * Constructor
     *
     * @param game The current game that is running
     */
    public EnterUsername(Game game) {
        super(game);
    }

    /**
     * Initializes the scene
     */
    public void initScene() {
        BorderPane root = new BorderPane();

        root.setBackground(new Background(new BackgroundImage(
                ResourceLoader.loadImage("background.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false, false, false, false)
        )));

        root.setTop(getTitle());

        VBox body = new VBox(10);
        body.setAlignment(Pos.TOP_CENTER);

        body.getChildren().add(new ImageView(ResourceLoader.loadImage("enter-username-logo.png")));

        HBox usernameRow = new HBox(10);
        usernameRow.setAlignment(Pos.CENTER);

        TextField usernameField = new TextField();
        usernameField.setMinWidth(Constants.SCREEN_WIDTH / 2);

        usernameField.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.isContentChange()) {
                return change;
            }
            String text = change.getControlNewText();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (!(c < ' ' || (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                    return null;
                }
            }
            if (text.indexOf(" ") != -1) {
                return null;
            }
            return change;
        }));

        ImageButton enterButton = new ImageButton();
        enterButton.setFitWidth(50);
        enterButton.setFitHeight(50);
        enterButton.setDisable(true);
        enterButton.setImages(ResourceLoader.loadImage("help-button.png"),
                              ResourceLoader.loadImage("help-button-hover.png"),
                              ResourceLoader.loadImage("help-button-selected.png"));
        EventHandler usernameEntered = (event -> {
            String username = usernameField.getText();
            if (username.length() == 0) {
                return;
            }
            this.game.setCurrentUser(username);
            this.game.updateState(State.MAIN_MENU);
        });
        enterButton.setOnAction(usernameEntered);

        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            enterButton.setDisable(newValue.length() == 0);
        });

        usernameRow.getChildren().addAll(usernameField, enterButton);
        body.getChildren().add(usernameRow);

        root.setCenter(body);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(usernameEntered);
        this.game.setScene(scene);
    }
}
