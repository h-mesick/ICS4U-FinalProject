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
 *  - May 16, 2019: Updated ~Evan Zhang
 *  - May 17, 2019: Updated ~Evan Zhang
 */
public abstract class BaseScene implements Constants {
    protected Game game;

    public BaseScene() {}

    public BaseScene(Game game) {
        this.game = game;
    }

    public abstract void initScene();

    public VBox getTitle() {
        VBox title = new VBox();
        title.setAlignment(Pos.CENTER);
        title.getChildren().add(new ImageView(ResourceLoader.loadImage("game-logo.png")));
        return title;
    }

    public BorderPane getFooter(Node left, Node right) {
        BorderPane footer = new BorderPane();
        footer.setPadding(new Insets(10));

        if (left != null) {
            footer.setLeft(left);
        }
        if (right != null) {
            footer.setRight(right);
        }
        return footer;
    }

    public StackPane getMainButton(String text, EventHandler onClick, int fontSize) {
        ImageButton mainButton = new ImageButton();
        mainButton.setImages(ResourceLoader.loadImage("button.png"),
                             ResourceLoader.loadImage("button-hover.png"),
                             ResourceLoader.loadImage("button-selected.png"));
        mainButton.setFitWidth(SCREEN_WIDTH / 5 * 2);
        mainButton.setFitHeight(SCREEN_HEIGHT / 8);
        mainButton.setOnAction(onClick);

        Text t = new Text(text);
        t.setTranslateY(5);
        t.setFont(Font.font("Verdana", fontSize));
        t.setFill(Color.RED);
        t.setMouseTransparent(true);

        StackPane button = new StackPane();
        button.setAlignment(Pos.CENTER);
        button.getChildren().add(mainButton);
        button.getChildren().add(t);

        return button;
    }

    public StackPane getMainButton(String text, EventHandler onClick) {
        return getMainButton(text, onClick, 30);
    }

    public void onExit() {}
    public void onEnter() {}
}
