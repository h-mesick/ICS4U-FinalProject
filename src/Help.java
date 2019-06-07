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
import javafx.scene.paint.*;
import javafx.scene.text.*;

/**
 * The Help scene.
 * <pre>
 * Revision history:
 *  - May 16, 2019: Created ~Evan Zhang
 *  - May 18, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Max Li
 *  - May 31, 2019: Updated ~Evan Zhang
 *  - Jun 2, 2019: Updated ~Max Li
 *  - Jun 2, 2019: Finished ~Max Li
 *  - Jun 3, 2019: Finishing touches ~Max Li
 *  - Jun 4, 2019: Updated ~Evan Zhang
 *  - Jun 6, 2019: Commented ~Evan Zhang
 *  - Jun 6, 2019: Updated ~Evan Zhang
 * </pre>
 * @author Max Li, Evan Zhang
 * @version 1
 */
public class Help extends BaseScene {
    /**
     * Constructor for the Help class.
     * @param game The current game that is running.
     */
    public Help(Game game) {
        super(game);
    }

    /**
     * {@inheritDoc}
     */
    public void initScene() {
        page1();
    }

    /**
     * Reads the help content from a file.
     * @param  filename The filename to load
     * @return          The String of text loaded
     */
    private String readText(String filename) {
        return Util.readLines(ResourceLoader.loadHelp(filename)).get(0);
    }

    /**
     * Gets the main body for help.
     * @param  spacing The VBOX spacing
     * @return         The main body forhelp
     */
    private VBox getMainBody(double spacing) {
        Image helpLogo = ResourceLoader.loadImage("help-logo.png");

        VBox body = new VBox(spacing);
        body.setAlignment(Pos.TOP_CENTER);
        body.setBackground(new Background(new BackgroundFill(
            Color.web("#000000", 0.4),
            CornerRadii.EMPTY,
            new Insets(helpLogo.getHeight() - 10, 0, 0, 0)
        )));

        body.getChildren().add(new ImageView(helpLogo));
        return body;
    }

