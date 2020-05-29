package com.company;

import lombok.Getter;
import lombok.Setter;

/**
 * Class, describing an animal
 */
@Getter
@Setter
public abstract class Animal {

    /**
     * Name of animal
     */
    protected String name;

    /**
     * Amount of animals
     */
    protected int amount;

    /**
     * State of animal a certain type
     */
    protected AnimalState state = AnimalState.CALM;

    /**
     * Defines behavior for event "Morning onset"
     */
    public abstract void morning();

    /**
     * Defines behavior for event "Nightfall"
     */
    public abstract void night();

    /**
     * Defines behavior for event "Thunderbolt"
     */
    public abstract void thunder();

    /**
     * Defines behavior for event "Visitor come"
     */
    public abstract void visitorCome();

    /**
     * Defines behavior for event "Feed animals"
     */
    public abstract void feeding();

    /**
     * Defines behavior for event "Update state"
     */
    public abstract void updateState();

    /**
     * Defines behavior of animal depending on the specific event
     * @param events event, influence the animal
     */
    public void changeState(Events events){

        switch (events){
            case NIGHT:
                night();
                break;
            case MORNING:
                morning();
                break;
            case THUNDER:
                thunder();
                break;
            case VISITER_COME:
                visitorCome();
                break;
            case FEEDING:
                feeding();
                break;
            case UPDATE:
                updateState();
        }

    }

    @Override
    public String toString(){

        return "Name: " + name + ". Amount: " + amount + ". State: " + state;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Animal other = (Animal) obj;
        if (amount != other.amount)
            return false;
        if (!name.equals(other.name))
            return false;
        if (!state.equals(other.state))
            return false;
        return true;
    }

}
