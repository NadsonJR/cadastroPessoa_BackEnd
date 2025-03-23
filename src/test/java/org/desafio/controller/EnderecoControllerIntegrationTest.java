package org.desafio.controller;

import org.desafio.model.Endereco;
import org.desafio.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnderecoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @BeforeEach
    public void setUp() {
        enderecoRepository.deleteAll();
    }

    @Test
    public void testCreateEndereco() {
        Endereco endereco = new Endereco();
        endereco.setCep("12345678");
        endereco.setLogradouro("123");
        endereco.setBairro("Bairro Teste");
        endereco.setLocalidade("Localidade Teste");
        endereco.setUf("TS");
        endereco.setEstado("Estado Teste");
        endereco.setPessoaId(2);

        ResponseEntity<Endereco> response = restTemplate.postForEntity("/endereco/create", endereco, Endereco.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCep()).isEqualTo("12345678");
    }
}
