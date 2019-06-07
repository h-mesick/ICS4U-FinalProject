/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * The Game class that stores the game states and transitions.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.*;
import javafx.stage.*;

/**
 * The Game class that stores the game states and transitions.
 * <pre>
 * Revision history:
 *  - May 14, 2019: Created ~Evan Zhang
 *  - May 16, 2019: Updated ~Evan Zhang
 *  - May 18, 2019: Updated ~Evan Zhang
 *  - May 21, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 *  - Jun 2, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Commented ~Evan Zhang
 *  - Jun 4, 2019: Updated ~Evan Zhang
 *  - Jun 6, 2019: Updated ~Evan Zhang
 *  - Jun 6, 2019: Commented ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public class Game {
    /** The current state of the game */
    private State currentState = null;
    /** The proposed next state of the game by the current state */
    private State nextState = null;
    /** The current scene of the game */
    private BaseScene currentScene;
    /** The list of users */
    private Map<String, User> users = new HashMap<String, User>();

    /** The stage to draw everything */
    public Stage stage;
    /** The current user playing the game */
    public User currentUser;

    /**
     * Constructor
     * @param stage The current stage to draw everything
     */
    public Game(Stage stage) {
        this.stage = stage;

        for (File f : new File(Constants.DATA_DIRECTORY).listFiles()) {
            String filename = f.getName();
            if (f.isFile() && filename.endsWith(Constants.DATA_EXTENSION)) {
                User u = User.loadFromFile(filename.substring(0, filename.length() - Constants.DATA_EXTENSION.length()));
                users.put(u.username, u);
            }
        }
        // TODO: change to loading screen
        // updateState(State.LOADING_SCREEN);
        updateState(State.ENTER_USERNAME);
    }

    /**
     * Sets the current user of the game
     * @param username The user's username
     */
    public void setCurrentUser(String username) {
        if (!users.containsKey(username)) {
            this.currentUser = User.loadFromFile(username);
            users.put(username, this.currentUser);
        } else {
            this.currentUser = users.get(username);
        }
    }

    /**
     * Gets all the users with a save file
     * @return The list of all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<User>(users.values());
    }

    /**
     * Gets whether the level is complete or not
     * @param  index The level number
     * @return       Whether the level is complete
     */
    public boolean levelComplete(int index) {
        return this.currentUser != null && this.currentUser.levelSaves[index] != null &&
               this.currentUser.levelSaves[index].levelComplete;
    }

    /**
     * Sets the scene of the stage
     * @param scene The scene
     */
    public void setScene(Scene scene) {
        this.stage.setScene(scene);
    }

    /**
     * Gets the current state of the game
     * @return The current state of the game
     */
    public State getCurrentState() {
        return this.currentState;
    }

    /**
     * Sets the next state of the game
     * @param newState The next state of the game
     */
    public void setNextState(State newState) {
        this.nextState = newState;
    }

    /**
     * Returns whether there is a next state or not
     * @return Whether there is a next state or not
     */
    public boolean hasNextState() {
        return this.nextState != null;
    }

    /**
     * Switchs to the next state, if there is one
     */
    public void nextState() {
        if (hasNextState()) {
            State tmpState = nextState;
            this.nextState = null;
            updateState(tmpState);
        }
    }

    /**
     * Changes the state to the one specified
     * @param newState The new state
     */
    public void updateState(State newState) {
        if (this.currentScene != null) {
            this.currentScene.onExit();
        }
        this.currentState = newState;
        updateScene();
        /**
         * Saves everytime the user goes to a different state.
         * Note that this might not be the most efficient way,
         * but it is the most transparent way.
         */
        if (this.currentUser != null) {
            this.currentUser.updateAll();
        }
    }

    /**
     * Updates the scene to the new scene defined by the current state
     */
    public void updateScene() {
        currentScene = null;
        switch (this.currentState) {
            case LOADING_SCREEN: currentScene =  new LoadingScreen(this); break;
            case ENTER_USERNAME: currentScene = new EnterUsername(this); break;
            case MAIN_MENU: currentScene = new MainMenu(this); break;
            case HIGH_SCORES: currentScene = new Highscores(this); break;
            case HELP: currentScene = new Help(this); break;
            case LEVEL_SELECT: currentScene = new LevelSelect(this); break;
            case LEVEL_ONE: currentScene = new LevelOne(this); break;
            case LEVEL_TWO: currentScene = new LevelTwo(this); break;
            case LEVEL_THREE: currentScene = new LevelThree(this); break;
            default: break;
        }
        currentScene.initScene();
        currentScene.onEnter();
    }
}
