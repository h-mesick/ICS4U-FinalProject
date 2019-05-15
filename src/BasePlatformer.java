import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
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
 *  - May 14, 2019: Updated ~Evan Zhang
 *  - May 15, 2019: Updated ~Evan Zhang
 */
public abstract class BasePlatformer extends BaseLevel {
    protected Sprite player;
    protected Group root;
    protected boolean keyRight = false, keyLeft = false, keyUp = false;
    protected Level level;
    protected double referencePoint;

    public BasePlatformer(Game game) {
        super(game);
        level = new Level(getLevelFile());
        referencePoint = this.level.screenLength() - Constants.SCREEN_HEIGHT;
        root = new Group();
    }

    public void initScene() {
        for (Sprite s : level.getAllSprites()) {
            root.getChildren().add(s);
        }
        for (Node s : root.getChildren()) {
            s.setTranslateY(s.getTranslateY() - referencePoint);
        }
        player = new Sprite(100, 100, 20, 20, Color.BLUE);
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

    private void updatePlayer() {
        BoundingBox box = new BoundingBox(player.getTranslateX(), getRealY(player.getTranslateY()),
                                          player.getWidth(), player.getHeight());

        double ground = getScreenY(this.level.getLowerBound(box) - player.getHeight());
        double ceiling = getScreenY(this.level.getUpperBound(box));

        if (keyUp && player.onGround(ground)) {
            player.jump();
        }
        if (keyLeft) {
            player.moveLeft(this.level.getLeftBound(box));
        }
        if (keyRight) {
            player.moveRight(this.level.getRightBound(box) - player.getWidth());
        }
        player.fall(ground, ceiling);
    }

    private double calcSlide(double cur, double max) {
        double ret = 10 * (max - cur) / max;
        return ret < 0 ? 10 : ret;
    }

    private void updateScreen() {
        double mod = 0;
        double posY = player.getTranslateY();
        double screenY = Constants.SCREEN_HEIGHT;
        if (posY <= screenY / 3) {
            mod -= calcSlide(posY, screenY / 3);
        } else if (posY >= screenY * 2 / 3) {
            mod += calcSlide(screenY - posY, screenY / 3);
        }
        if (Math.abs(mod) > 1e-7 && 0 <= referencePoint + mod && referencePoint + mod + screenY < this.level.screenLength()) {
            referencePoint += mod;
            for (Node obj : root.getChildren()) {
                obj.setTranslateY(obj.getTranslateY() - mod);
            }
        }
    }

    private void updateSpecial() {
        int positionValue = this.level.getPosition(player.getTranslateX(), getRealY(player.getTranslateY()));
        if (positionValue < 0) {
            handleSpecial(-positionValue);
        }
    }

    protected void update() {
        updateScreen();
        updatePlayer();
        updateSpecial();
    }

    protected double getRealY(double y) {
        return y + referencePoint;
    }

    protected double getScreenY(double y) {
        return y - referencePoint;
    }

    protected abstract String getLevelFile();
    protected abstract void handleSpecial(int specialType);
}
