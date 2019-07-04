package com.company;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        DetectLanguageClient detectLanguageClient = new DetectLanguageClient();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Введите текст:");
            String string = sc.nextLine();
            System.out.println("Язык введенного текста:" + detectLanguageClient.detectLanguage(string));
        }
    }
}
