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
 */
public class Game {
    private State currentState;
    private State nextState = null;

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
        this.currentState = newState;
        updateScene();
    }

    public void updateScene() {
        switch(this.currentState) {
            case LOADING_SCREEN: new LoadingScreen(this).initScene(); break;
            case MAIN_MENU: new MainMenu(this).initScene(); break;
            case HIGH_SCORES:
                // new HighScores(this).initScene();
                break;
            case HELP: new Help(this).initScene(); break;
            case TUTORIAL:
                // new Tutorial(this).initScene();
                break;
            case LEVEL_SELECT: new LevelSelect(this).initScene(); break;
            case LEVEL_ONE: new LevelOne(this).initScene(); break;
            case LEVEL_TWO: new LevelTwo(this).initScene(); break;
            case LEVEL_THREE: new LevelThree(this).initScene(); break;
        }
    }
}
