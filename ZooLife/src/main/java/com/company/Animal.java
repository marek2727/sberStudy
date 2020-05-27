package com.company;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс, описывающий животное
 */
@Getter
@Setter
public abstract class Animal {

    /**
     * Имя животного
     */
    protected String name;

    /**
     * Численность определённого животного
     */
    protected int amount;

    /**
     * Состояние животных определённого типа
     */
    protected AnimalState state = AnimalState.CALM;

    /**
     * Определяет поведение при событии "Наступление утра"
     */
    public abstract void morning();

    /**
     * Определяет поведение при событии "Наступление ночи"
     */
    public abstract void night();

    /**
     * Определяет поведение при событии "Удар грома"
     */
    public abstract void thunder();

    /**
     * Определяет поведение при событии "Приход смотрителя"
     */
    public abstract void visiterCome();

    /**
     * Определяет поведение при событии "Кормление животных"
     */
    public abstract void feeding();

    /**
     * Определяет поведение при событии "Обновление состояния"
     */
    public abstract void updateState();

    /**
     * Определяет поведение животного в зависимости от конкретного события
     * @param events событие, влияющее на животных
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
                visiterCome();
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
