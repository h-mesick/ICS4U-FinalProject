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
    public static Image loadImage(String filename) {
        return new Image("file:resources/images/" + filename);
    }

    public static FileReader loadLevel(String filename) {
        try {
            return new FileReader("resources/levels/" + filename);
        } catch(FileNotFoundException e) {
            System.out.println("Could not load level named \"" + filename + "\": file not found.");
        }
        return null;
    }
}
