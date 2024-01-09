package br.com.actinvestimentos.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidNameException  extends ResponseStatusException {

    public InvalidNameException(){
        super(HttpStatus.BAD_REQUEST);
    }

    public InvalidNameException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }

}