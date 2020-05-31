package com.company;

import lombok.Getter;

import java.util.List;

/**
 * Class for serialization JSON file
 */
@Getter
public class AnimalsData {

    /**
     * List contains information about carnivore animals
     */
    private List<Carnivore> carnivore;

    /**
     * List contains information about herbivore animals
     */
    private List<Herbivore> herbivore;

}
