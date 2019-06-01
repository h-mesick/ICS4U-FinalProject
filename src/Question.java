import java.lang.IllegalArgumentException;

import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

/**
 * @version 1
 * @author Evan Zhang
 * Revision history:
 *  - May 18, 2019: Created ~Evan Zhang
 *  - May 27, 2019: Finished ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 *  - May 31, 2019: Updated ~Evan Zhang
 */
public class Question {
    /** Instance variables */
    private String question;
    private String[] answers;
    private EventHandler[] handlers;

    /**
     * Constructor
     * @param  question The question
     * @param  answers  The possible answer choices
     * @param  handlers The handler for each answer choice
     */
    public Question(String question, String[] answers, EventHandler[] handlers) {
        if (answers.length != handlers.length) {
            throw new IllegalArgumentException("Length of answers array and length of handlers array must be the same.");
        }
        this.question = question;
        this.answers = answers;
        this.handlers = handlers;
    }

    /**
     * Gets the question
     * @return The question
     */
    public String getQuestion() {
        return this.question;
    }

    /**
     * Gets the answers
     * @return The answers
     */
    public String[] getAnswers() {
        return this.answers;
    }

    /**
     * Gets the handlers
     * @return The handlers
     */
    public EventHandler[] getHandlers() {
        return this.handlers;
    }

    public int size() {
        return this.answers.length;
    }

    public StackPane getFormattedQuestion() {
        String question = this.question + "\n";
        for (int i = 0; i < this.answers.length; i++) {
            question += (char)(i + 'A') + ". " + this.answers[i] + "\n";
        }
        StackPane questionPane = new StackPane();
        questionPane.setAlignment(Pos.CENTER);
        Text questionText = new Text(question);
        questionText.setFont(Util.getMainFont(18));
        questionText.setFill(Color.WHITE);
        questionText.setWrappingWidth(Constants.SCREEN_WIDTH - 250);
        questionPane.getChildren().add(questionText);
        return questionPane;
    }

    public GridPane getFormattedChoices(int numRows) {
        GridPane answersPane = new GridPane();
        answersPane.setAlignment(Pos.CENTER);
        answersPane.setHgap(10);
        answersPane.setVgap(10);
        for (int i = 0; i < this.answers.length; i++) {
            StackPane button = Util.getMainButton("" + (char)(i + 'A'), this.handlers[i], 15);
            if (this.handlers[i] == null) {
                button.setDisable(true);
            }
            answersPane.add(button, i / numRows, i % numRows);
        }
        return answersPane;
    }

    public GridPane getFormattedChoices() {
        return getFormattedChoices(2);
    }
}
