package org.study.commons.messageBundle;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageBundle {


    public static String getMessage(String code){
        ResourceBundle bundle = ResourceBundle.getBundle("messages/validation");

        return bundle.getString(code);
    }


}
