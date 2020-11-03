package com.corelab.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping(value = {"", "/"})
    public String home() {
        System.out.println(this.getClass().getSimpleName() + " - " + new Object() {
                }.getClass().getEnclosingMethod().getName()
        );
        return "index";
    }

    @GetMapping(value = "/user")
    public @ResponseBody String user() {
        System.out.println(this.getClass().getSimpleName() + " - " + new Object() {
                }.getClass().getEnclosingMethod().getName()
        );
        return "user";
    }

    @GetMapping(value = "/admin")
    public @ResponseBody String admin() {
        System.out.println(this.getClass().getSimpleName() + " - " + new Object() {
                }.getClass().getEnclosingMethod().getName()
        );
        return "admin";
    }

    @GetMapping(value = "/manager")
    public @ResponseBody String manager() {
        System.out.println(this.getClass().getSimpleName() + " - " + new Object() {
                }.getClass().getEnclosingMethod().getName()
        );
        return "manager";
    }
}
