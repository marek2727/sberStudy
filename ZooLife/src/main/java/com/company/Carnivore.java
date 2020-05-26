package com.company;

/**
 * Класс, описывающий поведение хищных животных
 */
public class Carnivore extends Animal {

    /**
     * Метод, описывающий поведение хищных животных при событии "Наступило утро"
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
     * Метод, описывающий поведение хищных животных при событии "Наступила ночь"
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
     * Метод, описывающий поведение хищных животных при событии "Удар грома"
     */
    @Override
    public void thunder() {

        state = AnimalState.NOISE;

        Zoo.setCarnivoreState(state);

    }

    /**
     * Метод, описывающий поведение хищных животных при событии "Приход смотрителя"
     */
    @Override
    public void visiterCome() {

        state = AnimalState.NOISE;

        Zoo.setCarnivoreState(state);

    }

    /**
     * Метод, описывающий поведение хищных животных при событии "Кормление животных"
     */
    @Override
    public void feeding(){

        state = AnimalState.CALM;

        Zoo.setCarnivoreState(state);

    }


}
