package com.company;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;


public class Scanner {

    private Logger logger = LogManager.getLogger();
    private static List<Integer> ports = new ArrayList<>();
    private static List<String> hosts = new ArrayList<>();
    private static List<Result> resultOfScan = new ArrayList<>();

    public static int getSizeHosts(){
        return hosts.size();
    }

    /*
    // Метод, применяющийся при указании и перечня, и диапазона портов
    */

    public void mainPorts(String[] enumPorts){

        // 80-82, 631, 888-889, 5667

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

        for (int i = k; i < enumPorts.length; i++)
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

        Collections.shuffle(ports);
    }

    /*
    // Перегруженный метод, принимающий перечень портов, и заполняющий массив портов
    */

    public void scanPorts(String[] enumPorts) {
        for (String enumPort : enumPorts)
            ports.add(Integer.parseInt(enumPort));

        Collections.shuffle(ports);
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

        Collections.shuffle(hosts);
    }

    /*
    // Перегруженный метод, принимающий диапазон хостов, и заполняющий массив хостов
    */

    public void scanHosts(String minHost, String maxHost) {

        //127.0.0.1 - 5 - пример
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
        // 127.0.0.
        for (int i = a; i <= b; i++) {

            String h = stringBuilder.toString() + i;
            hosts.add(h);
        }

        Collections.shuffle(hosts);

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

    public void startScan(int numberHost) {

        InetAddress ia = null;
        try {
            // Возвращаем IP в виде объекта класса InetAddress
            ia = InetAddress.getByName(hosts.get(numberHost));
        } catch (UnknownHostException e) {
            logger.error("Не удалось распознать хост.");
        }

        logger.info("Начато сканирование хоста: " + ia);

        for (int j = 0; j < ports.size(); j++){

            Integer port = ports.get(j);

            try {
                // Создает адрес сокета из IP-адреса и номера порта.
                InetSocketAddress isa = new InetSocketAddress(ia, port);
                Socket socket = new Socket();

                // Производится попытка соединения
                int timeout = 100;
                socket.connect(isa, timeout);
                logger.info("Подключение к порту прошло успешно: " + port);

                // При успешном соединении, в массив добавляется открытый порт

                Result result = new Result();

                result.setHost(ia);
                result.setOpenPort(port);

                resultOfScan.add(result);

                socket.close();

            } catch (IOException ioe) {
                logger.error("Не удалось подключиться к порту: " + port);
            }

        }

        logger.info("Сканирование завершено.");

    }

    public void serialization(){

        logger.info("Начат процесс сериализации.");

        // Записываем в переменную результаты сканирования в формате JSON

        String json = new Gson().toJson(resultOfScan);
        json = String.valueOf((new StringBuilder(json).insert(0, "{\"resultOfScan\":")));
        json = json.concat("}");

        // Cоздаём файл и записываем в него результаты

        GregorianCalendar gc = new GregorianCalendar();
        try(FileWriter writer = new FileWriter("jsonResultOfScan" + gc.get(Calendar.DATE) + "-"
                + gc.get(Calendar.HOUR) + gc.get(Calendar.MINUTE) + ".json", true))
        {
            // Запись строки в файл
            writer.write(json);
            // Очистка буфера
            writer.flush();
            logger.info("Запись в файл прошла успешно.");
        }
        catch(IOException ex){
            logger.info("Ошибка при записи результатов сканирования в файл.");
        }
        logger.info("Программа закончила свою работу.");
    }
}

    /*public void startScan() {

        // Производим перемешивание массива
        Collections.shuffle(hosts);
        Collections.shuffle(ports);

        int k = 0;
        int i = 0;

        while (k <= hosts.size() - 1) {
            //System.out.println(new Thread().currentThread());

            InetAddress ia = null;

            try {
                // Возвращаем IP в виде объекта класса InetAddress
                ia = InetAddress.getByName(hosts.get(k));
            } catch (UnknownHostException e) {
                logger.error("Не удалось распознать хост.");
            }

            if ( i == 0) {
                logger.info("Начато сканирование хоста: " + ia);
            }

            Integer port = ports.get(i);

            try {
                // Создает адрес сокета из IP-адреса и номера порта.
                InetSocketAddress isa = new InetSocketAddress(ia, port);
                Socket socket = new Socket();

                // Производится попытка соединения
                int timeout = 100;
                socket.connect(isa, timeout);
                logger.info("Подключение к порту прошло успешно: " + port);

                // При успешном соединении, в массив добавляется открытый порт

                Result result = new Result();

                result.setHost(ia);
                result.setOpenPort(port);

                resultOfScan.add(result);

                socket.close();

            } catch (IOException ioe) {
                logger.error("Не удалось подключиться к порту: " + port);
            }

            if (port == ports.get(ports.size() - 1)){
                // Переход на следующий хост
                k++;
                // Счётчик портов обнуляем
                i = -1;
                // Перемешиваем массив
                Collections.shuffle(ports);
            }
            i++;
        }
        logger.info("Сканирование завершено.");
        serialization();
    }*/