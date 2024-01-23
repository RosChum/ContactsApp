package org.example;

import org.example.appHandler.ConsoleService;
import org.example.appHandler.ContactStore;
import org.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(ConsoleService.class).consoleHandler();

    }
}