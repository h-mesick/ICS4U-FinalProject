/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * The tutorial class that offers a tutorial for every level
 */

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The class for creating tutorial objects.
 * <pre>
 * Revision history:
 *  - June 4, 2019: Created ~Max Li
 *  - June 5, 2019: Updated ~Max Li
 *  - June 6, 2019: Finished ~Max Li
 * </pre>
 *
 * @author Max Li
 * @version 1
 */
public class Tutorial {
    /**
     * Instance variables
     */
    protected BaseLevel baseLevel;
    protected int level;
    private List<StackPane> boxes;
    private List<Arrow> arrows;
    private int curBoxIdx, curArrowIdx;
    private StackPane curBox;
    private Arrow curArrow;
    private boolean[] hasArrow;
    private boolean arrowPresent;

    /**
     * Constructor for the tutorial class.
     * Reads in the separate tutorial files for each level and make the corresponding tutorial dialogues.
     *
     * @param baseLevel The level in which components are being added to
     * @param level     The level on which the tutorial is running
     */
    public Tutorial(BaseLevel baseLevel, int level) {
        this.baseLevel = baseLevel;
        this.level = level;
        curBoxIdx = 0;
        curBox = dialogBox("Welcome to Level " + level + " !\n\nClick to begin...", 20, 300, 200, (Constants.SCREEN_WIDTH - 300) / 2, (Constants.SCREEN_HEIGHT - 200) / 2);
        curArrowIdx = 0;
        curArrow = null;
        baseLevel.root.getChildren().add(curBox);
        boxes = new ArrayList<>();
        arrows = new ArrayList<>();
        List<String> lines = Util.readLines(ResourceLoader.loadTutorial("level-" + level + ".txt"));
        hasArrow = new boolean[lines.size()];
        for (int j = 0; j < lines.size(); j++) {
            String[] tokens = lines.get(j).split(" ");
            int i = 0;
            if (tokens[0].equals("?")) {
                arrows.add(new Arrow(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
                hasArrow[j] = true;
                i = 5;
            }
            String temp = tokens[i];
            for (i += 1; i < tokens.length - 2; i++) {
                temp += " " + tokens[i];
            }
            boxes.add(contentDialogBox(temp, Integer.parseInt(tokens[tokens.length - 2]), Integer.parseInt(tokens[tokens.length - 1])));
        }
    }

    /**
     * Sets the dialog to the next dialog after the user clicks on the current one.
     */
    public void nextDialog() {
        if (curBoxIdx < boxes.size()) {
            if (hasArrow[curBoxIdx]) {
                if (arrowPresent)
                    baseLevel.root.getChildren().remove(curArrow);
                arrowPresent = true;
                curArrow = arrows.get(curArrowIdx);
                baseLevel.root.getChildren().add(curArrow);
                curArrowIdx++;
            } else if (!hasArrow[curBoxIdx] && arrowPresent) {
                arrowPresent = false;
                baseLevel.root.getChildren().remove(curArrow);
            }
            baseLevel.root.getChildren().remove(curBox);
            curBox = boxes.get(curBoxIdx);
            baseLevel.root.getChildren().add(curBox);
            curBoxIdx++;
        } else {
            baseLevel.root.getChildren().remove(curBox);
            baseLevel.root.setOnMouseClicked(null);
            if (level == 1) {
                LevelOne temp = (LevelOne) baseLevel;
                baseLevel.root.setOnMouseClicked(e -> temp.nextDialog());
            }
        }
    }

    /**
     * Creates a tutorial dialog box.
     *
     * @param content  Content of the dialog box
     * @param fontSize Size of the font in the box
     * @param width    Width of the box
     * @param height   Height of the box
     * @param x        Translates x by this amount
     * @param y        Translates y by this amount
     * @return The dialog box, made using a StackPane
     */
    public StackPane dialogBox(String content, int fontSize, int width, int height, int x, int y) {
        StackPane box = new StackPane();
        box.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        box.setMinWidth(width);
        box.setMinHeight(height);
        box.setTranslateX(x);
        box.setTranslateY(y);
        box.setPadding(new Insets(10));

        Text text = new Text();
        text.setFont(Util.getMainFont(fontSize));
        text.setWrappingWidth(width / 1.25);
        text.setText(content);
        text.setFill(Color.WHITE);

        box.getChildren().add(text);
        return box;
    }

    /**
     * Creates a tutorial dialog box for content.
     *
     * @param content Content of the dialog box
     * @param x       Translates x by this amount
     * @param y       Translates y by this amount
     * @return The dialog box, made using a StackPane
     */
    public StackPane contentDialogBox(String content, int x, int y) {
        return dialogBox(content, 15, 200, 100, x, y);
    }

    /**
     * Local class to make arrow objects.
     */
    public static class Arrow extends Path {
        /**
         * Constructor of the Arrow class.
         *
         * @param sx Starting x coordinate
         * @param sy Starting y coordinate
         * @param ex Ending x coordinate
         * @param ey Ending y coordinate
         */
        public Arrow(double sx, double sy, double ex, double ey) {
            super();
            strokeProperty().bind(fillProperty());
            setFill(Color.BLACK);
            double angle = Math.atan2((ey - sy), (ex - sx)) - Math.PI / 2.0;
            double sin = Math.sin(angle);
            double cos = Math.cos(angle);
            getElements().add(new MoveTo(sx, sy));
            getElements().add(new LineTo(ex, ey));
            getElements().add(new LineTo((-1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * 10 + ex, (-1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * 10 + ey));
            getElements().add(new LineTo((1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * 10 + ex, (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * 10 + ey));
            getElements().add(new LineTo(ex, ey));
        }
    }
}
