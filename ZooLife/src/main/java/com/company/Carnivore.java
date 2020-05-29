package com.company;

/**
 * Class describes behavior of carnivore animals
 */
public class Carnivore extends Animal {

    /**
     * Method that update state of animals
     */
    @Override
    public void updateState() {
        state = Zoo.getCarnivoreState();
    }

    /**
     * Method describes behavior carnivore animals an event "Morning onset"
     */
    @Override
    public void morning(){

        if (Zoo.getCarnivoreState().equals(AnimalState.SLEEP) || Zoo.getCarnivoreState().equals(AnimalState.CALM) ){

            state = AnimalState.CALM;

            Zoo.setCarnivoreState(state);

        }else{
            System.out.println("Кто-то из животных шумит!");
        }

    }

    /**
     * Method describes behavior carnivore animals an event "Nightfall"
     */
    @Override
    public void night() {

        if (Zoo.getCarnivoreState().equals(AnimalState.CALM) && Zoo.getHerbivoreState().equals(AnimalState.CALM)
                || Zoo.getCarnivoreState().equals(AnimalState.SLEEP) && Zoo.getHerbivoreState().equals(AnimalState.SLEEP)
                || Zoo.getCarnivoreState().equals(AnimalState.SLEEP) && Zoo.getHerbivoreState().equals(AnimalState.CALM)
                || Zoo.getCarnivoreState().equals(AnimalState.CALM) && Zoo.getHerbivoreState().equals(AnimalState.SLEEP)){

            state = AnimalState.SLEEP;

            Zoo.setCarnivoreState(state);

        }else{
            System.out.println("Кто-то из животных шумит!");
        }
    }

    /**
     * Method describes behavior carnivore animals an event "Thunderbolt"
     */
    @Override
    public void thunder() {

        state = AnimalState.NOISE;

        Zoo.setCarnivoreState(state);

    }

    /**
     * Method describes behavior carnivore animals an event "Visitor come"
     */
    @Override
    public void visitorCome() {

        state = AnimalState.NOISE;

        Zoo.setCarnivoreState(state);

    }

    /**
     * Method describes behavior carnivore animals an event "Feed animals"
     */
    @Override
    public void feeding(){

        state = AnimalState.CALM;

        Zoo.setCarnivoreState(state);

    }


}
