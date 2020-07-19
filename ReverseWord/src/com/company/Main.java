package com.company;

/*
 * Программа должна получить строку, состоящую из нескольких слов. Необходимо реверснуть порядок слов в строке.
 */

public class Main {

    public static void main(String[] args) {
	    String s = "Hello Igor Voytenko";

        System.out.println("Исходная строка: " + s);
	    ReverseWords rev = new ReverseWords();
        rev.reverse(s);
    }
}

