package com.qualtrics.mfa;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by cgagnon on 6/5/17.
 */
@Controller
public class LoginController {


    @Autowired
    MockDB mockDB;

    User loggedInUser;

    @RequestMapping("/tokenInfo")
    @ResponseBody
    public Map<String, Object> home() {
        Map<String, Object> model = new HashMap<String, Object>();
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final int token = gAuth.getTotpPassword("D5MV2SE6N2ESX7LC");
        model.put("token", token);
        return model;
    }

    @RequestMapping("/generateToken")
    @ResponseBody
    public Map<String, Object> home2() {
        Map<String, Object> model = new HashMap<String, Object>();
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        model.put("secret", key.getKey());
        model.put("token", key.getVerificationCode());
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> login(@RequestBody ObjectNode userData) {
        System.out.println("Received login request");
        String username = userData.get("username").asText();
        String password = userData.get("password").asText();
        boolean MFARequired = userData.get("MFARequired").asBoolean();
        boolean MFAEnrolled = userData.get("MFAEnrolled").asBoolean();

        System.out.println(username);
        System.out.println(password);
        System.out.println(MFARequired);
        System.out.println(MFAEnrolled);

        Map<String, String> response = new HashMap<String, String>();

        mockDB.addUser(username, password, MFARequired, MFAEnrolled);
        if (!mockDB.isValidUser(username)) {
            response.put("success", "false");
            response.put("error", "Invalid user");
            response.put("step", "error");
            return response;
        }

        if (mockDB.requiresMFA(username)) {
            loggedInUser = mockDB.getUser(username);
            if (mockDB.enrolledInMFA(username)) {
                response.put("success", "true");
                response.put("step", "doMFA");
                return response;

            } else {
                response.put("success", "true");
                response.put("step", "doMFAEnrollment");
                return response;
            }
        }

        return response;
    }
}