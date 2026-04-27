package com.exemplo.obra.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCalcularConcreto() throws Exception {
        String json = """
                {
                  "alturaViga": 0.30,
                  "arestas": [
                    {
                      "id": "a1",
                      "verticeOrigem": "V1",
                      "verticeDestino": "V2",
                      "comprimento": 5.0,
                      "espessura": 0.15,
                      "alturaParede": 2.8,
                      "possuiPorta": false,
                      "alturaPorta": 0.0,
                      "larguraPorta": 0.0,
                      "possuiJanela": false,
                      "alturaJanela": 0.0,
                      "larguraJanela": 0.0
                    }
                  ]
                }
                """;

        mockMvc.perform(post("/api/materiais/concreto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.volumeTotal").value(0.2250))
                .andExpect(jsonPath("$.quantidadeArestasProcessadas").value(1));
    }
}
