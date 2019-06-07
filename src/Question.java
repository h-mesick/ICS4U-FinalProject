/**
 * Evan Zhang and Max Li
 * Mrs Krasteva
 * Due: June 10, 2019
 * The Question class for storing questions in the game.
 */
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;

/**
 * The Question class for storing questions in the game.
 * <pre>
 * Revision history:
 *  - May 18, 2019: Created ~Evan Zhang
 *  - May 27, 2019: Finished ~Evan Zhang
 *  - May 27, 2019: Commented ~Evan Zhang
 *  - May 31, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Updated ~Evan Zhang
 *  - Jun 1, 2019: Commented ~Evan Zhang
 *  - Jun 2, 2019: Commented ~Evan Zhang
 *  - Jun 3, 2019: Updated ~Evan Zhang
 *  - Jun 3, 2019: Commented ~Evan Zhang
 *  - Jun 6, 2019: Commented ~Evan Zhang
 *  - Jun 7, 2019: Finished ~Evan Zhang
 * </pre>
 * @author Evan Zhang
 * @version 1
 */
public class Question {
    /** The question */
    private String question;
    /** The choices that the user can choose */
    private String[] answers;
    /** The handlers for each choice */
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

    /**
     * Gets the choices the user has
     * @return The number of choices
     */
    public int size() {
        return this.answers.length;
    }

    /**
     * Formats the question to display to the user
     * @return The formatted question as a StackPane
     */
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

    /**
     * Formats the choices to display to the user
     * @param  numRows The number of rows to format the choices into
     * @return         The formatted choices as a GridPane
     */
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

    /**
     * Formats the choices to display to the user with a default row count of 2
     * @return The formatted choices as a GridPane
     */
    public GridPane getFormattedChoices() {
        return getFormattedChoices(2);
    }
}
