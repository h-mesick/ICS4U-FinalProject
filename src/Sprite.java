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
    final double gravity = 0.1;
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

    private boolean equal(double x, double y) {
        return Math.abs(x - y) < 1e-5;
    }

    public void jump() {
        yVel -= 4;
    }

    public boolean onGround(double ground) {
        return equal(ground, getTranslateY());
    }

    public void fall(double ground) {
        yVel += gravity;
        move(0, yVel);
        setTranslateY(Math.min(ground, getTranslateY()));
        if (onGround(ground))
            yVel = 0;
    }

    public void moveLeft() {
        move(-1, 0);
    }

    public void moveRight() {
        move(1, 0);
    }
}
