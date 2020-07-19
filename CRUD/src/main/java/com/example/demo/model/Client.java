package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class, that describes the client entity
 */
// Аннотация указывает, что данный класс является сущностью
@Entity
// Аннотация указывает на имя таблицы, которая будет отображаться в этой сущности.
@Table(name = "clients")
// Аннотация необходима из-за ленивой инициализации hibernate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client {

    /**
     * Client ID
     */
    @Id
    // Аннотация для создания генератора последовательности
    @SequenceGenerator(name = "clientsIdSeq", sequenceName = "clients_id_seq", allocationSize = 1)
    // Механизм генерации последовательных значений
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientsIdSeq")
    private int id;

    /**
     * First name of client
     */
    // Аннатоция, связывающая поле класса с названием колонки в таблице
    @Column(name = "first_name")
    private String firstName;

    /**
     * Last name of client
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Date birthday of client
     */
    @Column(name = "date_birthday")
    private String dateBirthday;

    public Client(){

    }

    public Client(int id, String firstName, String lastName, String dateBirthday){

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirthday = dateBirthday;

    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getDateBirthday(){
        return dateBirthday;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setDateBirthday(String dateBirthday){
        this.dateBirthday = dateBirthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(dateBirthday, client.dateBirthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateBirthday);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateBirthday='" + dateBirthday + '\'' +
                '}';
    }
}
