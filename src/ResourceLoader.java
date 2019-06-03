import java.io.*;
import java.util.TreeMap;

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

/**
 * Revision history:
 *  - May 15, 2019: Created ~Evan Zhang
 *  - May 16, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Updated ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 *  - May 31, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 *  - Jun 2, 2019: Updated ~Evan Zhang
 * @author Evan Zhang
 * @version 1
 */
public class ResourceLoader
{
    /** Static variables */
    private static TreeMap<String, Image> imageCache = new TreeMap<String, Image>();

    /**
     * Get the resource from the location
     * @param  location The file location
     * @return          The input stream containing the contents of the file at the specified location
     */
    public static InputStream getResource(String location) {
        return ResourceLoader.class.getClassLoader().getResourceAsStream(location);
    }

    /**
     * Get the location of the resource in the Jar file
     * @param  location The relative location
     * @return          The absolute location of the file
     */
    public static String getResourceLocation(String location) {
        return ResourceLoader.class.getClassLoader().getResource(location).toExternalForm();
    }

    /**
     * Loads the image specified by the filename, caching if possible
     * @param  filename The image filename
     * @return          The image specified by the filename
     */
    public static Image loadImage(String filename) {
        Image ret = imageCache.get(filename);
        if (ret == null) {
            ret = new Image(getResource("resources/images/" + filename));
            imageCache.put(filename, ret);
        }
        return ret;
    }

    /**
     * Loads the game level specified by the filename
     * @param  filename The level filename
     * @return          An InputStreamReader specified by the filename
     */
    public static Reader loadLevel(String filename) {
        try {
            return new InputStreamReader(getResource("resources/levels/" + filename), "UTF-8");
        } catch(UnsupportedEncodingException e) {
            System.err.println("Cannot decode level file: \"" + filename + "\".");
        }
        return null;
    }

    /**
     * Loads the CSS specified by the filename
     * @param  filename The CSS filename
     * @return          The absolute location of the CSS file
     */
    public static String loadCSS(String filename) {
        return getResourceLocation("resources/css/" + filename);
    }

    /**
     * Loads the font file as a stream
     * @param  filename The font file to load
     * @return          The stream containing the font file
     */
    public static InputStream loadFont(String filename) {
        return getResource("resources/fonts/" + filename);
    }
}
