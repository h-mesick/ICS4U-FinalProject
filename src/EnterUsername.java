/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * The Enter Username scene.
 */

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

/**
 * The Enter Username scene.
 * <pre>
 * Revision history:
 *  - May 28, 2019: Created ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
 *  - May 31, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Updated ~Evan Zhang
 *  - Jun 2, 2019: Commented ~Evan Zhang
 *  - Jun 3, 2019: Updated ~Evan Zhang
 *  - Jun 6, 2019: Updated ~Evan Zhang
 *  - Jun 7, 2019: Finished ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public class EnterUsername extends BaseScene {
    /**
     * Constructor
     * @param game The current game that is running
     */
    public EnterUsername(Game game) {
        super(game);
    }

    /**
     * {@inheritDoc}
     */
    public void initScene() {
        VBox body = new VBox(10);
        body.setAlignment(Pos.TOP_CENTER);

        body.getChildren().add(new ImageView(ResourceLoader.loadImage("enter-username-logo.png")));

        HBox usernameRow = new HBox(10);
        usernameRow.setAlignment(Pos.CENTER);

        TextField usernameField = new TextField();
        usernameField.setFont(Util.getMainFont(13));
        usernameField.setMinWidth(Constants.SCREEN_WIDTH / 2);

        usernameField.setTextFormatter(new TextFormatter<>(change -> {
            if (!change.isContentChange()) {
                return change;
            }
            String text = change.getControlNewText();
            if (text.length() > 50) {
                return null;
            }
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (!(c < ' ' || (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                    return null;
                }
            }
            return change;
        }));
        usernameField.getStylesheets().add(ResourceLoader.loadCSS("username-text-field.css"));

        ImageButton enterButton = Util.getMainImageButton("forward", event -> {
            String username = usernameField.getText().toUpperCase();
            if (username.length() == 0) {
                return;
            }
            this.game.setCurrentUser(username);
            this.game.updateState(State.MAIN_MENU);
        });
        enterButton.setDisable(true);

        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            enterButton.setDisable(newValue.length() == 0);
        });

        usernameRow.getChildren().addAll(usernameField, enterButton);
        body.getChildren().add(usernameRow);

        Text text = new Text("Your username defines where your game state is saved, so please use the same username everytime!");
        text.setFont(Util.getMainFont(15));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(Constants.SCREEN_WIDTH - 400);
        body.getChildren().add(text);

        Scene scene = new Scene(Util.getMainRoot(body));
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                enterButton.fire();
            }
        });
        this.game.setScene(scene);
    }
}
