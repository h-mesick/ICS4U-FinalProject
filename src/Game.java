import java.io.File;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

import javafx.animation.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 14, 2019: Created ~Evan Zhang
 *  - May 16, 2019: Updated ~Evan Zhang
 *  - May 18, 2019: Updated ~Evan Zhang
 *  - May 21, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 */
public class Game {
    private final EnumMap<State, BaseScene> sceneMap = new EnumMap<State, BaseScene>(State.class){{
        put(State.LOADING_SCREEN, new LoadingScreen(Game.this));
        put(State.ENTER_USERNAME, new EnterUsername(Game.this));
        put(State.MAIN_MENU, new MainMenu(Game.this));
        put(State.HIGH_SCORES, new Highscores(Game.this));
        put(State.HELP, new Help(Game.this));
//        put(State.TUTORIAL, new Tutorial(Game.this));
        put(State.LEVEL_SELECT, new LevelSelect(Game.this));
        put(State.LEVEL_ONE, new LevelOne(Game.this));
        put(State.LEVEL_TWO, new LevelTwo(Game.this));
        put(State.LEVEL_THREE, new LevelThree(Game.this));
    }};

    private State currentState = null, nextState = null;
    private BaseScene currentScene;
    private HashMap<String, User> users = new HashMap();

    public Stage stage;
    public User currentUser;

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

    public void setCurrentUser(String username) {
        if (!users.containsKey(username)) {
            this.currentUser = User.loadFromFile(username);
            users.put(username, this.currentUser);
        } else {
            this.currentUser = users.get(username);
        }
    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<User>(users.values());
    }

    public boolean levelComplete(int index) {
        return this.currentUser != null && this.currentUser.levelSaves[index] != null &&
               this.currentUser.levelSaves[index].levelComplete;
    }

    public void setScene(Scene scene) {
        this.stage.setScene(scene);
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void setNextState(State newState) {
        this.nextState = newState;
    }

    public boolean hasNextState() {
        return this.nextState != null;
    }

    public void nextState() {
        if (hasNextState()) {
            State tmpState = nextState;
            this.nextState = null;
            updateState(tmpState);
        }
    }

    public void updateState(State newState) {
        // save everytime the user goes to a different state
        // note that this might not be the most efficient way
        if (this.currentUser != null) {
            this.currentUser.updateAll();
        }
        if (this.currentScene != null) {
            this.currentScene.onExit();
        }
        this.currentState = newState;
        updateScene();
    }

    public void updateScene() {
        currentScene = sceneMap.get(this.currentState);
        currentScene.initScene();
        currentScene.onEnter();
    }
}
