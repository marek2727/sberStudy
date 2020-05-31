package com.company;

import java.util.*;

public class ConvertCollections {

    /**
     * Метод, преобразующий set в list
     */
    public void setToArray(){

        HashSet<Integer> set = new HashSet<>();
        set.add(5);
        set.add(25);
        set.add(35);
        set.add(15);

        System.out.println("HashSet: " + set);

        ArrayList<Integer> list = new ArrayList<>(set);

        // Преобразование set в list
        // При этом видим, что ничего особенного не происходит, set копируется в list без изменений
        System.out.println("List: " + list);

    }

    /**
     * Метод, преобразующий list в set
     */
    public void arrayToSet(){

        ArrayList<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(3);
        list.add(17);
        list.add(3);

        System.out.println("List: " + list);

        HashSet<Integer> set = new HashSet<>(list);

        // Преобразование set в list
        // При этом видим, что значения в set вставлены с учётом их хэшкода, а повторные элементы удалены
        System.out.println("HashSet: " + set);

    }

    /**
     * Метод, преобразующий значения и ключи map в list
     */
    public void mapToList(){

        Map<String, Integer> map = new HashMap<>();

        map.put("1", 5);
        map.put("2", 2);
        map.put("3", 3);
        map.put("6", 1);
        map.put("4", 2);

        System.out.println(map);

        ArrayList<Integer> listValues = new ArrayList<>(map.values());

        // Преобразование значений map в list
        // При этом видим, что ничего особенного не происходит, значения копируются в list без изменений
        System.out.println(listValues);

        ArrayList<String> listKeys = new ArrayList<>(map.keySet());

        // Преобразование ключей map в list
        // При этом видим, что ничего особенного не происходит, значения копируются в list без изменений
        System.out.println(listKeys);

    }

}
