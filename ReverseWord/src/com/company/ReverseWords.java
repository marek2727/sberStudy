package com.company;

public class ReverseWords {

    String reverse(String s){
        /*
        // Производим полный реверс строки
        */
        int j = s.length();

        s = new StringBuilder(s).reverse().toString();
        int k = 0;

        System.out.println(s.length());
        /*
        // Алгоритм по реверсу каждой подстроки в отдельности
        */

        s.replace(s.substring(k,5), new StringBuilder(s.substring(k, 5)).reverse().toString() + "\n");
        System.out.print(s);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' '){
                s.replace(s.substring(k,i), new StringBuilder(s.substring(k, i)).reverse().toString() + "\n");
            }
        }

        System.out.print(s);

        return s;
    }
}
