package com.nttdata.springboot.productservice.utils.exceptions;

public class NotFoundException extends Exception {

    public NotFoundException(String message){
        super(message);
    }
}
