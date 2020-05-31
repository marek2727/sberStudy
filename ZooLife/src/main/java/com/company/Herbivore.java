package com.company;

/**
 * Class describes behavior of herbivore animals
 */
public class Herbivore extends Animal {

    /**
     * Method that update state of animals
     */
    @Override
    public void updateState() {
        state = Zoo.getHerbivoreState();
    }

    /**
     * Method describes behavior herbivore animals an event "Morning onset"
     */
    @Override
    public void morning(){

        if (Zoo.getHerbivoreState().equals(AnimalState.SLEEP) || Zoo.getHerbivoreState().equals(AnimalState.CALM) ){

            state = AnimalState.CALM;

            Zoo.setHerbivoreState(state);

        }else{
            System.out.println("Кто-то из животных шумит!");
        }

    }

    /**
     * Method describes behavior herbivore animals an event "Nightfall"
     */
    @Override
    public void night() {

        if (Zoo.getCarnivoreState().equals(AnimalState.CALM) && Zoo.getHerbivoreState().equals(AnimalState.CALM)
                || Zoo.getCarnivoreState().equals(AnimalState.SLEEP) && Zoo.getHerbivoreState().equals(AnimalState.SLEEP)
                || Zoo.getCarnivoreState().equals(AnimalState.SLEEP) && Zoo.getHerbivoreState().equals(AnimalState.CALM)
                || Zoo.getCarnivoreState().equals(AnimalState.CALM) && Zoo.getHerbivoreState().equals(AnimalState.SLEEP)){

            state = AnimalState.SLEEP;

            Zoo.setHerbivoreState(state);

        }else{
            System.out.println("Кто-то из животных шумит!");
        }

    }

    /**
     * Method describes behavior herbivore animals an event "Thunderbolt"
     */
    @Override
    public void thunder() {

        state = AnimalState.NOISE;

        Zoo.setHerbivoreState(state);

    }

    /**
     * Method describes behavior herbivore animals an event "Visitor come"
     */
    @Override
    public void visitorCome() {

        state = AnimalState.NOISE;

        Zoo.setHerbivoreState(state);

        Animal animal = new Carnivore();

        animal.changeState(Events.VISITER_COME);

    }

    /**
     * Method describes behavior herbivore animals an event "Feed animals"
     */
    @Override
    public void feeding(){

        state = AnimalState.CALM;

        Zoo.setHerbivoreState(state);

    }

}
