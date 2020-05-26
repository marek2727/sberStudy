package com.company;

import lombok.Getter;

import java.util.List;

/**
 * Класс, для сериализации JSON файла
 */
@Getter
public class AnimalsData {

    /**
     * Список, содержащий в себе информацию о хищных животных
     */
    private List<Carnivore> carnivore;

    /**
     * Список, содержащий в себе информацию о травоядных животных
     */
    private List<Herbivore> herbivore;

}
