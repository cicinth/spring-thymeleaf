package com.aula04.banco.banco.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void cadastraClienteComSucesso() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"nome\":\"cinrthaiqcardoso.com\",\n" +
                                "\t\"email\":\"cinrhiaqteste@hotmail.com\",\n" +
                                "\t\"senha\":\"432423\",\n" +
                                "\t\"cpf\":\"22222222222\",\n" +
                                "\t\"agencia\":323\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.email").value(true))
                .andExpect(jsonPath("$.active").doesNotExist());
    }
}
