package com.company;

public class Threads extends Thread {

    private int numberHost;

    Threads(int numberHost){

        this.numberHost = numberHost;
    }

    @Override
    public void run() {

        Scanner s = new Scanner();
        s.startScan(this.numberHost);

    }
}
