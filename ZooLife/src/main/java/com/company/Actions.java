package com.company;

/**
 * Class contains possible events and their further consequences
 */
public class Actions {

    /**
     * Link to the zoo
     */
    private Zoo zoo;

    /**
     * @param zoo link to the zoo
     */
    public Actions (Zoo zoo){

        this.zoo = zoo;

    }

    /**
     * Method, related to the event "Morning onset"
     */
    public void morning(){

        System.out.println("Наступило утро!");

        zoo.startActions(Events.MORNING);

    }

    /**
     * Method, related to the event "Visitor come" to a certain type of animal
     * @param animalType type of animal
     */
    public void feederVisit(AnimalType animalType){

        System.out.println("Смотритель пришёл кормить животных: " + animalType);

        zoo.startActions(Events.VISITER_COME, animalType);

    }

    /**
     * Method, related to the event "Nightfall"
     */
    public void night(){

        System.out.println("Наступила ночь!");

        zoo.startActions(Events.NIGHT);

    }

    /**
     * Method, related to the event "Thunderbolt"
     */
    public void thunder(){

        System.out.println("Грянул гром!");

        zoo.startActions(Events.THUNDER);

    }

    /**
     * Method, related to the event "Feed animals" to a certain type of animal
     * @param animalType type of animal
     */
    public void feeding(AnimalType animalType){

        System.out.println("Животных покормили: " + animalType);

        zoo.startActions(Events.FEEDING, animalType);

    }

}
