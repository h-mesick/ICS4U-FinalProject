import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

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
 *  - May 14, 2019: Created ~Evan Zhang
 *  - May 15, 2019: Updated ~Evan Zhang
 *  - May 17, 2019: Updated ~Evan Zhang
 */
public class Level {
    private int[][] arr;
    private Sprite[][] blocks;

    public Level(String file) {
        ArrayList<String> lines = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(ResourceLoader.loadLevel(file));
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

        blocks = new Sprite[lines.size()][Constants.BLOCK_WIDTH_COUNT];
        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < Constants.BLOCK_WIDTH_COUNT; x++) {
                double xx = getActX(x), yy = getActY(y);
                blocks[y][x] = getBlock(xx, yy);
            }
        }
    }

    private Sprite getBlock(double x, double y) {
        boolean topBlocked = y - Constants.PLATFORM_BLOCK_HEIGHT >= 0 &&
                             isBlocked(x, y - Constants.PLATFORM_BLOCK_HEIGHT);
        String image = null;
        if (isBlocked(x, y)) {
            image = topBlocked ? "platform.png" : "platform-top.png";
        } else if (isSpecial(x, y)) {
            image = "special.png";
        } else {
            if (topBlocked) {
                int num = (int)(Math.random() * 3) + 1;
                image = "platform-overhang-" + num + ".png";
            }
        }
        if (image != null) {
            return new Sprite(x, y, Constants.PLATFORM_BLOCK_WIDTH,
                              Constants.PLATFORM_BLOCK_HEIGHT, ResourceLoader.loadImage(image));
        }
        return null;
    }

    public int length() {
        return this.arr.length;
    }

    public int screenLength() {
        return this.arr.length * Constants.PLATFORM_BLOCK_HEIGHT;
    }

    public int getActX(int x) {
        return x * Constants.PLATFORM_BLOCK_WIDTH;
    }

    public int getActY(int y) {
        return y * Constants.PLATFORM_BLOCK_HEIGHT;
    }

    public int getBlockX(double x) {
        return (int)x / Constants.PLATFORM_BLOCK_WIDTH;
    }

    public int getBlockY(double y) {
        return (int)y / Constants.PLATFORM_BLOCK_HEIGHT;
    }

    //TODO: optimize
    public List<Sprite> getSpecialSprites() {
        List<Sprite> ret = new ArrayList();
        for (int y = 0; y < length(); y++) {
            for (int x = 0; x < Constants.BLOCK_WIDTH_COUNT; x++) {
                if (arr[y][x] < 0) {
                    ret.add(blocks[y][x]);
                }
            }
        }
        return ret;
    }

    //TODO: optimize
    public List<Sprite> getAllSprites() {
        List<Sprite> ret = new ArrayList();
        for (int y = 0; y < length(); y++) {
            for (int x = 0; x < Constants.BLOCK_WIDTH_COUNT; x++) {
                if (blocks[y][x] != null) {
                    ret.add(blocks[y][x]);
                }
            }
        }
        return ret;
    }

    public Sprite getSprite(double x, double y) {
        return blocks[getBlockY(y)][getBlockX(x)];
    }

    public int getPosition(double x, double y) {
        return arr[getBlockY(y)][getBlockX(x)];
    }

    public boolean isBlocked(double x, double y) {
        return getPosition(x, y) == 1;
    }

    public boolean isSpecial(double x, double y) {
        return getPosition(x, y) < 0;
    }

    public double getLeftBound(BoundingBox obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        for (double x = obj.getMinX(); x >= 0; x -= jumpX) {
            for (double y = obj.getMinY(); y < obj.getMaxY(); y += jumpY) {
                if (isBlocked(x, y)) {
                    return getActX(getBlockX(x) + 1);
                }
            }
            if (isBlocked(x, obj.getMaxY() - 0.5)) {
                return getActX(getBlockX(x) + 1);
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
                    return getActX(getBlockX(x));
                }
            }
            if (isBlocked(x, obj.getMaxY() - 0.5)) {
                return getActX(getBlockX(x));
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
                    return getActY(getBlockY(y) + 1);
                }
            }
            if (isBlocked(obj.getMaxX() - 0.5, y)) {
                return getActY(getBlockY(y) + 1);
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
                    return getActY(getBlockY(y));
                }
            }
            if (isBlocked(obj.getMaxX() - 0.5, y)) {
                return getActY(getBlockY(y));
            }
        }
        return screenLength() - 1;
    }
}
