import java.io.BufferedReader;
import java.io.FileReader;

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

public class Level {
    private int[][] arr = new int[Constants.BLOCK_HEIGHT_COUNT][Constants.BLOCK_WIDTH_COUNT];
    
    public Level(String file) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            int specialCnt = 0;
            for (int y = 0; y < Constants.BLOCK_HEIGHT_COUNT; y++) {
                String line = in.readLine();
                for (int x = 0; x < Constants.BLOCK_WIDTH_COUNT; x++) {
                    if (line.charAt(x) == '.') {
                        arr[y][x] = 0;
                    } else if (line.charAt(x) == '#') {
                        arr[y][x] = 1;
                    } else {
                        arr[y][x] = --specialCnt;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public int getBlockX(double x) {
        return (int)x / Constants.PLATFORM_BLOCK_WIDTH;
    }

    public int getBlockY(double y) {
        return (int)y / Constants.PLATFORM_BLOCK_HEIGHT;
    }

    public int getPosition(double x, double y) {
        return arr[getBlockY(y)][getBlockX(x)];
    }

    public boolean isBlocked(double x, double y) {
        return getPosition(x, y) == 1;
    }

    public double getLeftBound(Sprite obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        double px = obj.getTranslateX(), py = obj.getTranslateY();
        for (double x = px; x >= 0; x -= jumpX) {
            for (double y = 0; y < obj.getHeight(); y += jumpY) {
                if (isBlocked(x, py + y)) {
                    return (getBlockX(x) + 1) * Constants.PLATFORM_BLOCK_WIDTH;
                }
            }
            if (isBlocked(x, py + obj.getHeight() - 0.5)) {
                return (getBlockX(x) + 1) * Constants.PLATFORM_BLOCK_WIDTH;
            }
        }
        return 0;
    }

    public double getRightBound(Sprite obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        double px = obj.getTranslateX() + obj.getWidth(), py = obj.getTranslateY();
        for (double x = px; x < Constants.SCREEN_WIDTH; x += jumpX) {
            for (double y = 0; y < obj.getHeight(); y += jumpY) {
                if (isBlocked(x, py + y)) {
                    return getBlockX(x) * Constants.PLATFORM_BLOCK_WIDTH;
                }
            }
            if (isBlocked(x, py + obj.getHeight() - 0.5)) {
                return getBlockX(x) * Constants.PLATFORM_BLOCK_WIDTH;
            }
        }
        return Constants.SCREEN_WIDTH - 1;
    }

    public double getUpperBound(Sprite obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        double px = obj.getTranslateX(), py = obj.getTranslateY();
        for (double y = py; y >= 0; y -= jumpY) {
            for (double x = 0; x < obj.getWidth(); x += jumpX) {
                if (isBlocked(px + x, y)) {
                    return (getBlockY(y) + 1) * Constants.PLATFORM_BLOCK_HEIGHT;
                }
            }
            if (isBlocked(px + obj.getWidth() - 0.5, y)) {
                return (getBlockY(y) + 1) * Constants.PLATFORM_BLOCK_HEIGHT;
            }
        }
        return 0;
    }

    public double getLowerBound(Sprite obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        double px = obj.getTranslateX(), py = obj.getTranslateY() + obj.getHeight();
        for (double y = py; y < Constants.SCREEN_HEIGHT; y += jumpY) {
            for (double x = 0; x < obj.getWidth(); x += jumpX) {
                if (isBlocked(px + x, y)) {
                    return getBlockY(y) * Constants.PLATFORM_BLOCK_HEIGHT;
                }
            }
            if (isBlocked(px + obj.getWidth() - 0.5, y)) {
                return getBlockY(y) * Constants.PLATFORM_BLOCK_HEIGHT;
            }
        }
        return Constants.SCREEN_HEIGHT - 1;
    }
}
