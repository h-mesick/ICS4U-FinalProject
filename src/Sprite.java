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
 *  - May 14, 2019: Created ~Evan Zhang
 */
public class Sprite extends Rectangle {
    final double gravity = 0.2;
    double yVel = 0;

    public Sprite(double x, double y, double w, double h, Color c) {
        super(w, h, c);
        setTranslateX(x);
        setTranslateY(y);
    }

    private void move(double x, double y) {
        setTranslateX(getTranslateX() + x);
        setTranslateY(getTranslateY() + y);
    }

    public void jump() {
        yVel -= 8;
    }

    public boolean onGround(double ground) {
        return ground <= getTranslateY();
    }

    public boolean onCeiling(double ceiling) {
        return ceiling >= getTranslateY();
    }

    public void fall(double ground, double ceiling) {
        yVel += gravity;
        move(0, yVel);
        setTranslateY(Math.min(ground, getTranslateY()));
        setTranslateY(Math.max(ceiling, getTranslateY()));
        if (onGround(ground) || onCeiling(ceiling))
            yVel = 0;
    }

    public void moveLeft(double boundary) {
        move(-1.5, 0);
        setTranslateX(Math.max(boundary, getTranslateX()));
    }

    public void moveRight(double boundary) {
        move(1.5, 0);
        setTranslateX(Math.min(boundary, getTranslateX()));
    }
}
