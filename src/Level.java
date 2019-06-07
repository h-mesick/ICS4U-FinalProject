/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * The Level utility class for platformer level management.
 */
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.*;

/**
 * The Level utility class for platformer level management.
 * <pre>
 * Revision history:
 *  - May 14, 2019: Created ~Evan Zhang
 *  - May 15, 2019: Updated ~Evan Zhang
 *  - May 17, 2019: Updated ~Evan Zhang
 *  - May 19, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
 *  - May 31, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 *  - Jun 2, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Commented ~Evan Zhang
 *  - Jun 6, 2019: Commented ~Evan Zhang
 *  - Jun 7, 2019: Finished ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public class Level {
    /** The offset to compare two Sprites */
    private static final double OFFSET = 0.1;
    /** The default image for special nodes */
    private static final String DEFAULT_SPECIAL_IMAGE = "coin.png";

    /** The array of blocks stored as integers */
    private int[][] arr;
    /** The array of blocks stored as sprites */
    private Sprite[][] blocks;

    /** The list of all the sprites */
    private List<Sprite> allSprites = new ArrayList<Sprite>();
    /** The list of all the special sprites */
    private List<Sprite> specialSprites = new ArrayList<Sprite>();

    /**
     * Constructor
     * @param  file The filename to load from
     */
    public Level(String file) {
        List<String> lines = Util.readLines(ResourceLoader.loadLevel(file));
        int specialCnt = 0;
        arr = new int[lines.size()][Constants.BLOCK_WIDTH_COUNT];
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < Constants.BLOCK_WIDTH_COUNT; x++) {
                if (line.charAt(x) == '.') {
                    arr[y][x] = 0;
                } else if (line.charAt(x) == '#') {
                    arr[y][x] = 1;
                } else if (line.charAt(x) == 'f') {
                    arr[y][x] = 2;
                } else {
                    arr[y][x] = --specialCnt;
                }
            }
        }

        blocks = new Sprite[lines.size()][Constants.BLOCK_WIDTH_COUNT];
        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < Constants.BLOCK_WIDTH_COUNT; x++) {
                double xx = getActX(x);
                double yy = getActY(y);
                blocks[y][x] = getBlock(xx, yy);

                if (arr[y][x] < 0) {
                    specialSprites.add(blocks[y][x]);
                }
                /** Add auxiliary blocks first so they are shown underneath */
                allSprites.addAll(getAuxiliaryBlocks(xx, yy));
                if (blocks[y][x] != null) {
                    allSprites.add(blocks[y][x]);
                }
            }
        }
    }

    /**
     * Gets the block specified by the x and y coordinate
     * @param  x The x coordinate
     * @param  y The y coordinate
     * @return   The specified block
     */
    private Sprite getBlock(double x, double y) {
        String image = null;
        if (isEndPlatform(x, y)) {
            image = "platform-end.png";
        } else if (isNormalPlatform(x, y)) {
            image = belowNormalPlatform(x, y) ? "platform.png" : "platform-top.png";
        } else if (isSpecial(x, y)) {
            image = DEFAULT_SPECIAL_IMAGE;
        }
        if (image != null) {
            return new Sprite(x, y, Constants.PLATFORM_BLOCK_WIDTH,
                              Constants.PLATFORM_BLOCK_HEIGHT, ResourceLoader.loadImage(image));
        }
        return null;
    }

    /**
     * Gets the auxiliary blocks specified by the x and y coordinate
     * @param  x The x coordinate
     * @param  y The y coordinate
     * @return   An arraylist of auxiliary blocks
     */
    private List<Sprite> getAuxiliaryBlocks(double x, double y) {
        List<String> imageNames = new ArrayList<String>();
        if (!isBlocked(x, y)) {
            if (belowNormalPlatform(x, y)) {
                int num = (int)(Math.random() * 3) + 1;
                imageNames.add("platform-overhang-" + num + ".png");
            }
            if (aboveEndPlatform(x, y)) {
                imageNames.add("platform-door.png");
            }
        }
        List<Sprite> sprites = new ArrayList<Sprite>();
        for (String image : imageNames) {
            sprites.add(new Sprite(x, y, Constants.PLATFORM_BLOCK_WIDTH,
                              Constants.PLATFORM_BLOCK_HEIGHT, ResourceLoader.loadImage(image)));
        }
        return sprites;
    }

    /**
     * Sets the image of all special blocks
     * @param imageFilename The filename of the new special image
     */
    public void setSpecialImage(String imageFilename) {
        for (Sprite s : specialSprites) {
            s.setImage(ResourceLoader.loadImage(imageFilename));
        }
    }

    /**
     * Gets the length of the level in blocks
     * @return The length
     */
    public int length() {
        return this.arr.length;
    }

    /**
     * Gets the length of the level in pixels
     * @return The length
     */
    public int screenLength() {
        return this.arr.length * Constants.PLATFORM_BLOCK_HEIGHT;
    }

    /**
     * Converts from block x to pixel x
     * @param  x The block x
     * @return   The pixel x
     */
    public int getActX(int x) {
        return x * Constants.PLATFORM_BLOCK_WIDTH;
    }

    /**
     * Converts from block y to pixel y
     * @param  y The block y
     * @return   The block y
     */
    public int getActY(int y) {
        return y * Constants.PLATFORM_BLOCK_HEIGHT;
    }

    /**
     * Converts from pixel x to block x
     * @param  x The pixel x
     * @return   The block x
     */
    public int getBlockX(double x) {
        return (int)x / Constants.PLATFORM_BLOCK_WIDTH;
    }

    /**
     * Converts from pixel y to block y
     * @param  y The pixel y
     * @return   The block y
     */
    public int getBlockY(double y) {
        return (int)y / Constants.PLATFORM_BLOCK_HEIGHT;
    }

    /**
     * Gets all the special blocks
     * @return The list of special blocks
     */
    public List<Sprite> getSpecialSprites() {
        return specialSprites;
    }

    /**
     * Gets all the blocks
     * @return The list of all blocks
     */
    public List<Sprite> getAllSprites() {
        return allSprites;
    }

    /**
     * Gets the block specified by the x and y coordinate
     * @param  x The x coordinate
     * @param  y The y coordinate
     * @return   The block specified by the coordinates
     */
    public Sprite getSprite(double x, double y) {
        return blocks[getBlockY(y)][getBlockX(x)];
    }

    /**
     * Gets the value specified by the x and y coordinates
     * @param  x The x coordinate
     * @param  y The y coordinate
     * @return   The value speciifed by the coordinates
     */
    public int getPosition(double x, double y) {
        return arr[getBlockY(y)][getBlockX(x)];
    }

    /**
     * Returns whether the specified x and y is directly above an end platform
     * @param  x The x coordinate
     * @param  y The y coordinate
     * @return   Whether the speciifed coordinate is directly above an end platform
     */
    public boolean aboveEndPlatform(double x, double y) {
        return y + Constants.PLATFORM_BLOCK_HEIGHT < screenLength() &&
               isEndPlatform(x, y + Constants.PLATFORM_BLOCK_HEIGHT);
    }

    /**
     * Returns whether the specified x and y is directly below a normal platform
     * @param  x The x coordinate
     * @param  y The y coordinate
     * @return   Whether the speciifed coordinate is directly below a normal platform
     */
    public boolean belowNormalPlatform(double x, double y) {
        return y - Constants.PLATFORM_BLOCK_HEIGHT >= 0 &&
               isNormalPlatform(x, y - Constants.PLATFORM_BLOCK_HEIGHT);
    }

    /**
     * Returns whether the specified x and y is an end platform
     * @param  x The x coordinate
     * @param  y The y coordinate
     * @return   Whether the specified coordinate is an end platform
     */
    public boolean isEndPlatform(double x, double y) {
        return getPosition(x, y) == 2;
    }

    /**
     * Returns whether the specified x and y is a normal platform
     * @param  x The x coordinate
     * @param  y The y coordinate
     * @return   Whether the specified coordinate is a normal platform
     */
    public boolean isNormalPlatform(double x, double y) {
        return getPosition(x, y) == 1;
    }

    /**
     * Checks whether there is a block at the specified x and y coordinates
     * @param  x The x coordinate
     * @param  y The y coordinate
     * @return   Whether there is a block
     */
    public boolean isBlocked(double x, double y) {
        return getPosition(x, y) >= 1;
    }

    /**
     * Checks whether there is a special at the specified x and y coordinates
     * @param  x The x coordinate
     * @param  y The y coordinate
     * @return   Whether there is a special block
     */
    public boolean isSpecial(double x, double y) {
        return getPosition(x, y) < 0;
    }

    /**
     * Gets the min x the object specified by the bounding box can go
     * @param  obj The object to check
     * @return     The min x the object can go
     */
    public double getLeftBound(BoundingBox obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        for (double x = obj.getMinX(); x >= 0; x -= jumpX) {
            for (double y = obj.getMinY(); y < obj.getMaxY(); y += jumpY) {
                if (isBlocked(x, y)) {
                    return getActX(getBlockX(x) + 1) + OFFSET;
                }
            }
            if (isBlocked(x, obj.getMaxY() - OFFSET)) {
                return getActX(getBlockX(x) + 1) + OFFSET;
            }
        }
        return 0;
    }

    /**
     * Gets the max x the object specified by the bounding box can go
     * @param  obj The object to check
     * @return     The max x the object can go
     */
    public double getRightBound(BoundingBox obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        for (double x = obj.getMaxX(); x < Constants.SCREEN_WIDTH; x += jumpX) {
            for (double y = obj.getMinY(); y < obj.getMaxY(); y += jumpY) {
                if (isBlocked(x, y)) {
                    return getActX(getBlockX(x)) - OFFSET;
                }
            }
            if (isBlocked(x, obj.getMaxY() - OFFSET)) {
                return getActX(getBlockX(x)) - OFFSET;
            }
        }
        return Constants.SCREEN_WIDTH - 1;
    }

    /**
     * Gets the min y the object specified by the bounding box can go
     * @param  obj The object to check
     * @return     The min y the object can go
     */
    public double getUpperBound(BoundingBox obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        for (double y = obj.getMinY(); y >= 0; y -= jumpY) {
            for (double x = obj.getMinX(); x < obj.getMaxX(); x += jumpX) {
                if (isBlocked(x, y)) {
                    return getActY(getBlockY(y) + 1) + OFFSET;
                }
            }
            if (isBlocked(obj.getMaxX() - OFFSET, y)) {
                return getActY(getBlockY(y) + 1) + OFFSET;
            }
        }
        return 0;
    }

    /**
     * Gets the max x the object specified by the bounding box can go
     * @param  obj The object to check
     * @return     The max x the object can go
     */
    public double getLowerBound(BoundingBox obj) {
        int jumpX = Constants.PLATFORM_BLOCK_WIDTH / 2;
        int jumpY = Constants.PLATFORM_BLOCK_HEIGHT / 2;
        for (double y = obj.getMaxY(); y < screenLength(); y += jumpY) {
            for (double x = obj.getMinX(); x < obj.getMaxX(); x += jumpX) {
                if (isBlocked(x, y)) {
                    return getActY(getBlockY(y)) - OFFSET;
                }
            }
            if (isBlocked(obj.getMaxX() - OFFSET, y)) {
                return getActY(getBlockY(y)) - OFFSET;
            }
        }
        return screenLength() - 1;
    }
}
