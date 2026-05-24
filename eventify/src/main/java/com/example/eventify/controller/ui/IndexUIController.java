package com.example.eventify.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexUIController {

    @GetMapping({"/", "/ui"})
    public String showIndex() {
        return "index/index";
    }
}
