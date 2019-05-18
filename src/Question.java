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
 */
public class Question {
    private String question;
    private String[] answers;
    private EventHandler[] handlers;
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

    public String getQuestion() {
        return this.question;
    }

    public String[] getAnswers() {
        return this.answers;
    }

    public EventHandler[] getHandlers() {
        return this.handlers;
    }
}
