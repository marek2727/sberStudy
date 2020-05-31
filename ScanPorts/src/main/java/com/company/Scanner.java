package com.company;

import com.google.gson.Gson;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

<<<<<<< HEAD

    public class Scanner {

    private int timeout = 100;
    private List<Integer> ports = new ArrayList<>();
    private List<String> hosts = new ArrayList<>();
    private List<Result> resultOfScan = new ArrayList<>();
    Thread t = new Thread();

    /*
    // Метод, применяющийся при указании и перечня, и диапазона портов
    */
=======
/**
 * Class, that add in the lists hosts and ports for scan and begin scan
 */
@Setter
@Getter
public class Scanner {

    private Logger logger = LogManager.getLogger();

    /**
     * List of ports
     */
    @Setter
    @Getter
    private static List<Integer> ports = new ArrayList<>();

    /**
     * Lists of hosts
     */
    @Setter
    @Getter
    private static List<String> hosts = new ArrayList<>();

    /**
     * Result of scan
     */
    @Setter
    @Getter
    private static List<Pair<InetAddress, Integer>> resultOfScan = new ArrayList<>();

    /**
     * Method, that defines size of list of hosts
     * @return size of list
     */
    public static int getSizeHosts(){
        return hosts.size();
    }
>>>>>>> 67640ac11ed6fdd3dc046fd5deb12795b89e6d13

    /**
     * Method, that used when ports specified in mixed form
     * @param enumPorts ports in mixed form
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

    /**
     * Method, that received range of ports and add this ports in the list
     * @param minPort start of range
     * @param maxPort end of range
     */
    public void scanPorts(Integer minPort, Integer maxPort) {
        for (int i = minPort; i <= maxPort; i++)
            ports.add(i);

    }

    /**
     * Method, that received enum of ports and add this ports in the list
     * @param enumPorts enum ports
     */
    public void scanPorts(String[] enumPorts) {
        for (String enumPort : enumPorts)
            ports.add(Integer.parseInt(enumPort));

    }

    /**
     * Method for random sort the lists
     */
    public void random(){

        Collections.shuffle(ports);
        Collections.shuffle(hosts);

    }

    /**
     * Method, that received port and add this port in the list
     * @param port port
     */
    public void scanPorts(int port) {
        ports.add(port);
    }

    /**
     * Method, that received host and add this host in the list
     * @param host host
     */
    public void scanHosts(String host) {

        hosts.add(host);
    }

    /**
     * Method that received enum of hosts and add this hosts in the list
     * @param getHosts enum of hosts
     */
    public void scanHosts(String[] getHosts) {

        hosts.addAll(Arrays.asList(getHosts));

    }

    /**
     * Method, that received range of hosts and add this hosts in the list
     * @param minHost start of range
     * @param maxHost end of range
     */
    public void scanHosts(String minHost, String maxHost) {

        //127.0.0.1 - 5 - пример
        // Отделяем конец хоста
        String[] last = minHost.split("\\.");

        // Диапазон хостов, где а - минимальный, б - максимальный
        int a = Integer.parseInt(last[last.length - 1]);
        int b = Integer.parseInt(maxHost);

        StringBuilder stringBuilder = new StringBuilder();

        // "соединяем" хост обратно
        for (int i = 0; i < last.length - 1; i++) {

            stringBuilder.append(last[i]).append(".");
        }

        // Заполняем массив хостами в нужном диапазоне
        // 127.0.0.
        for (int i = a; i <= b; i++) {

            String h = stringBuilder.toString() + i;
            hosts.add(h);
        }

    }

    /**
     * Method, that used when hosts specified in mixed form
     * @param enumHosts hosts in mixed form
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

    /**
     * Method, that begin of scan
     * @param numberHost number of host
     */
    public void startScan(int numberHost) {

        logger.info("Начался процесс сканирования.");

<<<<<<< HEAD
        Thread t = Thread.currentThread();

        System.out.println(t);
        // Производим перемешивание массива
        Collections.shuffle(hosts);
        Collections.shuffle(ports);

        int k = 0;
        int i = 0;

        while (k <= hosts.size() - 1) {

            InetAddress ia = null;
=======
        InetAddress ia = null;
        try {
            // Возвращаем IP в виде объекта класса InetAddress
            ia = InetAddress.getByName(hosts.get(numberHost));
        } catch (UnknownHostException e) {
            logger.error("Не удалось распознать хост.");
        }
>>>>>>> 67640ac11ed6fdd3dc046fd5deb12795b89e6d13

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

                Pair<InetAddress, Integer> results = new Pair<>(ia, port);
                resultOfScan.add(results);

                socket.close();

            } catch (IOException ioe) {
                logger.error("Ошибка "+ ioe.getMessage() + " возникла на порту " + port);
            }

        }

        logger.info("Сканирование завершено.");

    }

    /**
     * Method for serialization
     */
    public void serialization(){

        logger.info("Начат процесс сериализации.");

        // Записываем в переменную результаты сканирования в формате JSON

        String json = new Gson().toJson(resultOfScan);

        json = String.valueOf((new StringBuilder(json).insert(0, "{\"resultOfScan\":")));
        json = json.concat("}");

        // Cоздаём файл и записываем в него результаты

        long time = System.currentTimeMillis() / 1000l;
        try(FileWriter writer = new FileWriter("jsonResultOfScan" + time + ".json", true))
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
    }
}