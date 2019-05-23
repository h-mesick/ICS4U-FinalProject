import java.util.ArrayList;

import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
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
 *  - May 16, 2019: Updated ~Evan Zhang
 *  - May 17, 2019: Updated ~Evan Zhang
 *  - May 18, 2019: Updated ~Evan Zhang
 *  - May 19, 2019: Updated ~Evan Zhang
 *  - May 21, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
*/
public abstract class BasePlatformer extends BaseLevel {
    protected Sprite player;
    protected ProgressBar progress;
    protected VBox escapeOverlay;
    protected Level level;
    protected double referencePoint;
    protected ArrayList<Node> removedNodes = new ArrayList();

    public BasePlatformer(Game game) {
        super(game);
        level = new Level(getLevelFile());
        referencePoint = this.level.screenLength() - Constants.SCREEN_HEIGHT;
        root = new Group();
    }

    public void initScene() {
        // add blocks
        for (Sprite s : level.getAllSprites()) {
            root.getChildren().add(s);
            s.setTranslateY(getScreenY(s.getTranslateY()));
            s.setVisible(0 <= s.getTranslateY() + s.getHeight() && s.getTranslateY() < Constants.SCREEN_HEIGHT);
        }

        // add player
        player = new Sprite(30, Constants.SCREEN_HEIGHT - Constants.PLATFORM_BLOCK_HEIGHT - 30,
                            20, 29, ResourceLoader.loadImage("player.png"));
        root.getChildren().add(player);

        // add progress bar
        progress = new ProgressBar();
        progress.setTranslateX(Constants.SCREEN_WIDTH / 3);
        progress.setMinWidth(Constants.SCREEN_WIDTH / 3);
        progress.setTranslateY(20);
        progress.setMinHeight(20);
        progress.getStylesheets().add(ResourceLoader.loadCSS("game-progress-bar.css"));
        root.getChildren().add(progress);

        escapeOverlay = initEscapeOverlay();

        setScene(root);
        start();
    }

    private void updatePlayer() {
        BoundingBox box = new BoundingBox(player.getTranslateX(), getRealY(player.getTranslateY()),
                                          player.getWidth(), player.getHeight());

        double ground = getScreenY(this.level.getLowerBound(box) - player.getHeight());
        double ceiling = getScreenY(this.level.getUpperBound(box));

        if (pressedKeys.contains(KeyCode.UP) && player.onGround(ground)) {
            player.jump();
        }
        if (pressedKeys.contains(KeyCode.LEFT)) {
            player.moveLeft(this.level.getLeftBound(box));
        }
        if (pressedKeys.contains(KeyCode.RIGHT)) {
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
        this.level.getSpecialSprites().stream()
            .filter(s -> s.getBoundsInParent().intersects(player.getBoundsInParent()))
            .filter(s -> root.getChildren().remove(s))
            .forEach(s -> {
                removedNodes.add(s);
                handleSpecial(-this.level.getPosition(s.getCenterX(), getRealY(s.getCenterY())));
            });
    }

    private void updateProgress() {
        /**
         * Calculating the progress bar.
         * The challenge with calculating the progress bar is, which position do you use?
         * * Using the top of the screen means the bar can never be empty (at the beginning of the level).
         * * Using the bottom of the screen means the bar can never full (at the end of the level).
         * * Using the middle of the screen means the bar can never be full *nor* empty.
         *
         * Thus, the solution is to calculate the position of the screen to use *based* on the actual position.
         * This is calculated with the "add" variable.
         *
         *  add = (referencePoint + add * Constants.SCREEN_HEIGHT) / this.level.screenLength();
         *
         *  Note that the answer depends on the answer. Fortunately, the answer converges, so we can just approximate
         *  by recursing 3 times.
         */
        int n = 3;
        double add = 0;
        for (int x = n - 1; x >= 0; x--) {
            add += referencePoint * Math.pow(this.level.screenLength(), x) * Math.pow(Constants.SCREEN_HEIGHT, n - x - 1);
        }
        add /= Math.pow(this.level.screenLength(), n);
        add *= Constants.SCREEN_HEIGHT;

        progress.setProgress(1 - (referencePoint + add) / this.level.screenLength());
    }

    protected void update() {
        if (currentOverlay != null) {
            return;
        }
        updateScreen();
        updatePlayer();
        updateSpecial();
        updateProgress();
    }

    protected void handleKeyPressed(KeyCode key) {
        switch(key) {
            case ESCAPE: {
                if (currentOverlay != null) {
                    if (currentOverlay.equals(escapeOverlay)) {
                        removeOverlay();
                    }
                } else {
                    setOverlay(escapeOverlay);
                }
                break;
            }
        }
    }

    protected void load(PlatformerGameSave save) {
        root.getChildren().remove(this.player);
        this.player = (Sprite)save.player;
        root.getChildren().add(this.player);

        double mod = save.referencePoint - this.referencePoint;
        this.referencePoint = save.referencePoint;
        for (Sprite obj : this.level.getAllSprites()) {
            obj.setTranslateY(obj.getTranslateY() - mod);
            obj.setVisible(0 <= obj.getTranslateY() + obj.getHeight() && obj.getTranslateY() < Constants.SCREEN_HEIGHT);
        }

        this.removedNodes = save.removedNodes;
        for (Node n : this.removedNodes) {
            double x = n.getTranslateX();
            double y = getRealY(n.getTranslateY());
            root.getChildren().remove(this.level.getSprite(x, y));
        }
    }

    protected double getRealY(double y) {
        return y + referencePoint;
    }

    protected double getScreenY(double y) {
        return y - referencePoint;
    }

    protected String getLevelFile() {
        return "level" + getLevel() + ".txt";
    }

    protected abstract void handleSpecial(int specialType);
}
