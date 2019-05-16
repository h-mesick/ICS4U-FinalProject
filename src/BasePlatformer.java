import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
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
 *  - May 14, 2019: Updated ~Evan Zhang
 *  - May 15, 2019: Updated ~Evan Zhang
 */
public abstract class BasePlatformer extends BaseLevel {
    protected Sprite player;
    protected ProgressBar progress;
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
        player = new Sprite(30, Constants.SCREEN_HEIGHT - Constants.PLATFORM_BLOCK_HEIGHT - 30,
                            20, 30, ResourceLoader.loadImage("player.png"));
        root.getChildren().add(player);

        progress = new ProgressBar();
        progress.setTranslateX(Constants.SCREEN_WIDTH / 3);
        progress.setMinWidth(Constants.SCREEN_WIDTH / 3);
        progress.setTranslateY(20);
        progress.setMinHeight(20);
		progress.setStyle("-fx-accent: #00a000; -fx-background: #fff; -fx-control-inner-background: #fff;");
        root.getChildren().add(progress);

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
        if (Math.abs(mod) > 1e-7 &&
                0 <= referencePoint + mod && referencePoint + mod + screenY < this.level.screenLength()) {
            referencePoint += mod;
            for (Sprite obj : this.level.getAllSprites()) {
                obj.setTranslateY(obj.getTranslateY() - mod);
                obj.setVisible(0 <= obj.getTranslateY() + obj.getHeight() && obj.getTranslateY() < screenY);
            }
            player.setTranslateY(player.getTranslateY() - mod);
        }
    }

    private void updateSpecial() {
        double x = player.getTranslateX(), y = getRealY(player.getTranslateY());
        if (this.level.isSpecial(x, y)) {
            handleSpecial(-this.level.getPosition(x, y));
            root.getChildren().remove(this.level.getSprite(x, y));
            this.level.removeSpecial(x, y);
        }
    }

    protected void update() {
        updateScreen();
        updatePlayer();
        updateSpecial();
        progress.setProgress(1 - (referencePoint + Constants.SCREEN_HEIGHT) / this.level.screenLength());
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
