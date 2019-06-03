import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.util.*;

/**
 * Revision history:
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 22, 2019: Finished ~Max Li
 *  - May 22, 2019: Finishing touches ~Evan Zhang
 *  - May 27, 2019: Commented ~Max Li
 *  - May 28, 2019: Updated ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
 * @author Evan Zhang
 * @version 1
 */
public class LoadingScreen extends BaseScene {
    /**
     * Constructor for the LoadingScreen class.
     * @param game The current game that is running.
     */
    public LoadingScreen(Game game) {
        super(game);
    }

    /**
     * {@inheritDoc}
     */
    public void initScene() {
        BorderPane root = new BorderPane();

        VBox body = new VBox(10);
        body.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView();

        body.getChildren().add(imageView);

        KeyFrame frame1 = new KeyFrame(Duration.seconds(0),
                new KeyValue(imageView.imageProperty(),
                        ResourceLoader.loadImage("company-logo.png")),
                new KeyValue(imageView.opacityProperty(), 0.0));
        KeyFrame fadein1 = new KeyFrame(Duration.seconds(2), new KeyValue(imageView.opacityProperty(), 1.0));
        KeyFrame fadeout1 = new KeyFrame(Duration.seconds(3), new KeyValue(imageView.opacityProperty(), 0.0));
        KeyFrame frame2 = new KeyFrame(Duration.seconds(3), new KeyValue(imageView.imageProperty(),
                                       ResourceLoader.loadImage("game-logo.png")));
        KeyFrame fadein2 = new KeyFrame(Duration.seconds(5), new KeyValue(imageView.opacityProperty(), 1.0));
        KeyFrame fadeout2 = new KeyFrame(Duration.seconds(6), new KeyValue(imageView.opacityProperty(), 0.0));
        Timeline timeline = new Timeline(frame1, fadein1, fadeout1, frame2, fadein2, fadeout2);


        for (int i = 1; i <= 6; i++) {
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(5 + i),
                    new KeyValue(imageView.imageProperty(),
                                 ResourceLoader.loadImage("loadingscreen/house" + i + ".jpg")),
                    new KeyValue(imageView.opacityProperty(), 1.0))
            );
        }

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        KeyFrame end = new KeyFrame(Duration.seconds(12), event -> game.updateState(State.ENTER_USERNAME));

        timeline.getKeyFrames().add(end);
        timeline.play();

        root.setCenter(body);

        this.game.setScene(new Scene(root));
    }
}
