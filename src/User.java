import java.io.*;
import java.lang.IllegalArgumentException;

import javax.json.*;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 28, 2019: Created ~Evan Zhang
 */
public class User implements Comparable {
    public String username;
    public int score;
    public GameSave[] levelSaves = new GameSave[Constants.NUM_LEVELS];

    public User(String username, int score, GameSave[] saves) {
        this.username = username;
        this.score = score;
        if (saves.length != Constants.NUM_LEVELS) {
            throw new IllegalArgumentException("Saves array must be exactly length " + Constants.NUM_LEVELS + ".");
        }
        this.levelSaves = saves;
    }

    public void saveToFile() {
        try {
            FileWriter stream = new FileWriter(getDataFile(this.username));

            JsonArrayBuilder saves = Json.createArrayBuilder();
            for (GameSave g : levelSaves) {
                if (g == null) {
                    saves = saves.addNull();
                } else {
                    saves = saves.add(g.toJson());
                }
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
            System.err.println("Warning: Failed to save data file.");
            e.printStackTrace();
        }
    }

    public static User loadFromFile(String username) {
        try {
            JsonReader reader = Json.createReader(new FileInputStream(getDataFile(username)));
            JsonObject obj = reader.readObject();
            reader.close();

            GameSave[] saves = new GameSave[Constants.NUM_LEVELS];
            JsonArray savesArray = obj.getJsonArray("saves");
            for (int i = 0; i < Constants.NUM_LEVELS; i++) {
                if (!savesArray.isNull(i)) {
                    // kind of a dirty way to do this but there's no better way
                    if (i == 0) {
                        saves[i] = GameSave.fromJson(savesArray.getJsonObject(i));
                    } else {
                        saves[i] = PlatformerGameSave.fromJson(savesArray.getJsonObject(i));
                    }
                }
            }
            return new User(username, obj.getInt("score"), saves);
        } catch (FileNotFoundException e) {
            System.err.println(username + " has no save file, creating it....");
        } catch (Exception e) {
            System.err.println("Warning: Data file has been tampered with! Not using it.");
        }
        return new User(username, 0, new GameSave[3]);
    }

    @Override
    public int compareTo(Object other) {
        return this.score - ((User)other).score;
    }

    public static String getDataFile(String username) {
        return Constants.DATA_DIRECTORY + username + ".data";
    }
}
