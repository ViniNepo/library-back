package com.senac.library.api.controller;

import com.senac.library.api.utils.Utils;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

    @GetMapping()
    public String login() {

        if(Utils.isAuthenticated())
            return "redirect:/";

        return "login";
    }
}
