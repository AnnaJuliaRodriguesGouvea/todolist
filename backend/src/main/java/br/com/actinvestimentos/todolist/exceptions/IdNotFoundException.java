package br.com.actinvestimentos.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IdNotFoundException extends ResponseStatusException {
    public IdNotFoundException(){
        super(HttpStatus.NOT_FOUND);
    }

    public IdNotFoundException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
