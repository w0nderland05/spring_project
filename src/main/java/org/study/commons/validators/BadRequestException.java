package org.study.admin.user;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
