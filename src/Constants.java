/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * Class containing all the global constants in the game.
 */

/**
 * Class containing all the global constants in the game.
 * <pre>
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 14, 2019: Updated ~Evan Zhang
 *  - May 16, 2019: Updated ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 31, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 *  - Jun 3, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Commented ~Evan Zhang
 *  - Jun 6, 2019: Updated ~Evan Zhang
 *  - Jun 7, 2019: Finished ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public abstract class Constants {
    /** The number of the levels */
    static final int NUM_LEVELS = 3;

    /** The width of the screen */
    static final int SCREEN_WIDTH = 900;
    /** The height of the screen */
    static final int SCREEN_HEIGHT = 600;
    /** The width of each platform block */
    static final int PLATFORM_BLOCK_WIDTH = 30;
    /** The height of each platform block */
    static final int PLATFORM_BLOCK_HEIGHT = 30;
    /** The number of platform blocks to fit the screen width */
    static final int BLOCK_WIDTH_COUNT = SCREEN_WIDTH / PLATFORM_BLOCK_WIDTH;
    /** The number of platform blocks to fit the screen height */
    static final int BLOCK_HEIGHT_COUNT = SCREEN_HEIGHT / PLATFORM_BLOCK_HEIGHT;

    /** The directory to store the save files */
    static final String DATA_DIRECTORY = System.getProperty("user.home") + "/climbtograce_saves/";
    /** The extension for the save files */
    static final String DATA_EXTENSION = ".ctgd";

    /** Comparing doubles is inprecise, so the EPS exists */
    static final double EPS = 1e-7;
}