    /**
     * Page 1 of the help window.
     */
    private void page1() {
        VBox body = getMainBody(5);

        Text heading1 = new Text("Objectives");
        heading1.setFill(Color.WHITE);
        heading1.setFont(Util.getMainFont(25));
        body.getChildren().add(heading1);

        Text heading2 = new Text("Level 1");
        heading2.setFill(Color.WHITE);
        heading2.setFont(Util.getMainFont(20));
        body.getChildren().add(heading2);

        Text content = new Text(readText("page1/content-1.txt"));
        content.setFill(Color.WHITE);
        content.setFont(Util.getMainFont(15));
        content.setWrappingWidth(Constants.SCREEN_WIDTH * 3 / 5);
        content.setTextAlignment(TextAlignment.JUSTIFY);


        ImageView imageView = new ImageView(ResourceLoader.loadImage("help/level1select.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(140);

        HBox list = new HBox(15);
        list.setPadding(new Insets(25));
        list.getChildren().add(content);
        list.getChildren().add(imageView);

        body.getChildren().add(list);

        content = new Text(readText("page1/footer.txt"));
        content.setFill(Color.WHITE);
        content.setFont(Util.getMainFont(17));
        body.getChildren().add(content);

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
        VBox body = getMainBody(5);

        Text heading1 = new Text("Objectives");
        heading1.setFill(Color.WHITE);
        heading1.setFont(Util.getMainFont(25));
        body.getChildren().add(heading1);

        Text heading2 = new Text("Level 2 & 3");
        heading2.setFill(Color.WHITE);
        heading2.setFont(Util.getMainFont(20));
        body.getChildren().add(heading2);

        Text content1 = new Text(readText("page2/content-1.txt"));
        content1.setFill(Color.WHITE);
        content1.setWrappingWidth(Constants.SCREEN_WIDTH * 3 / 5);
        content1.setTextAlignment(TextAlignment.JUSTIFY);
        content1.setFont(Util.getMainFont(13));
        Text content2 = new Text(readText("page2/content-2.txt"));
        content2.setFill(Color.WHITE);
        content2.setWrappingWidth(Constants.SCREEN_WIDTH * 3 / 5);
        content2.setTextAlignment(TextAlignment.JUSTIFY);
        content2.setFont(Util.getMainFont(13));

        VBox content = new VBox(7);
        content.getChildren().addAll(content1, content2);

        ImageView imageView = new ImageView(ResourceLoader.loadImage("help/level2select.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(140);

        HBox list = new HBox(15);
        list.setPadding(new Insets(25));
        list.getChildren().addAll(content, imageView);

        body.getChildren().add(list);

        content1 = new Text(readText("page2/footer.txt"));
        content1.setFill(Color.WHITE);
        content1.setFont(Util.getMainFont(17));
        body.getChildren().add(content1);

        ImageButton backButton = Util.getMainImageButton("back", event -> page1());

        ImageButton forwardButton = Util.getMainImageButton("forward", event -> page3());

        this.game.setScene(new Scene(Util.getMainRoot(body, Util.getFooter(backButton, forwardButton))));
    }

    /**
     * Page 3 of the help window.
     */
    private void page3() {
        VBox body = getMainBody(0);

        Text heading1 = new Text("Special Objects");
        heading1.setFill(Color.WHITE);
        heading1.setFont(Util.getMainFont(25));
        body.getChildren().add(heading1);

        Text heading2 = new Text("Levels 2 & 3");
        heading2.setFill(Color.WHITE);
        heading2.setFont(Util.getMainFont(20));
        body.getChildren().add(heading2);

        GridPane list = new GridPane();
        list.setAlignment(Pos.CENTER);
        list.setPadding(new Insets(15));
        list.setHgap(50);
        list.setVgap(20);

        String[] imageNames = {
            "player/004.png",
            "platform-top.png",
            "coin.png",
            "star.png",
        };

        for (int i = 0; i < imageNames.length; i++) {
            ImageView imageView = new ImageView(ResourceLoader.loadImage(imageNames[i]));
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(40);
            list.add(imageView, 0, i);
            Text content = new Text(readText("page3/content-" + (i + 1) +".txt"));
            content.setFill(Color.WHITE);
            content.setWrappingWidth(Constants.SCREEN_WIDTH - 200);
            content.setFont(Util.getMainFont(12));
            list.add(content, 1, i);
        }
        body.getChildren().add(list);

        ImageButton backButton = Util.getMainImageButton("back", event -> page2());

        ImageButton forwardButton = Util.getMainImageButton("forward", event -> page4());

        this.game.setScene(new Scene(Util.getMainRoot(body, Util.getFooter(backButton, forwardButton))));
    }

    /**
     * Page 4 of the help window.
     */
    private void page4() {
        VBox body = getMainBody(15);

        Text heading1 = new Text("Controls");
        heading1.setFill(Color.WHITE);
        heading1.setFont(Util.getMainFont(25));
        body.getChildren().add(heading1);

        GridPane list = new GridPane();
        list.setAlignment(Pos.CENTER);
        list.setPadding(new Insets(15));
        list.setHgap(40);
        list.setVgap(20);

        Text heading2 = new Text("Level 1");
        heading2.setFill(Color.WHITE);
        heading2.setFont(Util.getMainFont(20));
        list.add(new StackPane() {{
            getChildren().add(heading2);
            setMinWidth(super.getWidth());
        }}, 0, 0, 2, 1);

        Text enter = new Text("ENTER / LEFT-CLICK");
        enter.setFill(Color.WHITE);
        enter.setFont(Util.getMainFont(17));
        list.add(new StackPane() {{
            getChildren().add(enter);
            setMinWidth(super.getWidth());
        }}, 0, 1);
        Text content = new Text(readText("page4/content-1.txt"));
        content.setFill(Color.WHITE);
        content.setWrappingWidth(Constants.SCREEN_WIDTH - 300);
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 1);

        Text heading3 = new Text("Levels 2 & 3");
        heading3.setFill(Color.WHITE);
        heading3.setFont(Util.getMainFont(20));
        list.add(new StackPane() {{
            getChildren().add(heading3);
            setMinWidth(super.getWidth());
        }}, 0, 2, 2, 1);

        ImageView keysImage = new ImageView(ResourceLoader.loadImage("help/keys.png"));
        keysImage.setPreserveRatio(true);
        keysImage.setFitHeight(40);
        list.add(new StackPane() {{
            getChildren().add(keysImage);
            setMinWidth(super.getWidth());
        }}, 0, 3);

        content = new Text(readText("page4/content-2.txt"));
        content.setFill(Color.WHITE);
        content.setWrappingWidth(Constants.SCREEN_WIDTH - 300);
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 3);

        Text space = new Text("SPACE / ESC");
        space.setFill(Color.WHITE);
        space.setFont(Util.getMainFont(17));
        list.add(new StackPane() {{
            getChildren().add(space);
            setMinWidth(super.getWidth());
        }}, 0, 4);
        content = new Text(readText("page4/content-3.txt"));
        content.setFill(Color.WHITE);
        content.setWrappingWidth(Constants.SCREEN_WIDTH - 300);
        content.setFont(Util.getMainFont(15));
        list.add(content, 1, 4);

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
