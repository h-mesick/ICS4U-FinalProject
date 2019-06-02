import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 * Revision history:
 *  - May 16, 2019: Created ~Evan Zhang
 *  - May 18, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Max Li
 *  - May 31, 2019: Updated ~Evan Zhang
 *  - June 2, 2019: Updated ~Max Li
 * @author Evan Zhang
 * @version 1
 */
public class Help extends BaseScene {
    /**
     * Constructor for the Help class.
     *
     * @param game The current game that is running.
     */
    public Help(Game game) {
        super(game);
    }

    /**
     * {@inheritDoc}
     */
    public void initScene() {
        // TODO: set to page 1 once level 1 is done
        page2();
    }

    //TODO: level 1
    private void page1() {
        VBox body = new VBox();
        body.setAlignment(Pos.TOP_CENTER);
        body.getChildren().add(new ImageView(ResourceLoader.loadImage("help-logo.png")));
        Text heading = new Text("Level 1");
        heading.setFont(Util.getMainFont(25));
        body.getChildren().add(heading);

        ImageButton backButton = Util.getMainImageButton("back", event -> {
            if (this.game.hasNextState()) {
                this.game.nextState();
            } else {
                this.game.updateState(State.MAIN_MENU);
            }
        });

        ImageButton forwardButton = Util.getMainImageButton("forward", event -> page2());
        this.game.setScene(new Scene(Util.getMainRoot(body, Util.getFooter(backButton, forwardButton))));
    }

    private void page2() {
        VBox body = new VBox();
        body.setAlignment(Pos.TOP_CENTER);
        body.getChildren().add(new ImageView(ResourceLoader.loadImage("help-logo.png")));
        Text heading = new Text("Level 2 & 3");
        heading.setFont(Util.getMainFont(25));
        body.getChildren().add(heading);

        GridPane list = new GridPane();
        list.setAlignment(Pos.CENTER);
        list.setHgap(50);
        list.setVgap(10);
        list.setPadding(new Insets(10, 10, 10, 10));

        ImageView imageView = new ImageView(ResourceLoader.loadImage("/player/004.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 0);
        Text content = new Text("This is the sprite that the player controls.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 0);

        imageView = new ImageView(ResourceLoader.loadImage("platform-top.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 1);
        content = new Text("These are platform blocks that you can jump onto.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 1);

        imageView = new ImageView(ResourceLoader.loadImage("coin.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 2);
        content = new Text("Coins are what you are trying to collect in level 2 and represent money.\n" +
                "A prompt will show up when you collect a coin and you choose an option\n" +
                "which makes you either gain or lose money.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 2);

        imageView = new ImageView(ResourceLoader.loadImage("star.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 3);
        content = new Text("Stars are what you are trying to collect in level 3 and represent points.\n" +
                "A prompt will show up when you collect a star. This prompt can be a buying choice \n" +
                "or a lifestyle choice. Depending on your choice you will gain or lose points.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 3);

        imageView = new ImageView(ResourceLoader.loadImage("platform-door.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 4);
        content = new Text("Once you reach the top of the level, you can finish the level through this door.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 4);

        body.getChildren().add(list);

        ImageButton backButton = Util.getMainImageButton("back", event -> page1());

        ImageButton forwardButton = Util.getMainImageButton("forward", event -> page3());

        this.game.setScene(new Scene(Util.getMainRoot(body, Util.getFooter(backButton, forwardButton))));
    }

    private void page3() {
        VBox body = new VBox();
        body.setAlignment(Pos.TOP_CENTER);
        body.getChildren().add(new ImageView(ResourceLoader.loadImage("help-logo.png")));
        Text heading1 = new Text("Controls");
        heading1.setFont(Util.getMainFont(30));
        body.getChildren().add(heading1);

        Text heading2 = new Text("Level 1");
        heading2.setFont(Util.getMainFont(25));
        body.getChildren().add(heading2);

        //TODO: add controls for level 1

        Text heading3 = new Text("Level 2 & 3");
        heading3.setFont(Util.getMainFont(25));
        body.getChildren().add(heading3);

        GridPane list = new GridPane();
        list.setAlignment(Pos.CENTER);
        list.setHgap(50);
        list.setVgap(10);
        list.setPadding(new Insets(10, 10, 10, 10));

        // TODO: will probably combine the arrow keys together into one row to save space

        ImageView imageView = new ImageView(ResourceLoader.loadImage("/help/leftarrow.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 0);
        Text content = new Text("Use the left arrow key to move left.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 0);

        imageView = new ImageView(ResourceLoader.loadImage("/help/rightarrow.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 1);
        content = new Text("Use the right arrow key to move right.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 1);

        imageView = new ImageView(ResourceLoader.loadImage("/help/uparrow.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 2);
        content = new Text("Use the up arrow key to jump.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 2);

        Text space = new Text("SPACE / ESC");
        space.setFont(Util.getMainFont(20));
        list.add(space, 0, 3);
        content = new Text("Use the space or esc keys to get to the in game menu");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 3);

        body.getChildren().add(list);

        ImageButton backButton = Util.getMainImageButton("back", event -> page2());

        ImageButton forwardButton = Util.getMainImageButton("forward", event -> {
            this.game.updateState(State.MAIN_MENU);
            if (this.game.hasNextState()) {
                this.game.nextState();
            } else {
                this.game.updateState(State.MAIN_MENU);
            }
        });

        this.game.setScene(new Scene(Util.getMainRoot(body, Util.getFooter(backButton, forwardButton))));
    }
}
