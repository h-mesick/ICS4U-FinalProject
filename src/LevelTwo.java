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

    boolean keyRight = false, keyLeft = false, keyUp = false;
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
                case UP: keyUp = true; break;
                case RIGHT: keyRight = true; break;
                case LEFT: keyLeft = true; break;
                case ESCAPE: this.game.updateState(State.MAIN_MENU); break;
            }
        });
        scene.setOnKeyReleased(e -> {
            switch(e.getCode()) {
                case UP: keyUp = false; break;
                case RIGHT: keyRight = false; break;
                case LEFT: keyLeft = false; break;
            }
        });
        start();
        this.game.setScene(scene);
    }

    private double getGround(double x) {
        // TODO: update
        return Constants.SCREEN_HEIGHT - 50;
    }

    protected void update() {
        double ground = getGround(player.getTranslateX());
        if (keyUp && player.onGround(ground))
            player.jump();
        if (keyLeft)
            player.moveLeft();
        if (keyRight)
            player.moveRight();
        player.fall(ground);
    }
}
