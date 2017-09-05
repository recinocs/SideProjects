package com.chrisrecinos.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author - Christopher Recinos
 */

@Controller
@RequestMapping(value = "/")
public class StartController {

    @RequestMapping(method = RequestMethod.GET)
    String getStartPage() {
        return "index";
    }
}
