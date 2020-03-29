package com.company;

public class Main {

    public static void main(String[] args) {

        // проверка на наличие входных данных

        if (args.length < 1) {
            System.out.println("Ошибка! Необходимо ввести входные данные!");
            return;
        }

        Analysis analysis = new Analysis();

        analysis.analysisParameter(args);

    }
}