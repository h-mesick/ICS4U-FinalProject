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
    int xDelta = 0, yDelta = 0;

    public Sprite(int x, int y, int w, int h, Color c) {
        super(w, h, c);
        setTranslateX(x);
        setTranslateY(y);
    }

    private void move(int x, int y) {
        xDelta += x;
        yDelta += y;
    }

    public void updatePosition() {
        setTranslateX(getTranslateX() + xDelta);
        setTranslateY(getTranslateY() + yDelta);
        xDelta = 0;
        yDelta = 0;
    }

    public void moveLeft() {
        move(-5, 0);
    }

    public void moveRight() {
        move(5, 0);
    }

    public void moveUp() {
        move(0, -5);
    }

    public void moveDown() {
        move(0, 5);
    }
}
