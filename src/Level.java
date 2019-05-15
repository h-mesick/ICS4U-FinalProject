import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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

public class Level {
    private int[][] arr;

    public Level(String file) {
        ArrayList<String> lines = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            for (String line; (line = in.readLine()) != null;) {
                lines.add(line);
            }
        } catch (Exception e) {
        }
        int specialCnt = 0;
        arr = new int[lines.size()][Constants.BLOCK_WIDTH_COUNT];
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
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
    }

    public int length() {
        return this.arr.length;
    }

    public int screenLength() {
        return this.arr.length * Constants.PLATFORM_BLOCK_HEIGHT;
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

    public double getLeftBound(BoundingBox obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        for (double x = obj.getMinX(); x >= 0; x -= jumpX) {
            for (double y = obj.getMinY(); y < obj.getMaxY(); y += jumpY) {
                if (isBlocked(x, y)) {
                    return (getBlockX(x) + 1) * Constants.PLATFORM_BLOCK_WIDTH;
                }
            }
            if (isBlocked(x, obj.getMaxY() - 0.5)) {
                return (getBlockX(x) + 1) * Constants.PLATFORM_BLOCK_WIDTH;
            }
        }
        return 0;
    }

    public double getRightBound(BoundingBox obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        for (double x = obj.getMaxX(); x < Constants.SCREEN_WIDTH; x += jumpX) {
            for (double y = obj.getMinY(); y < obj.getMaxY(); y += jumpY) {
                if (isBlocked(x, y)) {
                    return getBlockX(x) * Constants.PLATFORM_BLOCK_WIDTH;
                }
            }
            if (isBlocked(x, obj.getMaxY() - 0.5)) {
                return getBlockX(x) * Constants.PLATFORM_BLOCK_WIDTH;
            }
        }
        return Constants.SCREEN_WIDTH - 1;
    }

    public double getUpperBound(BoundingBox obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        for (double y = obj.getMinY(); y >= 0; y -= jumpY) {
            for (double x = obj.getMinX(); x < obj.getMaxX(); x += jumpX) {
                if (isBlocked(x, y)) {
                    return (getBlockY(y) + 1) * Constants.PLATFORM_BLOCK_HEIGHT;
                }
            }
            if (isBlocked(obj.getMaxX() - 0.5, y)) {
                return (getBlockY(y) + 1) * Constants.PLATFORM_BLOCK_HEIGHT;
            }
        }
        return 0;
    }

    public double getLowerBound(BoundingBox obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        for (double y = obj.getMaxY(); y < screenLength(); y += jumpY) {
            for (double x = obj.getMinX(); x < obj.getMaxX(); x += jumpX) {
                if (isBlocked(x, y)) {
                    return getBlockY(y) * Constants.PLATFORM_BLOCK_HEIGHT;
                }
            }
            if (isBlocked(obj.getMaxX() - 0.5, y)) {
                return getBlockY(y) * Constants.PLATFORM_BLOCK_HEIGHT;
            }
        }
        return screenLength() - 1;
    }
}
