package org.desafio.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.log4j.Log4j2;
import org.desafio.controller.PessoaController;
import org.desafio.model.Pessoa;
import org.desafio.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

@Log4j2
public class PessoaControllerTest {
    private MockMvc mockMvc;
    @Mock
    private PessoaService pessoaService;
    @InjectMocks
    private PessoaController pessoaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
    }

    @Test
    public void testCreatePessoa() throws Exception {
        Pessoa pessoa = new Pessoa();

        when(pessoaService.createPessoa(pessoa)).thenReturn(pessoa);
        mockMvc.perform(post("/pessoas/create")
                        .contentType("application/json")
                        .content("{\"nome\":\"test\",\"cpf\":\"12345678901\",\"dataNascimento\":\"2025-01-08\",\"email\":\"test@example.com\",\"telefone\":\"1231231231\",\"senha\":\"password\"}"))
                .andExpect(status().isOk());
        log.info("Sucess");
    }

    @Test
    public void testLogin() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setEmail("test@example.com");
        pessoa.setSenha("password");

        when(pessoaService.findByEmailAndPassword("test@example.com", "password")).thenReturn(pessoa);
        mockMvc.perform(post("/pessoas/login")
                        .contentType("application/json")
                        .content("{\"email\":\"test@example.com\",\"senha\":\"password\"}"))
                .andExpect(status().isOk());
        log.info("Sucess");
    }

    @Test
    public void testCheckEmail() throws Exception {
        when(pessoaService.emailExists("test@example.com")).thenReturn(true);
        mockMvc.perform(get("/pessoas/check-email")
                        .param("email", "test@example.com"))
                .andExpect(status().isConflict())
                .andExpect(content().string("Email already exists in the database"));
        log.info("Sucess");
    }

    @Test
    public void testGetAllPessoas() throws Exception {
        when(pessoaService.getAllPessoas()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/pessoas/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
        log.info("Sucess");
    }

    @Test
    public void testGetPessoaById() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setEmail("test@example.com");
        when(pessoaService.getPessoaById(1L)).thenReturn(Optional.of(pessoa));
        mockMvc.perform(get("/pessoas/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"email\":\"test@example.com\"}"));
        log.info("Sucess");
    }

    @Test
    public void testUpdatePessoa() throws Exception {
        Pessoa pessoa = new Pessoa();
            when(pessoaService.updatePessoa(1L, pessoa)).thenReturn(pessoa);
        mockMvc.perform(put("/pessoas/2")
                        .contentType("application/json")
                        .content("{\"nome\":\"Updated Name\",\"cpf\":\"12345678901\",\"dataNascimento\":\"1990-01-01\",\"email\":\"updated@example.com\",\"telefone\":\"1231231231\",\"senha\":\"newpassword\"}"))
                .andExpect(status().isOk());
    }

}