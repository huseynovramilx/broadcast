package com.rooms.broadcast.User.Exceptions;

import com.rooms.broadcast.User.Exceptions.UserAlreadyExistsException;
import com.rooms.broadcast.User.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class UserAlreadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userAlreadyExistsHandler(UserAlreadyExistsException ex){
        return ex.getMessage();
    }
}
