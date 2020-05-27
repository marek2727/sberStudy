package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;


/**
 * Класс, представляющий зоопарк
 */
public class Zoo {

    /**
     * Список всех животных в зоопарке
     */
    @Getter
    @Setter
    private List<Animal> zooAnimals = new ArrayList<Animal>();

    /**
     * Поле, показывающее состояние всех животных определённого типа(хищников)
     */
    @Getter
    @Setter
    private static AnimalState carnivoreState;

    /**
     * Поле, показывающее состояние всех животных определённого типа(травоядных)
     */
    @Setter
    @Getter
    private static AnimalState herbivoreState;

    /**
     * Установление значений по-умолчанию
     */
    public Zoo(){

        carnivoreState = AnimalState.CALM;
        herbivoreState = AnimalState.CALM;

    }

    /**
     * Метод, добавляющий животных в зоопарк из JSON файла.
     * @param filePath путь к JSON файлу с информацией о животных
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
     * Метод, реагирующий на произошедшее событие для всех животных
     * @param events само событие
     */
    public void startActions(Events events){

        for (Animal animal : zooAnimals){

            animal.changeState(events);

        }

        updateAllStates();

        printState();

    }


    /**
     * Метод, реагирующий на произошедшее событие для определённого вида животных
     * @param events само событие
     * @param animalType тип животного, для которого произошло событие
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
     * Метод, обновляющий состояние животных
     */
    public void updateAllStates(){

        for (Animal animal : zooAnimals){

            animal.changeState(Events.UPDATE);

        }

    }


    /**
     * Метод для распечатывания состояний всех животных
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
