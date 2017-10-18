package com.chrisrecinos.model.data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;

/**
 * @author - Christopher Recinos
 */

@Controller
public class ExceptionController implements ExceptionController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException.class)
    public String notFoundError() {
        System.out.println("404");
        return "404error";
    }
}
