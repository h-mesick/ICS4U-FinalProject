import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 14, 2019: Created ~Evan Zhang
 */
public class Game {
    private State currentState;

    public Stage stage;


    public Game(Stage stage) {
        this.stage = stage;
        updateState(State.LOADING_SCREEN);
    }

    public void setScene(Scene scene) {
        this.stage.setScene(scene);
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
            case HELP:
                // new Help(this).initScene();
                break;
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
