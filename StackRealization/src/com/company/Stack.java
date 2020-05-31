package com.company;

/**
 * Класс, реализующий Stack
 */
public class Stack<E> {

    // Элементы стека
    private Object[] elementData;
    // Поле, указывающее на вершину стека
    private int top = -1;

    /**
     * Конструктор, создающий стек определённого размера
     * @param size размер стека
     */
    public Stack(int size){

        this.elementData = new Object[size];

    }

    /**
     * Метод, добавляющий элемент в стек
     * @param num добавляемый элемент
     */
    public void add(E num){

        elementData[++top] = num;

    }

    /**
     * Метод, возращающий размер стека
     */
    public int size(){

        return elementData.length;

    }

    /**
     * Метод, проверяющий стек на пустоту
     */
    public boolean isEmpty(){

        return top == -1;

    }

    /**
     * Метод, проверяющий крайний элемент стека без удаления
     */
    public Object peek(){
        return elementData[top];
    }

    /**
     * Метод, проверяющий крайний элемент стека с удалением
     *
     */
    public Object poll() { return elementData[top--]; }

}


