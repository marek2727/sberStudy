package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;


public class Scanner {

    private int timeout = 100;
    private List<Integer> ports = new ArrayList<>();
    private List<String> hosts = new ArrayList<>();
    private List<Result> resultOfScan = new ArrayList<>();

    /*
    // Метод, применяющийся при указании и перечня, и диапазона портов
    */

    public void mainPorts(String[] enumPorts){

        String[] rangePorts;
        ArrayList<String> rangePort = new ArrayList<>();
        int k = 0;

        /*
        // Цикл, определяющий диапазоны портов(если их несколько), и записывающий
        // их в массив диапазонов, а так же реализующий сдвиг обнаруженных диапазонов в начало
        // массива перечня портов, дабы потом исключить их из повторного включения
        */

        for (int i = 0; i < enumPorts.length; i++) {
            if (enumPorts[i].contains("-")){
                rangePorts = (enumPorts[i].split("-"));
                rangePort.add(rangePorts[0]);
                rangePort.add(rangePorts[1]);
                String a = enumPorts[k];
                enumPorts[k] = enumPorts[i];
                enumPorts[i] = a;
                k++;
            }
        }

        // Цикл, заполняющий массив сканируемых портов из массива введённого перечня портов(исключая найденные диапазоны)

        for (int i = k ; i < enumPorts.length; i++)
            ports.add(Integer.parseInt(enumPorts[i]));

        // Цикл, заполняющий массив сканируемых портов из массива введённого диапазона портов

        for (int i = 0; i < rangePort.size() - 1; i = i + 2) {
            scanPorts(Integer.parseInt(rangePort.get(i)),Integer.parseInt(rangePort.get(i+1)));
        }

    }

    /*
    // Метод, принимающий диапазон портов, и заполняющий массив портов, подлежащиъ сканированию
    */

    public void scanPorts(Integer minPort, Integer maxPort) {
        for (int i = minPort; i <= maxPort; i++)
            ports.add(i);
    }

    /*
    // Перегруженный метод, принимающий перечень портов, и заполняющий массив портов
    */

    public void scanPorts(String[] enumPorts) {
        for (String enumPort : enumPorts)
            ports.add(Integer.parseInt(enumPort));
    }

    /*
    // Перегруженный метод, принимающий один порт, и заполняющий массив портов
    */

    public void scanPorts(int port) {
        ports.add(port);
    }

    /*
    // Метод, принимающий один хост, и заполняющий массив хостов
    */

    public void scanHosts(String host) {

        hosts.add(host);
    }

    /*
    // Перегруженный метод, принимающий перечень хостов, и заполняющий массив хостов
    */

    public void scanHosts(String[] getHosts) {

        hosts.addAll(Arrays.asList(getHosts));
    }

    /*
    // Перегруженный метод, принимающий диапазон хостов, и заполняющий массив хостов
    */

    public void scanHosts(String minHost, String maxHost) {

        // Отделяем конец хоста
        String[] mn = minHost.split("\\.");

        // Диапазон хостов, где а - минимальный, б - максимальный
        int a = Integer.parseInt(mn[mn.length - 1]);
        int b = Integer.parseInt(maxHost);

        StringBuilder stringBuilder = new StringBuilder();

        // "соединяем" хост обратно
        for (int i = 0; i < mn.length - 1; i++) {

            stringBuilder.append(mn[i]).append(".");

        }

        // Заполняем массив хостами в нужном диапазоне
        for (int i = a; i <= b; i++) {

            String h = stringBuilder.toString() + i;
            hosts.add(h);

        }

    }

    /*
    // Метод, применяющийся при указании и перечня, и диапазона хостов
    */

    public void  mainHosts(String[] enumHosts){

        String[] rangeHosts;
        ArrayList<String> rangeHost = new ArrayList<>();
        int k = 0;

        /*
        // Цикл, определяющий диапазоны хостов(если их несколько), и записывающий
        // их в массив диапазонов, а так же реализующий сдвиг обнаруженных диапазонов в начало
        // массива перечня хостов, дабы потом исключить их из повторного включения
        */

        for (int i = 0; i < enumHosts.length; i++) {
            if (enumHosts[i].contains("-")){
                rangeHosts = (enumHosts[i].split("-"));
                rangeHost.add(rangeHosts[0]);
                rangeHost.add(rangeHosts[1]);
                String a = enumHosts[k];
                enumHosts[k] = enumHosts[i];
                enumHosts[i] = a;
                k++;
            }
        }

        // Цикл, заполняющий массив хостов из массива перечня портов(исключая найденные диапазоны)

        for (int i = k ; i < enumHosts.length; i++)
            hosts.add(enumHosts[i]);

        // Цикл, заполняющий массив портов диапазоном портов

        for (int i = 0; i < rangeHost.size() - 1; i = i + 2) {
            scanHosts((rangeHost.get(i)),(rangeHost.get(i+1)));
        }
    }

    /*
    // Метод, производящий сканирование
    */

    public void startScan() {

        // Производим перемешивание массива
        Collections.shuffle(hosts);
        Collections.shuffle(ports);

        int k = 0;
        int i = 0;

        while (k <= hosts.size() - 1) {

            InetAddress ia = null;

            try {
                // Возвращаем IP в виде объекта класса InetAddress
                ia = InetAddress.getByName(hosts.get(k));
            } catch (UnknownHostException e) {
                System.out.println("Ошибка!");
            }

            if ( i == 0) {
                System.out.println("Сканируемый хост: " + hosts.get(k));
                System.out.println("Сканируемые порты: ");
            }

            Integer port = ports.get(i);
            System.out.print(port);

            try {

                InetSocketAddress isa = new InetSocketAddress(ia, port);
                Socket socket = new Socket();

                // Производится попытка соединения
                socket.connect(isa, timeout);
                System.out.println(" открыт");

                // При успешном соединении, в массив добавляется открытый порт

                Result result = new Result();

                result.setHost(ia);
                result.setOpenPort(port);

                resultOfScan.add(result);

                socket.close();

            } catch (IOException ioe) {
                System.out.println("");
            }

            if (port == ports.get(ports.size() - 1)){
                k++;
                i = -1;
                Collections.shuffle(ports);
            }
            i++;
        }

        serialization();
    }

    public void serialization(){

        if (resultOfScan.size() == 0){
            System.out.println("Открытых портов не найдено!");
        }

        ObjectMapper mapper = new ObjectMapper();
        String res = null;

        // Записываем в переменную результаты сканирования в формате JSON
        try {
            res = mapper.writeValueAsString(resultOfScan);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Cоздаём файл и записываем в него результаты
        try(FileWriter writer = new FileWriter("jsonResultOfScan.json", false))
        {
            // Запись строки в файл
            writer.write(res);
            // Очистка буфера
            writer.flush();
        }
        catch(IOException ex){
            System.out.println("Ошибка! Невозможно записать данные в файл!");
        }
    }

}