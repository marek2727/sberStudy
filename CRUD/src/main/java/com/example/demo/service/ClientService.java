package com.example.demo.service;

import com.example.demo.model.Client;

import java.util.List;

/**
 * Interface with CRUD-operations
 */
public interface ClientService {

    /**
     * Create a new client
     * @param client body of client for create
     */
    Client create(Client client);

    /**
     * Return the list of all clients
     * @return list of clients
     */
    List<Client> readAll();

    /**
     * Return the clients with specified IF
     * @param id ID client
     * @return body client with specified ID
     */
    Client read(int id);

    /**
     * Update client with specified ID
     * @param client body client
     * @param id id client
     * @return true, if data was updated, else false
     */
    boolean update(Client client, int id);

    /**
     * Delete client with specified ID
     * @param id id client for delete
     * @return true, if client was deleted, else false
     */
    boolean delete(int id);

}
