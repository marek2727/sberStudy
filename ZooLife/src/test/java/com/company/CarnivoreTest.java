package com.company;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarnivoreTest {

    private static Carnivore carnivore;

    @BeforeAll
    static void setup() {

        carnivore = new Carnivore();
        Zoo zoo = new Zoo();
        String filePath = ZooTest.class.getClassLoader().getResource("zooAnimalsTest.json").getPath();
        zoo.addAnimals(filePath);

    }

    @Test
    public void testMorning(){

        Zoo.setCarnivoreState(AnimalState.SLEEP);

        carnivore.morning();

        assertEquals(AnimalState.CALM, Zoo.getCarnivoreState());

        Zoo.setCarnivoreState(AnimalState.NOISE);

        carnivore.morning();

        assertNotEquals(AnimalState.CALM, Zoo.getCarnivoreState());

    }

    @Test
    public void testVisiterCome(){

        carnivore.visiterCome();

        assertEquals(AnimalState.NOISE, Zoo.getCarnivoreState());

    }

    @Test
    public void testNight(){

        Zoo.setHerbivoreState(AnimalState.SLEEP);
        Zoo.setCarnivoreState(AnimalState.CALM);

        carnivore.night();

        assertEquals(AnimalState.SLEEP, Zoo.getCarnivoreState());

        Zoo.setHerbivoreState(AnimalState.NOISE);
        Zoo.setCarnivoreState(AnimalState.CALM);

        carnivore.night();

        assertNotEquals(AnimalState.SLEEP, Zoo.getCarnivoreState());

    }

    @Test
    public void testThunder(){

        carnivore.thunder();

        assertEquals(AnimalState.NOISE, Zoo.getCarnivoreState());

    }

    @Test
    public void testFeeding(){

        carnivore.feeding();

        assertEquals(AnimalState.CALM, Zoo.getCarnivoreState());

    }

}
