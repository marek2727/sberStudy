package com.company;

import java.util.ArrayList;

public class Arr {

    void removeElement(int[] arr){
        int k = 0;

        /*
        //Переносим числа != 7 в начало массива
        */

        for (int i = 0; i < arr.length ; i++) {
            if (arr[i] != 7) {
                arr[k] = arr[i];
                k++;
            }
        }

        ArrayList<Integer> arrayList = new ArrayList<>();

        /*
        //Заполняем ArrayList отсортированным массивом
        */

        for (int i = 0; i < arr.length; i++) {
            arrayList.add(new Integer(arr[i]));
        }

        /*
        //Удаляем лишние элементы
        */

        while (arrayList.size() > k){
            arrayList.remove(k);
        }

        /*
        //Уменьшаем размер Capacity до размера Size
        */

        arrayList.trimToSize();
        System.out.println(arrayList);

    }
}
