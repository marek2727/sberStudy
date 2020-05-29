package com.company;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HerbivoreTest {

    private static Herbivore herbivore;

    @BeforeAll
    static void setup() {

        herbivore = new Herbivore();
        Zoo zoo = new Zoo();
        String filePath = ZooTest.class.getClassLoader().getResource("zooAnimalsTest.json").getPath();
        zoo.addAnimals(filePath);

    }

    @Test
    public void testMorning(){

        Zoo.setHerbivoreState(AnimalState.SLEEP);

        herbivore.morning();

        assertEquals(AnimalState.CALM, Zoo.getHerbivoreState());

        Zoo.setHerbivoreState(AnimalState.NOISE);

        herbivore.morning();

        assertNotEquals(AnimalState.CALM, Zoo.getHerbivoreState());

    }

    @Test
    public void testVisitorCome(){

        herbivore.visitorCome();

        assertEquals(AnimalState.NOISE, Zoo.getHerbivoreState());

    }

    @Test
    public void testNight(){

        Zoo.setHerbivoreState(AnimalState.CALM);
        Zoo.setCarnivoreState(AnimalState.SLEEP);

        herbivore.night();

        assertEquals(AnimalState.SLEEP, Zoo.getHerbivoreState());

        Zoo.setHerbivoreState(AnimalState.CALM);
        Zoo.setCarnivoreState(AnimalState.NOISE);

        herbivore.night();

        assertNotEquals(AnimalState.SLEEP, Zoo.getHerbivoreState());

    }

    @Test
    public void testThunder(){

        herbivore.thunder();

        assertEquals(AnimalState.NOISE, Zoo.getHerbivoreState());

    }

    @Test
    public void testFeeding(){

        herbivore.feeding();

        assertEquals(AnimalState.CALM, Zoo.getHerbivoreState());

    }

}
