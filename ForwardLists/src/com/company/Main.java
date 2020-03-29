package com.company;

public class Main {

    public static void main(String[] args) {
        ForwardList list = new ForwardList();

        // Создание двух списков
        ForwardList head1 = null;
        ForwardList head2 = null;

        //  Заполнение списков данными
        head1 = list.push(head1, 6);
        head1 = list.push(head1, 7);
        head1 = list.push(head1, 8);
        head1 = list.push(head1, 9);
        head1 = list.push(head1, 2);
        head1 = list.push(head1, 1);
        head1 = list.push(head1, 17);
        head1 = list.push(head1, 12);
        head1 = list.push(head1, 18);

        head2 = list.push(head2, 6);
        head2 = list.push(head2, 7);
        head2 = list.push(head2, 8);
        head2 = list.push(head2, 4);
        head2 = list.push(head2, 3);
        head2 = list.push(head2, 5);

        list.findValue(head1,head2); // первый метод решения, путём приравнивания размеров списков.
        list.hash(head1,head2); // второй метод решения, путём копирования одного списка в HashSet, а затем проверка
                                // данных второго списка на наличие в HashSet.
        //head1 = list.pop(head1);  // удалить элемент с начала списка
    }
}
