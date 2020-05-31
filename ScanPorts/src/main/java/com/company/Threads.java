package com.company;

/**
 * Class, that describes behavior of threads
 */
public class Threads extends Thread {

    private int numberHost;

    /**
     * Constructor
     * @param numberHost number of host
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
