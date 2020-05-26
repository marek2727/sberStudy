package com.company;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    @BeforeAll
    static void setup() {

        Zoo zoo = new Zoo();
        String filePath = ZooTest.class.getClassLoader().getResource("zooAnimalsTest.json").getPath();
        zoo.addAnimals(filePath);

    }

    @Test
    public void testChangeState(){

        for (Animal animal : Zoo.getZooAnimals()){

            animal.changeState(Events.THUNDER);

        }

        assertEquals(AnimalState.NOISE, Zoo.getCarnivoreState());
        assertEquals(AnimalState.NOISE, Zoo.getHerbivoreState());

        for (Animal animal : Zoo.getZooAnimals()){

            animal.changeState(Events.FEEDING);

        }

        assertEquals(AnimalState.CALM, Zoo.getCarnivoreState());
        assertEquals(AnimalState.CALM, Zoo.getHerbivoreState());

        Zoo.setHerbivoreState(AnimalState.SLEEP);
        Zoo.setCarnivoreState(AnimalState.SLEEP);

        for (Animal animal : Zoo.getZooAnimals()){

            animal.changeState(Events.MORNING);

        }

        assertEquals(AnimalState.CALM, Zoo.getCarnivoreState());
        assertEquals(AnimalState.CALM, Zoo.getHerbivoreState());

        for (Animal animal : Zoo.getZooAnimals()){

            animal.changeState(Events.VISITER_COME);

        }

        assertEquals(AnimalState.NOISE, Zoo.getCarnivoreState());
        assertEquals(AnimalState.NOISE, Zoo.getHerbivoreState());

        Zoo.setHerbivoreState(AnimalState.CALM);
        Zoo.setCarnivoreState(AnimalState.CALM);

        for (Animal animal : Zoo.getZooAnimals()){

            animal.changeState(Events.VISITER_COME);

        }

        assertEquals(AnimalState.NOISE, Zoo.getCarnivoreState());
        assertEquals(AnimalState.NOISE, Zoo.getHerbivoreState());

    }

}
