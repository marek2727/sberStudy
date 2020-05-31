package com.company;

/**
 * Программа, демонстрирующая преобразования одних контейнеров в другие
 */

public class Main {

    public static void main(String[] args) {

        ConvertCollections convertCollections = new ConvertCollections();

        convertCollections.setToArray();

        System.out.println();

        convertCollections.arrayToSet();

        System.out.println();

        convertCollections.mapToList();

    }
}
