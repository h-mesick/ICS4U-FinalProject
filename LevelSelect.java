import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created
 */
public class LevelSelect extends BaseScreen {
    public LevelSelect(Stage stage) {
        super(stage);
    }
    
    protected void drawScreen() {
        HBox root = new HBox();

        Button[] btns = new Button[Constants.NUM_LEVELS];
        for (int x = 0; x < Constants.NUM_LEVELS; x++) {
            btns[x] = new Button();
            btns[x].setText(Constants.LEVEL_NAMES[x]);
            btns[x].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ev) {
                    System.out.println("Test");
                }
            });
        }
        root.getChildren().addAll(btns);
        this.stage.setScene(new Scene(root));
        this.stage.show();
    }

    public int selectLevel() {
        drawScreen();
        return 0;
    }
}
