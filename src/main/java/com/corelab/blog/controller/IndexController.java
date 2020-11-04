package com.corelab.blog.controller;


import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {


    @GetMapping(value = {"", "/"})
    public String home() {
        return "index";
    }

    @GetMapping(value = "/user")
    public @ResponseBody
    String user() {
        return "user";
    }

    @GetMapping(value = "/admin")
    public @ResponseBody
    String admin() {
        return "admin";
    }

    @GetMapping(value = "/manager")
    public @ResponseBody
    String manager() {
        return "manager";
    }
}
