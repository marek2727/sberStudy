package com.company;

public class Arr {

    void removeElement(int[] arr){
        int k = 0;

        for (int i = 0; i < arr.length ; i++) {
            if (arr[i] != 7) {
                arr[k] = arr[i];
                k++;
            }
        }

        for (int i = k; i < arr.length; i++) {
            arr[i] = 0;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }

    }
}
