package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;


/**
 * Class describing the zoo
 */
public class Zoo {

    /**
     * List of all animals in the zoo
     */
    @Getter
    @Setter
    private List<Animal> zooAnimals = new ArrayList<Animal>();

    /**
     * Field of state of carnivore animals
     */
    @Getter
    @Setter
    private static AnimalState carnivoreState;

    /**
     * Field of state of herbivore animals
     */
    @Setter
    @Getter
    private static AnimalState herbivoreState;

    /**
     * Setting default values
     */
    public Zoo(){

        carnivoreState = AnimalState.CALM;
        herbivoreState = AnimalState.CALM;

    }

    /**
     * Method that adds animals to the zoo from a JSON file
     * @param filePath path to JSON file with information about an animals
     */
    public void addAnimals(String filePath){

        ObjectMapper mapper = new ObjectMapper();
        File animalsFile = new File(filePath);
        try {
            AnimalsData animalsData = mapper.readValue(animalsFile, AnimalsData.class);
            zooAnimals.addAll(animalsData.getCarnivore());
            zooAnimals.addAll(animalsData.getHerbivore());
        } catch (IOException e) {
            System.out.println(e.toString());
            throw new IllegalStateException("File hasn't been parsed");
        }

    }

    /**
     * Method that responds to an event for all animals
     * @param events event
     */
    public void startActions(Events events){

        for (Animal animal : zooAnimals){

            animal.changeState(events);

        }

        updateAllStates();

        printState();

    }


    /**
     * Method that responds to an event for a specific animal species
     * @param events event
     * @param animalType type of animal
     */
    public void startActions(Events events, AnimalType animalType){

        switch (animalType) {
            case CARNIVORE:
                for(Animal animal : zooAnimals) {
                    if(animal instanceof Carnivore) {
                        animal.changeState(events);
                    }
                }
                break;
            case HERBIVORE:
                for(Animal animal : zooAnimals) {
                    if(animal instanceof Herbivore) {
                        animal.changeState(events);
                    }
                }
                break;
            default:
                System.out.println("No such animal type in the zoo");
        }

        updateAllStates();

        printState();

    }

    /**
     * Method for update of states
     */
    public void updateAllStates(){

        for (Animal animal : zooAnimals){

            animal.changeState(Events.UPDATE);

        }

    }


    /**
     * Method for printing all states of animals
     */
    public void printState(){

        System.out.println("Carnivore state: " + carnivoreState);
        System.out.println("Herbivore state: " + herbivoreState);

        for(Animal animal : zooAnimals){
            System.out.println(animal);
        }

        System.out.println();

    }

}
