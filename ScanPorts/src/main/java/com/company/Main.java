package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Analysis analysis = new Analysis();

        analysis.analysisArguments(args);

        // Создание фиксированного пула потоков(5)

        ExecutorService es = Executors.newFixedThreadPool(5);

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

        Scanner scanner = new Scanner();

        scanner.serialization();

    }
}