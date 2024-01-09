package br.com.actinvestimentos.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPriorityException extends ResponseStatusException {
    public InvalidPriorityException(){
        super(HttpStatus.BAD_REQUEST);
    }

    public InvalidPriorityException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
