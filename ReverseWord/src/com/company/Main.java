package com.company;

public class Main {

    public static void main(String[] args) {
	    String s = new String("Hello Anton Putin");

        System.out.println("Исходная строка: " + s);
	    ReverseWords rev = new ReverseWords();
        rev.reverse(s);
    }
}

