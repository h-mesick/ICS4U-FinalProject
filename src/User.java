import java.io.*;

import javax.json.*;

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

            JsonArrayBuilder saves = Json.createArrayBuilder();
            for (GameSave g : levelSaves) {
                saves = saves.add(g.toJson());
            }

            JsonWriter writer = Json.createWriter(stream);
            writer.writeObject(
                Json.createObjectBuilder()
                    .add("username", username)
                    .add("score", score)
                    .add("saves", saves.build())
                    .build()
            );
            writer.close();
            stream.close();
        } catch (Exception e) {
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
