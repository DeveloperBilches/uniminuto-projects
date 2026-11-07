package com.lavamotos.lavamotos_api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.lavamotos.lavamotos_api.exception.ManejadorErrores;
import com.lavamotos.lavamotos_api.service.LavadoService;

@WebMvcTest(LavadoController.class)
@Import({LavadoService.class, ManejadorErrores.class})
public class LavadoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /api/lavados con datos completos responde 201")
    void registrarLavado() throws Exception {
        mockMvc.perform(post("/api/lavados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"placa\":\"abc12d\",\"cilindrada\":125,\"tipo\":\"BASICO\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.placa").value("ABC12D"))
                .andExpect(jsonPath("$.valor").value(15000.0));
    }

    @Test
    @DisplayName("POST /api/lavados sin placa debe responder 400, no 500")
    void registrarSinPlaca() throws Exception {
        mockMvc.perform(post("/api/lavados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cilindrada\":125,\"tipo\":\"BASICO\"}"))
                .andExpect(status().isBadRequest());
    }
}
