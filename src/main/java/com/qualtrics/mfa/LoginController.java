package com.qualtrics.mfa;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cgagnon on 6/5/17.
 */
@Controller
public class LoginController {

    private static final String test_env_val = System.getenv("TEST_VAL");

    @RequestMapping(value="/")
    public String index(){
        System.out.println("trying to find login.html");
        return "login.html";
    }

    public ResponseEntity<?> showLoginPage(){
        System.out.printf("Hello!");
        printTestVal();
        return new ResponseEntity<>("login.html", HttpStatus.OK);
    }

    public static void printTestVal(){
        System.out.println(test_env_val);
    }
}
