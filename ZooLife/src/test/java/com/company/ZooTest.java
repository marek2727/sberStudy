package com.company;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import java.util.List;

public class ZooTest {

    private static Zoo zoo = new Zoo();

    private AnimalType carnivore = AnimalType.CARNIVORE;
    private AnimalType herbivore = AnimalType.HERBIVORE;

    @BeforeAll
    static void setup() {

        zoo = new Zoo();
        String filePath = ZooTest.class.getClassLoader().getResource("zooAnimalsTest.json").getPath();
        zoo.addAnimals(filePath);

    }

    @Test
    public void testAddAnimals(){

        Actions actions = new Actions(zoo);

        actions.feeding(carnivore);
        actions.feeding(herbivore);

        List<Animal> testAnimals = new ArrayList<Animal>();

        Carnivore lion = new Carnivore();
        lion.setName("Lion");
        lion.setAmount(4);

        Herbivore zebra = new Herbivore();
        zebra.setName("Zebra");
        zebra.setAmount(3);

        testAnimals.add(lion);
        testAnimals.add(zebra);

        assertEquals(testAnimals, zoo.getZooAnimals() );

    }

    @Test
    void performActionForType() {

        zoo.startActions(Events.VISITER_COME, carnivore);
        assertEquals(AnimalState.NOISE, Zoo.getCarnivoreState());

        zoo.startActions(Events.VISITER_COME, herbivore);
        assertEquals(AnimalState.NOISE, Zoo.getHerbivoreState());
        assertEquals(AnimalState.NOISE, Zoo.getCarnivoreState());

        zoo.startActions(Events.FEEDING, carnivore);
        assertEquals(AnimalState.CALM, Zoo.getCarnivoreState());

        zoo.startActions(Events.FEEDING, herbivore);
        assertEquals(AnimalState.CALM, Zoo.getHerbivoreState());
    }

    @Test
    void performAction() {

        if(Zoo.getCarnivoreState().equals(AnimalState.SLEEP) &&
                Zoo.getHerbivoreState().equals(AnimalState.SLEEP)) {

            zoo.startActions(Events.NIGHT);
            assertEquals(AnimalState.SLEEP, Zoo.getCarnivoreState());
            assertEquals(AnimalState.SLEEP, Zoo.getHerbivoreState());

            zoo.startActions(Events.MORNING);
            assertEquals(AnimalState.CALM, Zoo.getCarnivoreState());
            assertEquals(AnimalState.CALM, Zoo.getHerbivoreState());

            zoo.startActions(Events.THUNDER);
            assertEquals(AnimalState.NOISE, Zoo.getCarnivoreState());
            assertEquals(AnimalState.NOISE, Zoo.getHerbivoreState());
        }

        if(Zoo.getCarnivoreState().equals(AnimalState.NOISE) ||
                Zoo.getHerbivoreState().equals(AnimalState.NOISE)) {

            zoo.startActions(Events.NIGHT);
            assertNotEquals(AnimalState.SLEEP, Zoo.getCarnivoreState());
            assertNotEquals(AnimalState.SLEEP, Zoo.getHerbivoreState());

            zoo.startActions(Events.THUNDER);
            assertEquals(AnimalState.NOISE, Zoo.getCarnivoreState());
            assertEquals(AnimalState.NOISE, Zoo.getHerbivoreState());
        }
    }


}
