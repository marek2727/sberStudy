package com.company;


/**
 * Класс, содержащий возможные события и дальнейшие последствия
 */
public class Actions {

    /**
     * Ссылка на зоопарк
     */
    private Zoo zoo;

    /**
     * @param zoo ссылка на конкретный зоопарк
     */
    public Actions (Zoo zoo){

        this.zoo = zoo;

    }

    /**
     * Метод, соответствующий событию "Наступление утра"
     */
    public void morning(){

        System.out.println("Наступило утро!");

        zoo.startActions(Events.MORNING);

    }

    /**
     * Метод, соответствующий событию "Приход смотрителя" к определённому виду животных
     * @param animalType тип животного
     */
    public void feederVisit(AnimalType animalType){

        System.out.println("Смотритель пришёл кормить животных: " + animalType);

        zoo.startActions(Events.VISITER_COME, animalType);

    }

    /**
     * Метод, соответствующий событию "Наступление ночи"
     */
    public void night(){

        System.out.println("Наступила ночь!");

        zoo.startActions(Events.NIGHT);

    }

    /**
     * Метод, соответствующий событию "Удар грома"
     */
    public void thunder(){

        System.out.println("Грянул гром!");

        zoo.startActions(Events.THUNDER);

    }

    /**
     * Метод, соответствующий событию "Животных покормили" для определённого типа животных
     * @param animalType тип животного
     */
    public void feeding(AnimalType animalType){

        System.out.println("Животных покормили: " + animalType);

        zoo.startActions(Events.FEEDING, animalType);

    }

}
