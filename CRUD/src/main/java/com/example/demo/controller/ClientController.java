package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is about control of a CRUD-operations
 */
// Эта аннотация говорит спрингу, что данный класс является REST контроллером.
// Т.е. в данном классе будет реализована логика обработки клиентских запросов
@RestController
public class ClientController {

    private final ClientService clientService;
    private Logger logger = LogManager.getLogger();

    /* Эта аннотация говорит, что в этом месте необходимо внедрить зависимость
     В конструктор мы передаем интерфейс ClientService.
     Реализацию данного сервиса мы пометили аннотацией @Service ранее,
     и теперь спринг сможет передать экземпляр этой реализации в конструктор контроллера.
     */
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * This method handles a POST requests
     * @param client request body
     * @return http status
     */
    @PostMapping(value = "/clients")
    // RequestBody - значение этого параметра подставляется из тела запроса
    public ResponseEntity<?> create(@RequestBody Client client) {
        logger.info("Поступил POST запрос.");

        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * This method handles a GET requests for all clients
     * @return http status
     */
    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> read() {

        logger.info("Поступил GET запрос по всем клиентам.");

        final List<Client> clients = clientService.readAll();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles a GET requests for concrete client
     * @param id client if for read
     * @return http status
     */
    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") int id) {

        logger.info("Поступил GET запрос по конкретному клиенту.");

        final Client client = clientService.read(id);

        return client != null
                ? new ResponseEntity<>(client ,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles a PUT requests
     * @param id client id for update
     * @param client request body
     * @return http status
     */
    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Client client) {

        logger.info("Поступил PUT запрос.");

        final boolean updated = clientService.update(client, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * This method handle a DELETE requests
     * @param id client id for delete
     * @return http status
     */
    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {

        logger.info("Поступил DELETE запрос.");

        final boolean deleted = clientService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}