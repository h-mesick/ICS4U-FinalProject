/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * The Sprite class for drawing movable and stationary images on the screen.
 */
import javafx.geometry.*;
import javafx.scene.image.*;

/**
 * The Sprite class for drawing movable and stationary images on the screen.
 * <pre>
 * Revision history:
 *  - May 14, 2019: Created ~Evan Zhang
 *  - May 15, 2019: Updated ~Evan Zhang
 *  - May 17, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - Jun 2, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Commented ~Evan Zhang
 *  - Jun 4, 2019: Updated ~Evan Zhang
 *  - Jun 6, 2019: Commented ~Evan Zhang
 *  - Jun 7, 2019: Finished ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public class Sprite extends ImageView {
    /** The gravity of the sprite */
    public static final double gravity = 0.2;

    /** The current y velocity of the sprite */
    private double yVel = 0;

    /**
     * Constructor
     * @param  x Initial x coordinate
     * @param  y Initial y coordinate
     * @param  w Sprite width
     * @param  h Sprite height
     * @param  c Sprite image
     */
    public Sprite(double x, double y, double w, double h, Image c) {
        super(c);
        setPosition(x, y);
        setFitWidth(w);
        setFitHeight(h);
    }

    /**
     * Move the sprite a relative distance
     * @param x Delta x
     * @param y Delta y
     */
    private void move(double x, double y) {
        setPosition(getTranslateX() + x, getTranslateY() + y);
    }

    /**
     * Get width of the sprite
     * @return width of the sprite
     */
    public double getWidth() {
        return getFitWidth();
    }

    /**
     * Get height of the sprite
     * @return height of the sprite
     */
    public double getHeight() {
        return getFitHeight();
    }

    /**
     * Get center x of the sprite
     * @return center x of the sprite
     */
    public double getCenterX() {
        return getTranslateX() + getWidth() / 2;
    }

    /**
     * Get center y of the sprite
     * @return center y of the sprite
     */
    public double getCenterY() {
        return getTranslateY() + getHeight() / 2;
    }

    /**
     * Make the sprite jump
     */
    public void jump() {
        yVel -= 6.6;
    }

    /**
     * Checks if the sprite is on the ground
     * @param  ground The "ground"
     * @return        Whether the sprite is on the ground or not
     */
    public boolean onGround(double ground) {
        return ground - Constants.EPS <= getTranslateY();
    }

    /**
     * Checks if the sprite is touching the ceiling
     * @param  ceiling The "ceiling"
     * @return         Whther the sprite is touching the ceiling or not
     */
    public boolean onCeiling(double ceiling) {
        return ceiling >= getTranslateY();
    }

    /**
     * Make the player fall
     * @param ground  The "ground"
     * @param ceiling The "ceiling"
     */
    public void fall(double ground, double ceiling) {
        yVel += gravity;
        move(0, yVel);
        setTranslateY(Math.min(ground, getTranslateY()));
        setTranslateY(Math.max(ceiling, getTranslateY()));
        if (onGround(ground) || onCeiling(ceiling)) {
            yVel = 0;
        }
    }

    /**
     * Move the sprite left up to the boundary
     * @param boundary The left boundary
     */
    public void moveLeft(double boundary) {
        move(-1.5, 0);
        setTranslateX(Math.max(boundary, getTranslateX()));
    }

    /**
     * Move the sprite right up to the boundary
     * @param boundary The right boundary
     */
    public void moveRight(double boundary) {
        move(1.5, 0);
        setTranslateX(Math.min(boundary, getTranslateX()));
    }

    /**
     * Set the center position of the sprite
     * @param point The center point position
     */
    public void setCenterPosition(Point2D point) {
        setPosition(point.getX() - getWidth() / 2, point.getY() - getHeight() / 2);
    }

    /**
     * Get the center position of the sprite
     * @return The center position of the sprite
     */
    public Point2D getCenterPosition() {
        return new Point2D(getCenterX(), getCenterY());
    }

    /**
     * Set the position of the sprite
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public void setPosition(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
    }
}
