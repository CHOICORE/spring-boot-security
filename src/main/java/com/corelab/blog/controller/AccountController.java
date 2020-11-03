package com.corelab.blog.controller;


import com.corelab.blog.entity.Account;
import com.corelab.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    AccountService accountService;


    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String accountLoginForm(){
        System.out.println("login form");
        return "/accounts/accountlogin";
    }



    @RequestMapping(value="/signup",method = RequestMethod.GET)
    public String accountSignUpForm() {
        // TODO: 2020-11-03 호출하는 함수 로그 핸들러 만들거나 찾아보기 
        System.out.println(this.getClass().getSimpleName() + " - " + new Object() {
                }.getClass().getEnclosingMethod().getName()
        );
        return "accounts/accountSignUp";
    }

    @RequestMapping(value="/signup",method = RequestMethod.POST)
    public String accountSignUp(Account account) {
        System.out.println(this.getClass().getSimpleName() + " - " + new Object() {
                }.getClass().getEnclosingMethod().getName()
        );

        accountService.signUp(account);

        return "redirect:/";
    }
}
