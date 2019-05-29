/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 14, 2019: Updated ~Evan Zhang
 *  - May 16, 2019: Updated ~Evan Zhang
 */
public interface Constants {
    static final int NUM_LEVELS = 3;

    static final int SCREEN_WIDTH = 900;
    static final int SCREEN_HEIGHT = 600;
    static final int PLATFORM_BLOCK_WIDTH = 30;
    static final int PLATFORM_BLOCK_HEIGHT = 30;
    static final int BLOCK_WIDTH_COUNT = SCREEN_WIDTH / PLATFORM_BLOCK_WIDTH;
    static final int BLOCK_HEIGHT_COUNT = SCREEN_HEIGHT / PLATFORM_BLOCK_HEIGHT;

    static final String DATA_DIRECTORY = "saves/";
}
