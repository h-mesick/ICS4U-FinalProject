/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * The User class for storing user data.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import javax.json.*;

/**
 * The User class for storing user data.
 * <pre>
 * Revision history:
 *  - May 28, 2019: Created ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 *  - Jun 2, 2019: Commented ~Evan Zhang
 *  - Jun 3, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Commented ~Evan Zhang
 *  - Jun 6, 2019: Commented ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public class User implements Comparable {
    /** The user's username */
    public String username;
    /** The user's current score */
    public int score;
    /** The array of the user's level game saves */
    public BaseGameSave[] levelSaves = new BaseGameSave[Constants.NUM_LEVELS];

    /**
     * User Constructor
     * @param  username The user's username
     * @param  score    The user's score
     * @param  saves    An array of the user's saves
     */
    public User(String username, int score, BaseGameSave[] saves) {
        this.username = username;
        this.score = score;
        if (saves.length != Constants.NUM_LEVELS) {
            throw new IllegalArgumentException("Saves array must be exactly length " + Constants.NUM_LEVELS + ".");
        }
        this.levelSaves = saves;
    }

    /**
     * Recomputes the user's score and saves it to file
     */
    public void updateAll() {
        computeScore();
        saveToFile();
    }

    /**
     * Computes the user's score by taking the sum of the scores of the last non-null BaseGameSave in the array
     */
    public void computeScore() {
        for (BaseGameSave g : levelSaves) {
            if (g instanceof PlatformerGameSave) {
                this.score = 0;
                for (int p : ((PlatformerGameSave)g).scores) {
                    this.score += p;
                }
            }
        }
    }

    /**
     * Saves the user to a file
     */
    public void saveToFile() {
        try {
            FileWriter stream = new FileWriter(getDataFile(this.username));

            JsonArrayBuilder saves = Json.createArrayBuilder();
            for (BaseGameSave g : levelSaves) {
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

    /**
     * Loads the user from file
     * @param  username The username of the user to load
     * @return          The loaded user
     */
    public static User loadFromFile(String username) {
        try (JsonReader reader = Json.createReader(new FileInputStream(getDataFile(username)))) {
            JsonObject obj = reader.readObject();

            BaseGameSave[] saves = new BaseGameSave[Constants.NUM_LEVELS];
            JsonArray savesArray = obj.getJsonArray("saves");
            for (int i = 0; i < Constants.NUM_LEVELS; i++) {
                if (!savesArray.isNull(i)) {
                    /** kind of a dirty way to do this but there's no better way */
                    if (i == 0) {
                        saves[i] = StoryGameSave.fromJson(savesArray.getJsonObject(i));
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
        return new User(username, 0, new BaseGameSave[3]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Object other) {
        return this.score - ((User)other).score;
    }

    /**
     * Gets the save file of the specified user
     * @param  username The user's username
     * @return          The location of the data file
     */
    public static String getDataFile(String username) {
        return Constants.DATA_DIRECTORY + username + Constants.DATA_EXTENSION;
    }
}
