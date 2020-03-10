package com.company;

public class ReverseWords {

    String reverse(String s){

        // разделяем строку s на массив строк(разделитель - пробел)
        String arr[] = s.split(" ");
        s = "";

        //заполняем строку элементами с конца
        for (int i = 0; i < arr.length; i++) {
            s += arr[arr.length-1-i] + " ";
        }

        return s;
    }

}
