package com.bookStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
