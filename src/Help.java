/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * The Help scene.
 */

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

/**
 * The Help scene.
 * <pre>
 * Revision history:
 * - May 16, 2019: Created ~Evan Zhang
 * - May 18, 2019: Updated ~Evan Zhang
 * - May 27, 2019: Commented ~Max Li
 * - May 31, 2019: Updated ~Evan Zhang
 * - June 2, 2019: Updated ~Max Li
 * - June 2, 2019: Finished ~Max Li
 * - June 3, 2019: Finishing touches ~Max Li
 * </pre>
 *
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
     * Initializes the scene to the help window.
     */
    public void initScene() {
        page1();
    }

    /**
     * Page 1 of the help window.
     */
    private void page1() {
        VBox body = new VBox(5);
        body.setAlignment(Pos.TOP_CENTER);
        body.getChildren().add(new ImageView(ResourceLoader.loadImage("help-logo.png")));
        Text heading1 = new Text("Objectives");
        heading1.setFont(Util.getMainFont(25));
        body.getChildren().add(heading1);

        Text heading2 = new Text("Level 1");
        heading2.setFont(Util.getMainFont(20));
        body.getChildren().add(heading2);

        Text content = new Text(
                "Level 1 is a choose your own adventure. This means that the player has to explore the world by\n" +
                        "themselves and make their own choices. By playing through the scenes in the story, the player\n" +
                        "will come across prompts, where they will be given a list of possible choices they can make.\n" +
                        "By good making choices, the player will get coins and will open more good opportunities for\n" +
                        "themselves. However, by making bad choices, the player will lose opportunities.\n");
        content.setFont(Util.getMainFont(15));
        body.getChildren().add(content);

        GridPane list = new GridPane();
        list.setAlignment(Pos.CENTER);
        list.setHgap(50);

        ImageView imageView = new ImageView(ResourceLoader.loadImage("help/level1select.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(140);
        list.add(imageView, 0, 0);

        content = new Text("An example of a choice the player will get in Level 1,\n" +
                "and the choices that the user can choose.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 0);

        body.getChildren().add(list);

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

    /**
     * Page 2 of the help window.
     */
    private void page2() {
        VBox body = new VBox(5);
        body.setAlignment(Pos.TOP_CENTER);
        body.getChildren().add(new ImageView(ResourceLoader.loadImage("help-logo.png")));
        Text heading1 = new Text("Objectives");
        heading1.setFont(Util.getMainFont(25));
        body.getChildren().add(heading1);

        Text heading2 = new Text("Level 2 & 3");
        heading2.setFont(Util.getMainFont(20));
        body.getChildren().add(heading2);

        Text content = new Text(
                "Level 2 & 3 are platformer based levels. This means that there are platforms the player can\n" +
                        "jump on to. The objective of these levels are to collect the collectibles, coins for level 2\n" +
                        "and stars for level 3. A prompt will open up when collecting a coin or star, and the player has\n" +
                        "to choose the correct choices to get coins/points. In level 3 you can buy things with previously\n" +
                        "acquired coins to gain more points.");
        content.setFont(Util.getMainFont(15));
        body.getChildren().add(content);


        GridPane list = new GridPane();
        list.setAlignment(Pos.CENTER);
        list.setHgap(50);

        ImageView imageView = new ImageView(ResourceLoader.loadImage("help/level2select.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(140);
        list.add(imageView, 0, 0);

        content = new Text("An example of a choice the player will get in Level 2/3,\n" +
                "and the choices that the user can choose.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 0);

        body.getChildren().add(list);

        content = new Text("Final score is calculated as: Coins(money) left + Stars(score from level 3)");
        content.setFont(Util.getMainFont(17));
        body.getChildren().add(content);

        ImageButton backButton = Util.getMainImageButton("back", event -> page1());

        ImageButton forwardButton = Util.getMainImageButton("forward", event -> page3());

        this.game.setScene(new Scene(Util.getMainRoot(body, Util.getFooter(backButton, forwardButton))));
    }

    /**
     * Page 3 of the help window.
     */
    private void page3() {
        VBox body = new VBox();
        body.setAlignment(Pos.TOP_CENTER);
        body.getChildren().add(new ImageView(ResourceLoader.loadImage("help-logo.png")));
        Text heading1 = new Text("Special Objects");
        heading1.setFont(Util.getMainFont(25));
        body.getChildren().add(heading1);

        Text heading2 = new Text("Level 2 & 3");
        heading2.setFont(Util.getMainFont(20));
        body.getChildren().add(heading2);

        GridPane list = new GridPane();
        list.setAlignment(Pos.CENTER);
        list.setHgap(50);
        list.setVgap(10);

        ImageView imageView = new ImageView(ResourceLoader.loadImage("/player/004.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 0);
        Text content = new Text("This is the sprite that the player controls.");
        content.setFont(Util.getMainFont(13));
        list.add(content, 1, 0);

        imageView = new ImageView(ResourceLoader.loadImage("platform-top.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 1);
        content = new Text("These are platform blocks that you can jump onto.");
        content.setFont(Util.getMainFont(13));
        list.add(content, 1, 1);

        imageView = new ImageView(ResourceLoader.loadImage("coin.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 2);
        content = new Text("Coins are what you are trying to collect in level 2 and represent money.\n" +
                "A prompt will show up when you collect a coin and you choose an option\n" +
                "which makes you either gain or lose money.");
        content.setFont(Util.getMainFont(13));
        list.add(content, 1, 2);

        imageView = new ImageView(ResourceLoader.loadImage("star.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 3);
        content = new Text("Stars are what you are trying to collect in level 3 and represent points.\n" +
                "A prompt will show up when you collect a star. This prompt can be a buying choice \n" +
                "or a lifestyle choice. Depending on your choice you will gain or lose points.");
        content.setFont(Util.getMainFont(13));
        list.add(content, 1, 3);

        imageView = new ImageView(ResourceLoader.loadImage("platform-door.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 4);
        content = new Text("Once you reach the top of the level, you can finish the level through this door.");
        content.setFont(Util.getMainFont(13));
        list.add(content, 1, 4);

        body.getChildren().add(list);

        ImageButton backButton = Util.getMainImageButton("back", event -> page2());

        ImageButton forwardButton = Util.getMainImageButton("forward", event -> page4());

        this.game.setScene(new Scene(Util.getMainRoot(body, Util.getFooter(backButton, forwardButton))));
    }

    /**
     * Page 4 of the help window.
     */
    private void page4() {
        VBox body = new VBox(15);
        body.setAlignment(Pos.TOP_CENTER);
        body.getChildren().add(new ImageView(ResourceLoader.loadImage("help-logo.png")));
        Text heading1 = new Text("Controls");
        heading1.setFont(Util.getMainFont(25));
        body.getChildren().add(heading1);

        Text heading2 = new Text("Level 1");
        heading2.setFont(Util.getMainFont(20));
        body.getChildren().add(heading2);

        GridPane list = new GridPane();
        list.setAlignment(Pos.CENTER);
        list.setHgap(30);
        list.setVgap(10);

        Text enter = new Text("ENTER / LEFT-CLICK");
        enter.setFont(Util.getMainFont(17));
        list.add(enter, 0, 0);
        Text content = new Text("Use the enter key or the left click on mouse to play though the scenes.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 0);

        body.getChildren().add(list);

        Text heading3 = new Text("Level 2 & 3");
        heading3.setFont(Util.getMainFont(20));
        body.getChildren().add(heading3);

        list = new GridPane();
        list.setAlignment(Pos.CENTER);
        list.setHgap(30);
        list.setVgap(10);

        ImageView imageView = new ImageView(ResourceLoader.loadImage("/help/arrows.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(40);
        list.add(imageView, 0, 0);
        content = new Text("Use the left/right arrow keys to move left/right. Use the up arrow key to jump.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 0);

        Text space = new Text("SPACE / ESC");
        space.setFont(Util.getMainFont(17));
        list.add(space, 0, 3);
        content = new Text("Use the space or esc keys to get to the in game menu.");
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 3);

        body.getChildren().add(list);

        ImageButton backButton = Util.getMainImageButton("back", event -> page3());

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