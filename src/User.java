/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 28, 2019: Created ~Evan Zhang
 */
public class User implements Comparable {
    public String username;
    public GameSave[] levelSaves = new GameSave[Constants.NUM_LEVELS];
    public int score;

    public void saveToFile() {
    }

    public static User loadFromFile(String username) {
        return null;
    }

    @Override
    public int compareTo(Object other) {
        return this.score - ((User)other).score;
    }
}
