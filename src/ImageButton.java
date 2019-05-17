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

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 16, 2019: Created ~Evan Zhang
 */
public class ImageButton extends Button {
    private ImageView image;
    public ImageButton() {
        image = new ImageView();
        this.getChildren().add(image);
        this.setPadding(new Insets(0));
    }

    public void setImages(Image normal, Image hover, Image selected) {
        image.setImage(normal);
        image.setSmooth(true);

        this.setOnMousePressed(event -> image.setImage(selected));
        this.setOnMouseReleased(event -> image.setImage(normal));
        this.setOnMouseEntered(event -> image.setImage(hover));
        this.setOnMouseExited(event -> image.setImage(normal));

        super.getStylesheets().add(ResourceLoader.loadCSS("transparent-button.css"));
        super.setGraphic(image);
    }

    public void setImages(Image normal, Image hover) {
        setImages(normal, hover, hover);
    }

    public void setImages(Image normal) {
        setImages(normal, normal, normal);
    }

    public void setFitWidth(double width) {
        super.setMinWidth(width);
        image.setFitWidth(width);
    }

    public void setFitHeight(double height) {
        super.setMinHeight(height);
        image.setFitHeight(height);
    }
}
