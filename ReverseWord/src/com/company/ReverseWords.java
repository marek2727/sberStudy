package com.company;

public class ReverseWords {

    void reverse(String s){
        /*
        // Производим полный реверс строки
        */

        s = new StringBuilder(s).reverse().toString();
        int k = 0;

        /*
        // Алгоритм по реверсу каждой подстроки в отдельности
        */

        System.out.print("Полученная строка: ");
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == ' ') {
                System.out.print(new StringBuilder(s.substring(k, i)).reverse().toString() + " ");
                k = i +1;
            }
            if (i == s.length() - 1)
                System.out.print(new StringBuilder(s.substring(k , i+1)).reverse().toString());

        }
    }
}
