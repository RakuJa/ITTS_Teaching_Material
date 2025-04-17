package com.ensat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Homepage controller.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    String index() {
        return "index";
    }

}
