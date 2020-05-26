/**
 *
 * Программа, проверяющая, является число или строка палиндромом.
 *
 */

package com.company;

public class Main {

    public static void main(String[] args) {

        Palindrom palindrom = new Palindrom();

        System.out.println(palindrom.palindromNumber(151));

        System.out.println(palindrom.palindromNumber(155));

        System.out.println(palindrom.palindromString("Топот"));

        System.out.println(palindrom.palindromString("Москва"));

    }
}
