import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 */
public class LevelOne extends BaseLevel {
    public LevelOne(Stage stage) {
        super(stage);
    }

    public void drawScreen() {
        Group root = new Group();
        Canvas c = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(c);
        this.stage.setScene(new Scene(root));

        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
        gc.setFont(theFont);
        gc.fillText("Hello, World!", 60, 50);
        gc.strokeText("Hello, World!", 60, 50);
    }
}
