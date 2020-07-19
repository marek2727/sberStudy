package com.example.demo.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService service;

    @Test
    public void readTest() throws Exception {

        when(service.readAll()).thenReturn(Arrays.asList(
                new Client(1, "Mark", "Akopyan", "27.11"),
                new Client(2, "Igor", "Leontev", "14.02")
        ));

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1,2)))
                .andExpect(jsonPath("$[*].lastName", containsInAnyOrder("Akopyan","Leontev")));

        verify(service, times(1)).readAll();

    }

    @Test
    public void readByIdTest() throws Exception {

        when(service.read(anyInt())).thenReturn(
                new Client(1, "Mark", "Akopyan", "27.11")
        );

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.lastName", equalTo("Akopyan")));

        verify(service, times(1)).read(anyInt());

    }

    @Test
    public void createClientTest() throws Exception {

        Client client = new Client(1, "Mark", "Akopyan", "02.09");

        when(service.create(Mockito.any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Mark"))
                .andExpect(jsonPath("$.lastName").value("Akopyan"))
                .andExpect(jsonPath("$.dateBirthday").value("02.09"));

        verify(service, times(1)).create(Mockito.any(Client.class));

    }

    @Test
    public void updateClientTest() throws Exception {

        when(service.update(Mockito.any(Client.class), anyInt())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/clients/{id}", 1)
                .content(objectMapper.writeValueAsString(new Client(1, "Mark", "Akopyan", "29.09")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).update(Mockito.any(Client.class), eq(1));

    }


    @Test
    public void delete_book_OK() throws Exception {

        when(service.delete(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/clients/{id}", 1) )
                .andExpect(status().isOk());

        verify(service, times(1)).delete(1);
    }

}