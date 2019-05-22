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
 */
public class Game {
    private State currentState = null, nextState = null;
    private GameSave[] levelSave = new GameSave[Constants.NUM_LEVELS];
    private BaseScene currentScene;

    public Stage stage;

    public Game(Stage stage) {
        this.stage = stage;
        updateState(State.LOADING_SCREEN);
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
        if (this.currentState != null) {
            switch (this.currentState) {
                case LEVEL_ONE: levelSave[0] = ((BaseLevel)currentScene).save(); break;
                case LEVEL_TWO: levelSave[1] = ((BaseLevel)currentScene).save(); break;
                case LEVEL_THREE: levelSave[2] = ((BaseLevel)currentScene).save(); break;
            }
        }
        this.currentState = newState;
        updateScene();
    }

    public void updateScene() {
        currentScene = null;
        switch(this.currentState) {
            case LOADING_SCREEN: currentScene =  new LoadingScreen(this); break;
            case MAIN_MENU: currentScene = new MainMenu(this); break;
            case HIGH_SCORES:
                // currentScene = new HighScores(this);
                break;
            case HELP: currentScene = new Help(this); break;
            case TUTORIAL:
                // currentScene = new Tutorial(this);
                break;
            case LEVEL_SELECT: currentScene = new LevelSelect(this); break;
            case LEVEL_ONE: currentScene = new LevelOne(this); break;
            case LEVEL_TWO: currentScene = new LevelTwo(this); break;
            case LEVEL_THREE: currentScene = new LevelThree(this); break;
        }
        currentScene.initScene();
        switch(this.currentState) {
            case LEVEL_ONE: ((BaseLevel)currentScene).load(levelSave[0]); break;
            case LEVEL_TWO: ((BaseLevel)currentScene).load(levelSave[1]); break;
            case LEVEL_THREE: ((BaseLevel)currentScene).load(levelSave[2]); break;
        }
    }
}
