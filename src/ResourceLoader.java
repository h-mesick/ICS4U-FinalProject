import java.io.*;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class ResourceLoader
{
    public static InputStream getResource(String location) {
        return ResourceLoader.class.getClassLoader().getResourceAsStream(location);
    }

    public static Image loadImage(String filename) {
        return new Image(getResource("resources/images/" + filename));
    }

    public static Reader loadLevel(String filename) {
        try {
            return new InputStreamReader(getResource("resources/levels/" + filename), "UTF-8");
        } catch(UnsupportedEncodingException e) {
            System.err.println("Cannot decode level file: \"" + filename + "\".");
        }
        return null;
    }
}
