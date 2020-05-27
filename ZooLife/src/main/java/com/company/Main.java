/**
 * Задача:
 * Разработать программу моделирующую поведение животных в зоопарке.
 * В зоопарке присутствутют как травоядные, так и плотоядные животные.
 * Любое животное может выполнять следующие базовые действия:
 *     Питаться
 *     Спать
 *     Шуметь
 * Необходимо описать следующее поведение:
 *     Когда смотритель зоопарка приходит кормить травоядных - они начинают шуметь.
 *     Когда смотритель зоопарка приходит кормить плотоядных - они начинают шуметь.
 *     Плотоядные не любят, когда кто-то шумит. Если начинают шуметь травоядные, они тоже начинают шуметь.
 *     Поскольку травоядные животные более спокойные - они не начинают шуметь, когда шумит кто-то другой.
 *     Любые животные перестают шуметь, когда их покормят.
 *     Когда наступает ночь - животные ложатся спать, при условии что никто не шумит.
 *     Когда наступает утро - все животные просыпаются.
 *     Когда гремит гром - все животные просыпаются и начинают шуметь
 */

package com.company;

public class Main {

    public static void main(String[] args) {

        String filePath = args[0];

        // Создание зоопарка
        Zoo zoo = new Zoo();

        // Добавление животных в зоопарк
        zoo.addAnimals(filePath);

        // События, которые могут произойти в зоопарке
        Actions actions = new Actions(zoo);

        actions.morning();

        actions.feederVisit(AnimalType.CARNIVORE);

        actions.night();

        actions.thunder();

        actions.feeding(AnimalType.CARNIVORE);

        actions.feeding(AnimalType.HERBIVORE);

        actions.feederVisit(AnimalType.HERBIVORE);

        actions.feeding(AnimalType.CARNIVORE);

        actions.night();

        actions.feeding(AnimalType.HERBIVORE);

        actions.night();

    }
}
