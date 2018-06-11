package com.chrisrecinos.mynba2kdb.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * @author - Christopher Recinos
 */

@Controller
@RequestMapping(value = ["/"])
class StartController {

    @RequestMapping(method = [RequestMethod.GET])
    fun getIntroPage() : String {
        return "index"
    }
}