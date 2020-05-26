package com.company;

public class Main {

    public static void main(String[] args) {

        String filePath = args[0];

        // Создание зоопарка
        Zoo zoo = new Zoo();

        // Добавление животных в зоопарк
        zoo.addAnimals(filePath);

        // Различные действия
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
