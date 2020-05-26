package com.company;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ActionsTest {

    private static Actions actions;

    private AnimalType carnivore = AnimalType.CARNIVORE;
    private AnimalType herbivore = AnimalType.HERBIVORE;

    @BeforeAll
    static void setup() {

        Zoo zoo = new Zoo();
        String filePath = ZooTest.class.getClassLoader().getResource("zooAnimalsTest.json").getPath();
        zoo.addAnimals(filePath);
        actions = new Actions();

    }

    @Test
    void testfeederVisit() {

        actions.feederVisit(herbivore);
        assertEquals(Zoo.getHerbivoreState(), AnimalState.NOISE);
        assertEquals(Zoo.getCarnivoreState(), AnimalState.NOISE);

        actions.feederVisit(carnivore);
        assertEquals(Zoo.getCarnivoreState(), AnimalState.NOISE);

    }

    @Test
    void testFeeding() {

        actions.feeding(carnivore);
        assertEquals(Zoo.getCarnivoreState(), AnimalState.CALM);
        actions.feeding(herbivore);
        assertEquals(Zoo.getHerbivoreState(), AnimalState.CALM);

    }

    @Test
    void testNight() {

        Zoo.setHerbivoreState(AnimalState.CALM);
        Zoo.setCarnivoreState(AnimalState.CALM);
        actions.night();
        assertEquals(Zoo.getHerbivoreState(), AnimalState.SLEEP);
        assertEquals(Zoo.getCarnivoreState(), AnimalState.SLEEP);

        Zoo.setHerbivoreState(AnimalState.CALM);
        Zoo.setCarnivoreState(AnimalState.NOISE);
        actions.night();
        assertNotEquals(Zoo.getHerbivoreState(), AnimalState.SLEEP);
        assertNotEquals(Zoo.getCarnivoreState(), AnimalState.SLEEP);

    }

    @Test
    void testMorning() {

        Zoo.setHerbivoreState(AnimalState.SLEEP);
        Zoo.setCarnivoreState(AnimalState.SLEEP);
        actions.morning();
        assertEquals(Zoo.getHerbivoreState(), AnimalState.CALM);
        assertEquals(Zoo.getCarnivoreState(), AnimalState.CALM);

        Zoo.setHerbivoreState(AnimalState.NOISE);
        Zoo.setCarnivoreState(AnimalState.CALM);
        actions.morning();
        assertNotEquals(Zoo.getHerbivoreState(), AnimalState.CALM);
        assertEquals(Zoo.getCarnivoreState(), AnimalState.CALM);

    }

    @Test
    void testThunder() {

        actions.thunder();
        assertEquals(Zoo.getHerbivoreState(), AnimalState.NOISE);
        assertEquals(Zoo.getCarnivoreState(), AnimalState.NOISE);

    }

}
