package com.company;

import java.util.HashSet;

public class ForwardList {
    int data;     // Значение
    ForwardList next;   // Ссылка на следующий элемент

    ForwardList push(ForwardList head_next, int new_data) {
        ForwardList new_list = new ForwardList(); // Создаём новый узел
        new_list.data = new_data; // Присваиваем значение новому узлу
        new_list.next = head_next; // Присваиваем ссылку на предыдущий узел
        head_next = new_list; // Присваиваем head адрес нового узла
        return head_next;
    }

    void printList(ForwardList head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    void findValue(ForwardList head1, ForwardList head2){
        System.out.println("Исходный вид списков: ");
        printList(head1);
        printList(head2);

        ForwardList current1 = head1;
        ForwardList current2 = head2;

        int sizeA = 0;
        int sizeB = 0;

        // пройти список A до конца
        while (current1 != null) {

            // cчётчик размера списка А
            sizeA++;
            // увеличиваем текущий указатель списка A
            current1 = current1.next;
        }

        while (current2 != null) {

            // cчётчик размера списка B
            sizeB++;
            // увеличиваем текущий указатель списка B
            current2 = current2.next;
        }

        // присваеваем начало списка
        current1 = head1;
        current2 = head2;

        // производим сдвиг на необходимое количество узлов(разницу в размерах списков)
        if (sizeA > sizeB){
            for(int i = 0; i < sizeA - sizeB; i++){
                current1 = current1.next;
            }
        }else if(sizeB > sizeA){
            for(int i = 0; i < sizeB - sizeA; i++){
                current2 = current2.next;
            }
        }

        System.out.println("Вид списков после сдвига: ");
        printList(current1);
        printList(current2);

        // поиск одинаковых значений
        while (current1 != null) {
            if (current1.data == current2.data){
                System.out.print("Одинаковые элементы: ");
                System.out.print(current1.data + " \n");
            }
            // переходим на следующий элемент в списках
            current1 = current1.next;
            current2 = current2.next;
        }
    }

    void hash(ForwardList head1, ForwardList head2){
        System.out.println("\nИсходный вид списков: ");
        printList(head1);
        printList(head2);

        HashSet<Integer> set1 = new HashSet<Integer>(); // объявляем hashset

        // заполняем HashSet данными из списка
        while (head1 != null){
            set1.add(head1.data);
            head1 = head1.next;
        }

        System.out.println("Вид HashSet после копирования в него одного из списков: " + set1);

        // проверка совпадений данных второго списка и HashSet
        while (head2 != null){
            if (set1.contains(head2.data)){
                System.out.println("Одинаковые элементы: " + head2.data);
            }
            head2 = head2.next;
        }
    }

    ForwardList pop(ForwardList head){
        System.out.println("Удаляемое значение: " + head.data); // печатаем удаляемое значение
        head = head.next; // начало списка делаем со следующего элемента
        return head;
    }
}
