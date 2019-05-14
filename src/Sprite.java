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

public class Sprite extends Rectangle {
    final double gravity = 0.2;
    double xDelta = 0, yDelta = 0, yVel = 0, ground = 0;

    public Sprite(double x, double y, double w, double h, Color c) {
        super(w, h, c);
        setTranslateX(x);
        setTranslateY(y);
    }

    private boolean equal(double x, double y) {
        return Math.abs(x - y) < 1e-5;
    }

    private boolean onGround() {
        return equal(getTranslateY(), ground);
    }

    public void jump() {
        if (onGround())
            yVel -= 10;
    }

    public void updateGround(double ground) {
        this.ground = ground;
    }

    public void updatePosition() {
        yVel += gravity;
        yDelta += yVel;

        setTranslateX(getTranslateX() + xDelta);
        setTranslateY(Math.min(getTranslateY() + yDelta, ground));

        if (onGround()) {
            yVel = 0;
        }

        xDelta = 0;
        yDelta = 0;

        System.out.println(getTranslateX() + " " + getTranslateY());
    }

    public void moveLeft() {
        xDelta -= 1;
    }

    public void moveRight() {
        xDelta += 1;
    }
}
