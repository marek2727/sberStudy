package com.company;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Analysis {

    private Logger logger = LogManager.getLogger();
    private Scanner scanner = new Scanner();

    public void analysisArguments(String[] args){

        logger.info("Программа начала своё выполнение!");

        // Создание 2 опций

        Option optionHost = new Option("h", true,"хосты подлежащие сканированию");
        Option optionPort = new Option("p", true,"порты подлежащие сканированию");
        Option optionInfo = new Option("i","info", false,"печать этого сообщения");

        // Установка аргументов у опции(1)
        optionHost.setArgs(1);
        // Имя аргумента
        optionHost.setArgName("hosts");

        optionPort.setArgs(1);
        optionPort.setArgName("ports");

        // Добавление опций в объект Options

        Options options = new Options();
        options.addOption(optionHost);
        options.addOption(optionPort);
        options.addOption(optionInfo);

        // Создание парсера строки

        CommandLineParser cmdLinePosixParser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = cmdLinePosixParser.parse(options, args);
        } catch (ParseException e) {
            logger.error("Возникла ошибка при распознавании входных данных.");

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "info", options );
            return;
        }

        // Проверка на наличие опций

        if (commandLine.hasOption("i") || commandLine.hasOption("info")){
            System.out.println("-h <hosts>   use value for given property \n" + "-p <ports>   use value for given property");
            logger.info("На экран выведена справка по использованию утилиты.");
            logger.info("Программа закончила свою работу.");

            System.exit(0);
        }

        if (commandLine.hasOption("h")) {
            String[] arguments = commandLine.getOptionValues("h");
            analysisHost(arguments[0]);
        }else{
            logger.error("Неправильно введены входные данные.");

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "info", options );

            logger.info("На экран выведена справка по использованию утилиты.");
            logger.info("Программа закончила свою работу.");

            System.exit(0);
        }

        if (commandLine.hasOption("p")) {
            String[] arguments = commandLine.getOptionValues("p");
            analysisPort(arguments[0]);
        }else{
            logger.error("Неправильно введены входные данные.");

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "info", options );

            logger.info("На экран выведена справка по использованию утилиты.");
            logger.info("Программа закончила свою работу.");

            System.exit(0);
        }

        logger.info("Распознавание входных данных прошло успешно.");
        logger.info("Начался процесс сканирования.");

    }


    public void analysisPort(String args){

        /*
        // Вывод на экран хостов, подлежащих сканированию
        */

        logger.info("Сканируемые порты: " + args);

        /*
        // Условие, определяющее, в каком виде указаны порты
        */

        if (args.contains(",") && args.contains("-")) {

            // Случай, если сканировать необходимо и перечень, и диапазон портов

            String[] enumPorts = args.split(",");
            scanner.mainPorts(enumPorts);


        } else if (args.contains("-")) {

            // Случай, если сканировать необходимо только диапазон портов

            String[] ports = args.split("-");
            scanner.scanPorts(Integer.parseInt(ports[0]), Integer.parseInt(ports[1]));

        } else if (args.contains(",")) {

            // Случай, если сканировать необходимо только перечень портов

            String[] ports = args.split(",");
            scanner.scanPorts(ports);

        } else {

            // Случай, если сканировать необходимо только один порт

            scanner.scanPorts(Integer.parseInt(args));

        }

    }

    public void analysisHost(String args) {

        logger.info("Сканируемые хосты: " + args);

        /*
        // Условие, определяющее, в каком виде указаны хосты
        */

        if (args.contains(",") && args.contains("-")) {

            // Случай, если сканировать необходимо и диапазон, и перечень хостов

            String[] rangeHosts = args.split(",");
            scanner.mainHosts(rangeHosts);


        } else if (args.contains("-")) {

            // Случай, если сканировать необходимо диапазон хостов

            String[] rangeHosts = args.split("-");
            scanner.scanHosts(rangeHosts[0], rangeHosts[1]);

        } else if (args.contains(",")) {

            // Случай, если сканировать необходимо перечень хостов

            String[] enumHosts = args.split(",");
            scanner.scanHosts(enumHosts);
        } else {

            // Случай, если сканировать необходимо один хост

            scanner.scanHosts(args);
        }
    }
}