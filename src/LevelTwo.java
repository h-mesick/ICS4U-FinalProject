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
public class LevelTwo extends BaseLevel {
    Sprite player;
    public LevelTwo(Game game) {
        super(game);
    }

    public void initScene() {
        player = new Sprite(100, 100, 20, 20, Color.BLUE);
        Group root = new Group();
        root.getChildren().add(player);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> {
            switch(e.getCode()) {
                case UP: player.moveUp(); break;
                case DOWN: player.moveDown(); break;
                case RIGHT: player.moveRight(); break;
                case LEFT: player.moveLeft(); break;
                case ESCAPE: this.game.updateState(State.MAIN_MENU); break;
            }
        });
        start();
        this.game.setScene(scene);
    }

    protected void update() {
        player.updatePosition();
    }
}
