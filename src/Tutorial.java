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
import javafx.scene.text.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The class for creating tutorial objects.
 * <pre>
 * Revision history:
 *  - June 4, 2019: Created ~Max Li
 * </pre>
 *
 * @author Max Li
 * @version 1
 */
public class Tutorial {
    // TODO: change the design of the boxes, add arrows, implement level 3 (basically same as level 2), implement level 1, javadoc
    protected BaseLevel baseLevel;
    protected int level;
    private List<StackPane> boxes;
    private int curIdx;
    private StackPane curBox;

    public Tutorial(BaseLevel baseLevel, int level) {
        this.baseLevel = baseLevel;
        this.level = level;
        curIdx = 0;
        if (level == 2 || level == 3)
            curBox = dialogBox("Welcome to Level " + level + " !\n\nClick to begin...", 20, 300, 200, (Constants.SCREEN_WIDTH - 300) / 2, (Constants.SCREEN_HEIGHT - 200) / 2);
        baseLevel.root.getChildren().add(curBox);
        boxes = new ArrayList<>();
        List<String> lines = Util.readLines(ResourceLoader.loadTutorial("level-" + level + ".txt"));
        for (String s : lines) {
            String[] tokens = s.split(" ");
            String temp = tokens[0];
            for (int i = 1; i < tokens.length - 2; i++) {
                temp += " " + tokens[i];
            }
            boxes.add(contentDialogBox(temp, Integer.parseInt(tokens[tokens.length - 2]), Integer.parseInt(tokens[tokens.length - 1])));
        }
    }

    public void nextDialog() {
        if (curIdx < boxes.size()) {
            baseLevel.root.getChildren().remove(curBox);
            curBox = boxes.get(curIdx);
            baseLevel.root.getChildren().add(curBox);
            curIdx++;
        } else {
            baseLevel.root.getChildren().remove(curBox);
            baseLevel.root.setOnMouseClicked(null);
        }
    }

    public StackPane dialogBox(String content, int fontSize, int width, int height, int x, int y) {
        StackPane box = new StackPane();
        box.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        box.setMinWidth(width);
        box.setMinHeight(height);
        box.setTranslateX(x);
        box.setTranslateY(y);

        Text text = new Text();
        text.setFont(Util.getMainFont(fontSize));
        text.setWrappingWidth(width / 1.25);
        text.setText(content);
        text.setFill(Color.WHITE);

        box.getChildren().add(text);
        return box;
    }

    public StackPane contentDialogBox(String content, int x, int y) {
        return dialogBox(content, 15, 200, 100, x, y);
    }
}
