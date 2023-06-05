package org.study.commons.validators;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

public class BoardNotFoundException extends CommonException {

    private static String message;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("messages.validation");
        message = bundle.getString("Validation.board.NotFound");

        }
    public BoardNotFoundException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
