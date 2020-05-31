package com.company;

/**
 * Класс, проверяющий число или строку на палиндромом
 */
public class Palindrom {

    /**
     * Метод для проверки числа на палиндром
     * @param num число на проверку
     */
    public boolean palindromNumber(int num){

        // Проверка числа на палиндром
        int proverka = num;
        System.out.println("Число на проверку: " + num);
        int ostatok;
        int result = 0;

        while (num > 0) {

            ostatok = num % 10;

            result = result * 10 + ostatok;

            num /= 10;

        }

        return proverka == result;

    }

    /**
     * Метод для проверки строки на палиндром
     * @param str число на проверку
     */
    public boolean palindromString(String str){

        // Проверка слова на палиндром
        System.out.println("Строка на проверку: " + str);
        str = str.trim().toLowerCase();

        // Способ 1
        /*StringBuilder c = new StringBuilder(str).reverse();

        if (str.equals(c.toString())) {
            System.out.println(true);
        } else{
            System.out.println(false);
        }*/

        // Способ 2
        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) != str.charAt(str.length() - 1 - i)){

                return false;

            }

            if ( i == str.length() - 1){
                return true;
            }

        }

        return false;

    }

}
