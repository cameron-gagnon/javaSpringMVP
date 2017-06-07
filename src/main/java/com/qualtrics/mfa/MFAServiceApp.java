package com.qualtrics.mfa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Arrays;

/**
 * Created by cgagnon on 6/6/17.
 */

@SpringBootApplication
public class MFAServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(MFAServiceApp.class, args);
    }
}
