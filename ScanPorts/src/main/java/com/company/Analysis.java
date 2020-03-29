package com.company;

public class Analysis {
    public void analysisParameter(String[] args) {

        Scanner scanner = new Scanner();

        /*
        // Условие, проверяющее, корректно ли указаны входные данные
        */

        if (args[0].equals("scan") && args[1].equals("-h") && args[3].equals("-p")) {

            /*
            // Вывод на экран хостов, подлежащих сканированию
            */

            String host = args[2];
            System.out.println("Сканируемые хост(ы): " + host);

            /*
            // Условие, определяющее, в каком виде указаны порты
            */

            if (args[4].contains(",") && args[4].contains("-")) {

                // Случай, если сканировать необходимо и перечень, и диапазон портов

                String[] enumPorts = args[4].split(",");
                scanner.mainPorts(enumPorts);


            } else if (args[4].contains("-")) {

                // Случай, если сканировать необходимо только диапазон портов

                String[] ports = args[4].split("-");
                try {
                    scanner.scanPorts(Integer.parseInt(ports[0]), Integer.parseInt(ports[1]));
                } catch (NumberFormatException nfe) {
                    System.out.println("Ошибка с портом!");
                    return;
                }
            } else if (args[4].contains(",")) {

                // Случай, если сканировать необходимо только перечень портов

                String[] ports = args[4].split(",");
                scanner.scanPorts(ports);

            } else {

                // Случай, если сканировать необходимо только один порт

                try {
                    int port = Integer.parseInt(args[4]);
                    scanner.scanPorts(port);
                } catch (NumberFormatException nfe) {
                    System.out.println("Ошибка с портом!");
                    return;
                }
            }

        /*
        // Условие, определяющее, в каком виде указаны хосты
        */

            if (args[2].contains(",") && args[2].contains("-")) {

                // Случай, если сканировать необходимо и диапазон, и перечень хостов

                String[] rangeHosts = args[2].split(",");
                scanner.mainHosts(rangeHosts);


            } else if (args[2].contains("-")) {

                // Случай, если сканировать необходимо диапазон хостов

                String[] rangeHosts = args[2].split("-");
                scanner.scanHosts(rangeHosts[0], rangeHosts[1]);

            } else if (args[2].contains(",")) {

                // Случай, если сканировать необходимо перечень хостов

                String[] enumHosts = args[2].split(",");
                scanner.scanHosts(enumHosts);
            } else {

                // Случай, если сканировать необходимо один хост

                String aloneHost = args[2];
                scanner.scanHosts(aloneHost);
            }

            scanner.startScan();

        } else{
            System.out.println("\tНекорректно введены входные данные! Попробуйте ещё раз!\n\t" +
                                "Корректный формат ввода данных: " + "scan -h 127.0.0.1 -p 631");
        }
    }
}