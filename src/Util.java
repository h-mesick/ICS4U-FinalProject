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
 */
public abstract class Util {
    public static VBox getTitle() {
        VBox title = new VBox();
        title.setAlignment(Pos.CENTER);
        title.getChildren().add(new ImageView(ResourceLoader.loadImage("game-logo.png")));
        return title;
    }

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

    public static BorderPane getFooter(Node left, Node right) {
        return getFooter(left, null, right);
    }

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

    public static BorderPane getMainRoot(Node body) {
        return getMainRoot(body, null);
    }

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

    public static StackPane getMainButton(String text, EventHandler onClick) {
        return getMainButton(text, onClick, 30);
    }

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

    public static void fade(Node object, double duration, double from, double to, EventHandler onFinished) {
        FadeTransition ft = new FadeTransition(Duration.seconds(duration), object);
        ft.setFromValue(from);
        ft.setToValue(to);
        if (onFinished != null) {
            ft.setOnFinished(onFinished);
        }
        ft.play();
    }

    public static void fade(Node object, double duration, double from, double to) {
        fade(object, duration, from, to, null);
    }

    public static Font getMainFont(double size) {
        return Font.loadFont(ResourceLoader.loadFont("nvscript_sc.ttf"), size + 4);
    }

    public static Font getDefaultFont(double size) {
        return Font.font("Verdana", size);
    }

}
