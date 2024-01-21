package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите Ф.И.О.");

       String name =  scanner.nextLine();
        System.out.println("Введите телефон");
       String tel = scanner.nextLine();
        System.out.println("Введите email");
        String email = scanner.nextLine();

        System.out.println("Введено имя " + name + " телефон " + tel + " email " + email);



    }
}