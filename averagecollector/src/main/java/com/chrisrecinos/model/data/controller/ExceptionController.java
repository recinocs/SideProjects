package com.chrisrecinos.model.data.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author - Christopher Recinos
 */

@Controller
public class ExceptionController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public String notFoundError(HttpServletRequest request) {

        int httpErrorCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        String error;

        switch(httpErrorCode) {
            case 404: {
                error = "404error";
                break;
            }
            default: {
                error = "500error";
            }
        }
        return error;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
