package com.example.mongospring.service.exception;

public class ObjectNotFoundException extends  RuntimeException{
    public ObjectNotFoundException(String msg){
        super(msg);
    }
}
