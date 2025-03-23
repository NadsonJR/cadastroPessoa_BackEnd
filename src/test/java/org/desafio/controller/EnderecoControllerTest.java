package org.desafio.controller;

import lombok.extern.log4j.Log4j2;
import org.desafio.controller.EnderecoController;
import org.desafio.model.Endereco;
import org.desafio.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@Log4j2
public class EnderecoControllerTest {

    @Mock
    private EnderecoService enderecoService;

    @InjectMocks
    private EnderecoController enderecoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCep() {
        String cep = "12345678";
        Object response = new Object();
        when(enderecoService.fetchCep(cep)).thenReturn(response);

        ResponseEntity<Object> result = enderecoController.getCep(cep);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        log.info(result.getBody().toString());
        verify(enderecoService, times(1)).fetchCep(cep);
    }

    @Test
    public void testGetCepException() {
        String cep = "12345678";
        when(enderecoService.fetchCep(cep)).thenThrow(new RuntimeException("Service error"));

        ResponseEntity<Object> result = enderecoController.getCep(cep);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals("Erro ao processar a solicitacao: Service error", result.getBody());
        log.info(result.getBody().toString());
        verify(enderecoService, times(1)).fetchCep(cep);
    }

    @Test
    public void testCreateEndereco() {
        Endereco endereco = new Endereco();
        Endereco createdEndereco = new Endereco();
        createdEndereco.setCep("06763040");
        when(enderecoService.createEndereco(endereco)).thenReturn(createdEndereco);

        ResponseEntity<Endereco> result = enderecoController.createEndereco(endereco);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(createdEndereco, result.getBody());
        log.info(result.getBody().toString());
        verify(enderecoService, times(1)).createEndereco(endereco);
    }
}
