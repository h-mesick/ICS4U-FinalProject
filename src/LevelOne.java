import javafx.animation.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
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
public class LevelOne extends BaseLevel {
    public LevelOne(Game game) {
        super(game);
    }

    public void initScene() {
        root = new Group();
        Canvas c = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(c);
        this.game.setScene(new Scene(root));

        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
        gc.setFont(theFont);
        gc.fillText("Hello, World!", 60, 50);
        gc.strokeText("Hello, World!", 60, 50);
    }

    protected void update() {
        
    }
}
