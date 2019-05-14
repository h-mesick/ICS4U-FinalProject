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
    Level level = new Level("../resources/level2.txt");

    public LevelTwo(Game game) {
        super(game);
    }

    public void drawLevel(Group root) {
        for (int y = 0; y < Constants.SCREEN_HEIGHT; y += Constants.PLATFORM_BLOCK_HEIGHT) {
            for (int x = 0; x < Constants.SCREEN_WIDTH; x += Constants.PLATFORM_BLOCK_WIDTH) {
                if (this.level.isBlocked(x, y)) {
                    root.getChildren().add (
                        new Sprite(x, y, Constants.PLATFORM_BLOCK_WIDTH, Constants.PLATFORM_BLOCK_HEIGHT, Color.RED)
                    );
                }
            }
        }
    }

    public void initScene() {
        player = new Sprite(100, 100, 20, 20, Color.BLUE);
        Group root = new Group();
        root.getChildren().add(player);
        drawLevel(root);

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

    protected void update() {
        double ground = this.level.getLowerBound(player) - player.getHeight();
        double ceiling = this.level.getUpperBound(player);

        if (keyUp && player.onGround(ground)) {
            player.jump();
        }
        if (keyLeft) {
            player.moveLeft(this.level.getLeftBound(player));
        }
        if (keyRight) {
            player.moveRight(this.level.getRightBound(player) - player.getWidth());
        }
        player.fall(ground, ceiling);
    }
}
