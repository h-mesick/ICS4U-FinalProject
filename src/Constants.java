/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 14, 2019: Updated ~Evan Zhang
 *  - May 16, 2019: Updated ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 31, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 */
public interface Constants {
    /** The number of the levels */
    static final int NUM_LEVELS = 3;

    /** Dimensions of various objects */
    static final int SCREEN_WIDTH = 900;
    static final int SCREEN_HEIGHT = 600;
    static final int PLATFORM_BLOCK_WIDTH = 30;
    static final int PLATFORM_BLOCK_HEIGHT = 30;
    static final int BLOCK_WIDTH_COUNT = SCREEN_WIDTH / PLATFORM_BLOCK_WIDTH;
    static final int BLOCK_HEIGHT_COUNT = SCREEN_HEIGHT / PLATFORM_BLOCK_HEIGHT;

    /** Save-related constants */
    static final String DATA_DIRECTORY = "saves/";
    static final String DATA_EXTENSION = ".ctgd";

    /** Comparing doubles is bad, so the EPS exists */
    static final double EPS = 1e-7;
}
