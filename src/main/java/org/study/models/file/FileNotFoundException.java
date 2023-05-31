package org.study.models.file;

import org.springframework.http.HttpStatus;
import org.study.commons.validators.CommonException;

import java.util.ResourceBundle;

public class FileNotFoundException extends CommonException {
    private static ResourceBundle bundle = ResourceBundle.getBundle("messages.errors");

    public FileNotFoundException() {
        super(bundle.getString("errors.file.notFound"), HttpStatus.BAD_REQUEST);
    }
}
