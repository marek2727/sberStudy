package com.company;

/**
 * Класс, определяющий поведение потоков
 */
public class Threads extends Thread {

    private int numberHost;

    /**
     * Конструктор, принимающий номер хоста по счёту
     * @param numberHost
     */
    Threads(int numberHost){

        this.numberHost = numberHost;
    }

    @Override
    public void run() {

        Scanner s = new Scanner();
        s.startScan(this.numberHost);

    }

}
