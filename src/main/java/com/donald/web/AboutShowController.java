package com.donald.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Donald
 * @data 29/04/2020 21:29
 */
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about(){
        return "AboutMe";
    }
}
