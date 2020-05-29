/*
 * Задача:
 * Разработать приложение сканер TCP-портов.
 * Необходимо реализовать:
 *      1. Сканирование методом установления соединения
 *      2. Список хостов и портов может быть указан в виде:
 *          а. диапазона
 *          b. перечисления
 *          с. в смешанном виде.
 *      3. Результат сканирования должен сохраняться в файл в формате JSON
 *      для возможности дальнейшего анализа другими программами.
 *      4. Сканирование портов и хостов должно вестись в случайном порядке.
 *      5. Многопоточное сканирование для различных хостов.
 *      6. Возможность указания максимального количества потоков.
 */

package com.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static Logger logger = LogManager.getLogger();
    private static Scanner scanner = new Scanner();

    public static void main(String[] args) {

        logger.info("Программа начала своё выполнение!");

        Analysis analysis = new Analysis();

        // Получение количества потоков
        int countThreads = analysis.analysisArguments(args);

        if (countThreads == 0){
            return;
        }

        // Создание фиксированного пула потоков

        ExecutorService es = Executors.newFixedThreadPool(countThreads);

        scanner.random();

        // Запуск указанных потоков на исполнение

        for (int i = 0; i < Scanner.getSizeHosts(); i++){

            es.submit(new Threads(i));

        }

        // Упорядоченное завершение работы, при котором ранее отправленные задачи выполняются, а новые задачи не принимаются

        es.shutdown();

        // Блокировка до тех пор, пока все задачи не завершат выполнение после запроса на завершение работы

        try {
            es.awaitTermination(24L, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scanner.serialization();

        logger.info("Программа закончила свою работу.");

    }
}