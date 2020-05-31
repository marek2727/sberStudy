/**
 *
 * Программа, реализующая Stack(LIFO).
 *
 */

package com.company;

public class Main {

    public static void main(String[] args) {

        // Создание Stack
        Stack<String> stack = new Stack<>(5);

        // Добавление элементов в стек
        stack.add("First");
        stack.add("Second");
        stack.add("Third");
        stack.add("Fourth");

        // Проверка стека на пустоту
        System.out.println("Стек пустой? " + stack.isEmpty());

        // Размер стека
        System.out.println("Размер стека: " + stack.size());

        // Проверка крайнего элемента без удаления
        System.out.println("Крайний элемент: " + stack.peek());

        // Проверка крайнего элемента с удалением
        System.out.println("Крайний элемент: " + stack.poll());

        // Проверка крайнего элемента без удаления
        System.out.println("Крайний элемент: " + stack.peek());

        System.out.println();

        // Создание Stack
        Stack<Integer> stackNumbers = new Stack<>(5);

        // Добавление элементов в стек
        stackNumbers.add(1);
        stackNumbers.add(2);
        stackNumbers.add(3);
        stackNumbers.add(4);

        // Проверка стека на пустоту
        System.out.println("Стек пустой? " + stack.isEmpty());

        // Размер стека
        System.out.println("Размер стека: " + stackNumbers.size());

        // Проверка крайнего элемента без удаления
        System.out.println("Крайний элемент: " + stackNumbers.peek());

        // Проверка крайнего элемента с удалением
        System.out.println("Крайний элемент: " + stackNumbers.poll());

        // Проверка крайнего элемента без удаления
        System.out.println("Крайний элемент: " + stackNumbers.peek());

    }
}
