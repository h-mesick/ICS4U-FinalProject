import java.io.*;

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
        try {
            FileWriter stream = new FileWriter(username + ".data");
            stream.write(username + "\n");
            stream.write("" + score + "\n");
            for (GameSave g : levelSaves) {
                stream.write("----------\n");
                stream.write(g.saveToFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User loadFromFile(String username) {
        return null;
    }

    @Override
    public int compareTo(Object other) {
        return this.score - ((User)other).score;
    }
}
