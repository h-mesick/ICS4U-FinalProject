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
}
