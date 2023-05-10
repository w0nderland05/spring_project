package org.study.commons.validators;

import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

public class PostNotFoundException extends CommonException {
    private static String message;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("messages.validation");
        message = bundle.getString("Validation.Post.NotFound");
    }
    public PostNotFoundException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}

