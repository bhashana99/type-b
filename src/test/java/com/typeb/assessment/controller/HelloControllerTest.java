package com.typeb.assessment.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void validName_returns200WithMessage() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Alice"));
    }

    @Test
    void invalidName_returns400WithError() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "nancy"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    void missingName_returns400WithError() throws Exception {
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    void emptyName_returns400WithError() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    void boundaryM_returns200() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "mike"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Mike"));
    }

    @Test
    void boundaryN_returns400() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "nick"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    void uppercaseName_returns200() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "ALICE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello ALICE"));
    }
}