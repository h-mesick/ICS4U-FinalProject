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
 *  - May 13, 2019: Created ~Evan Zhang
 *  - May 16, 2019: Updated ~Evan Zhang
 *  - May 17, 2019: Updated ~Evan Zhang
 *  - May 22, 2019: Updated ~Evan Zhang
 *  - May 29, 2019: Updated ~Evan Zhang
 *  - May 30, 2019: Updated ~Evan Zhang
 */
public abstract class BaseScene implements Constants {
    protected Game game;

    public BaseScene() {}

    public BaseScene(Game game) {
        this.game = game;
    }

    public abstract void initScene();

    public VBox getTitle() {
        VBox title = new VBox();
        title.setAlignment(Pos.CENTER);
        title.getChildren().add(new ImageView(ResourceLoader.loadImage("game-logo.png")));
        return title;
    }

    public BorderPane getFooter(Node left, Node center, Node right) {
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

    public BorderPane getFooter(Node left, Node right) {
        return getFooter(left, null, right);
    }

    public BorderPane getMainRoot(Node body, Node footer) {
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

    public BorderPane getMainRoot(Node body) {
        return getMainRoot(body, null);
    }

    public StackPane getMainButton(String text, EventHandler onClick, int fontSize) {
        ImageButton mainButton = new ImageButton();
        mainButton.setImages(ResourceLoader.loadImage("button.png"),
                             ResourceLoader.loadImage("button-hover.png"),
                             ResourceLoader.loadImage("button-selected.png"));
        mainButton.setFitWidth(SCREEN_WIDTH / 5 * 2);
        mainButton.setFitHeight(SCREEN_HEIGHT / 8);
        mainButton.setOnAction(onClick);

        Text t = new Text(text);
        t.setTranslateY(5);
        t.setFont(Font.font("Verdana", fontSize));
        t.setFill(Color.RED);
        t.setMouseTransparent(true);

        StackPane button = new StackPane();
        button.setAlignment(Pos.CENTER);
        button.getChildren().add(mainButton);
        button.getChildren().add(t);

        return button;
    }

    public StackPane getMainButton(String text, EventHandler onClick) {
        return getMainButton(text, onClick, 30);
    }

    public ImageButton getMainImageButton(String baseFilename, EventHandler onClick) {
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

    public Image overlayImage(Image bottom, Image top) {
        WritableImage ret = new WritableImage(bottom.getPixelReader(), (int)bottom.getWidth(), (int)top.getHeight());
        PixelReader topPixelReader = top.getPixelReader();
        for (int i = 0; i < top.getWidth(); i++) {
            for (int j = 0; j < top.getHeight(); j++) {
                if (topPixelReader.getColor(i, j).getOpacity() > 1 - 1e-7) {
                    ret.getPixelWriter().setColor(i, j, topPixelReader.getColor(i, j));
                }
            }
        }
        return (Image)ret;
    }

    public void fade(Node object, double duration, double from, double to, EventHandler onFinished) {
        FadeTransition ft = new FadeTransition(Duration.seconds(duration), object);
        ft.setFromValue(from);
        ft.setToValue(to);
        if (onFinished != null) {
            ft.setOnFinished(onFinished);
        }
        ft.play();
    }

    public void fade(Node object, double duration, double from, double to) {
        fade(object, duration, from, to, null);
    }

    public void onExit() {}
    public void onEnter() {}
}
