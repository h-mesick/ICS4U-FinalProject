import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.*;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 31, 2019: Created ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 */
public abstract class Util {
    /**
     * Get the default title
     * @return The default title as a VBox
     */
    public static VBox getTitle() {
        VBox title = new VBox();
        title.setAlignment(Pos.CENTER);
        title.getChildren().add(new ImageView(ResourceLoader.loadImage("game-logo.png")));
        return title;
    }

    /**
     * Get the default footer
     * @param  left   The left side of the footer
     * @param  center The center of the footer
     * @param  right  The right side of the footer
     * @return        The footer as a BorderPane
     */
    public static BorderPane getFooter(Node left, Node center, Node right) {
        BorderPane footer = new BorderPane();
        footer.setPadding(new Insets(10));

        if (left != null) {
            footer.setLeft(left);
        }
        if (center != null) {
            footer.setCenter(center);
        }
        if (right != null) {
            footer.setRight(right);
        }
        return footer;
    }

    /**
     * Get the default footer without the center element
     * @param  left  The left side of the footer
     * @param  right The right side of the footer
     * @return       The footer as a BorderPane
     */
    public static BorderPane getFooter(Node left, Node right) {
        return getFooter(left, null, right);
    }

    /**
     * Gets the main scene
     * @param  body   The body (center) of the scene
     * @param  footer The footer of the scene
     * @return        The main scene as a BorderPane
     */
    public static BorderPane getMainRoot(Node body, Node footer) {
        BorderPane root = new BorderPane();

        root.setBackground(new Background(new BackgroundImage(
            ResourceLoader.loadImage("background.png"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false, false, false, false)
        )));

        root.setTop(getTitle());
        root.setCenter(body);
        root.setBottom(footer);
        return root;
    }

    /**
     * Gets the main scene without a null footer
     * @param  body The body (center) of the scene
     * @return      The main scene as a BorderPane
     */
    public static BorderPane getMainRoot(Node body) {
        return getMainRoot(body, null);
    }

    /**
     * Gets the main image button with text overlayed
     * @param  text     The text to overlay with
     * @param  onClick  The handler for when the button is clicked
     * @param  fontSize The font size of the overlay'd text
     * @return          The image button as a StackPane
     */
    public static StackPane getMainButton(String text, EventHandler onClick, int fontSize) {
        ImageButton mainButton = new ImageButton();
        mainButton.setImages(ResourceLoader.loadImage("button.png"),
                             ResourceLoader.loadImage("button-hover.png"),
                             ResourceLoader.loadImage("button-selected.png"));
        mainButton.setFitWidth(Constants.SCREEN_WIDTH / 5 * 2);
        mainButton.setFitHeight(Constants.SCREEN_HEIGHT / 8);
        mainButton.setOnAction(onClick);

        Text t = new Text(text);
        t.setTranslateY(5);
        t.setFont(Util.getMainFont(fontSize));
        t.setFill(Color.RED);
        t.setMouseTransparent(true);

        StackPane button = new StackPane();
        button.setAlignment(Pos.CENTER);
        button.getChildren().add(mainButton);
        button.getChildren().add(t);

        return button;
    }

    /**
     * Gets the main image button with text overlayed and a default font size of 30
     * @param  text     The text to overlay with
     * @param  onClick  The handler for when the button is clicked
     * @return          The image button as a StackPane
     */
    public static StackPane getMainButton(String text, EventHandler onClick) {
        return getMainButton(text, onClick, 30);
    }

    /**
     * Get the main image button with an image overlayed
     * @param  baseFilename The filename of the overlayed image
     * @param  onClick      The handler for when the button is clicked
     * @return              The iamge button as an ImageButton
     */
    public static ImageButton getMainImageButton(String baseFilename, EventHandler onClick) {
        ImageButton button = new ImageButton();
        button.setFitWidth(50);
        button.setFitHeight(50);
        Image image = ResourceLoader.loadImage("buttonimages/" + baseFilename + ".png");
        button.setImages(overlayImage(ResourceLoader.loadImage("image-button.png"), image),
                         overlayImage(ResourceLoader.loadImage("image-button-hover.png"), image),
                         overlayImage(ResourceLoader.loadImage("image-button-selected.png"), image));
        button.setOnAction(onClick);
        return button;
    }

    /**
     * Overlay two images
     * @param  bottom The bottom image
     * @param  top    The top image
     * @return        The combined image
     */
    public static Image overlayImage(Image bottom, Image top) {
        WritableImage ret = new WritableImage(bottom.getPixelReader(), (int)bottom.getWidth(), (int)top.getHeight());
        PixelReader topPixelReader = top.getPixelReader();
        for (int i = 0; i < top.getWidth(); i++) {
            for (int j = 0; j < top.getHeight(); j++) {
                if (topPixelReader.getColor(i, j).getOpacity() > 1 - Constants.EPS) {
                    ret.getPixelWriter().setColor(i, j, topPixelReader.getColor(i, j));
                }
            }
        }
        return (Image)ret;
    }

    /**
     * Fade the object (transition)
     * @param object     The target object
     * @param duration   The duration of the transitiion
     * @param from       The opacity to begin at
     * @param to         The opacity to end at
     * @param onFinished The handler once the transition is completed
     */
    public static void fade(Node object, double duration, double from, double to, EventHandler onFinished) {
        FadeTransition ft = new FadeTransition(Duration.seconds(duration), object);
        ft.setFromValue(from);
        ft.setToValue(to);
        if (onFinished != null) {
            ft.setOnFinished(onFinished);
        }
        ft.play();
    }

    /**
     * Fade the object (transition) with no handler for when the transition is completed
     * @param object     The target object
     * @param duration   The duration of the transitiion
     * @param from       The opacity to begin at
     * @param to         The opacity to end at
     */
    public static void fade(Node object, double duration, double from, double to) {
        fade(object, duration, from, to, null);
    }

    /**
     * Gets the main font of the program
     * @param  size The font size
     * @return      The specified Font
     */
    public static Font getMainFont(double size) {
        return Font.loadFont(ResourceLoader.loadFont("nvscript_sc.ttf"), size + 4);
    }

    /**
     * Gets the default font of the program
     * @param  size The font size
     * @return      The specified Font
     */
    public static Font getDefaultFont(double size) {
        return Font.font("Verdana", size);
    }

}
