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
        if (answers.length != 4) {
            throw new IllegalArgumentException("Answers array must be exactly length 4.");
        }
        if (handlers.length != 4) {
            throw new IllegalArgumentException("Handlers array must be exactly length 4.");
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
}
