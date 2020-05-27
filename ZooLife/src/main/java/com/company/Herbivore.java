package com.company;

/**
 * Класс, описывающий поведение травоядных животных
 */
public class Herbivore extends Animal {

    /**
     * Метод, обновляющий состояние животных
     */
    @Override
    public void updateState() {
        state = Zoo.getHerbivoreState();
    }

    /**
     * Метод, описывающий поведение травоядных животных при событии "Наступило утро"
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
     * Метод, описывающий поведение травоядных животных при событии "Наступила ночь"
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
     * Метод, описывающий поведение травоядных животных при событии "Удар грома"
     */
    @Override
    public void thunder() {

        state = AnimalState.NOISE;

        Zoo.setHerbivoreState(state);

    }

    /**
     * Метод, описывающий поведение хищных животных при событии "Приход смотрителя"
     */
    @Override
    public void visiterCome() {

        state = AnimalState.NOISE;

        Zoo.setHerbivoreState(state);

        Animal animal = new Carnivore();

        animal.changeState(Events.VISITER_COME);

    }

    /**
     * Метод, описывающий поведение хищных животных при событии "Кормление животных"
     */
    @Override
    public void feeding(){

        state = AnimalState.CALM;

        Zoo.setHerbivoreState(state);

    }

}
