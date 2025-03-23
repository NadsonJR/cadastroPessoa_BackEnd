package org.desafio.controller;

import org.desafio.model.Endereco;
import org.desafio.model.Pessoa;
import org.desafio.repository.EnderecoRepository;
import org.desafio.repository.PessoaRepository;
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
public class PessoaControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PessoaRepository pessoaRepository;
    private EnderecoRepository enderecoRepository;

//    @BeforeEach
//    public void setUp() {
//        pessoaRepository.deleteAll();
//    }

    @Test
    public void testCreatePessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Test");
        pessoa.setCpf("12345678901");
        pessoa.setDataNascimento("2025-01-08");
        pessoa.setEmail("test@example.com");
        pessoa.setTelefone("1231231231");
        pessoa.setSenha("password");

        ResponseEntity<Pessoa> response = restTemplate.postForEntity("/pessoas/create", pessoa, Pessoa.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    public void testLogin() {
        Pessoa loginRequest = new Pessoa();
        loginRequest.setEmail("login@example.com");
        loginRequest.setSenha("password");

        ResponseEntity<Void> response = restTemplate.postForEntity("/pessoas/login", loginRequest, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testCheckEmail() {

        ResponseEntity<String> response = restTemplate.getForEntity("/pessoas/check-email?email=login@example.com", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isEqualTo("Email already exists in the database");
    }

    @Test
    public void testGetAllPessoas() {

        ResponseEntity<List> response = restTemplate.getForEntity("/pessoas/all", List.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isGreaterThan(0);
    }

    @Test
    public void testGetPessoaById() {

        ResponseEntity<Pessoa> response = restTemplate.getForEntity("/pessoas/2", Pessoa.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getEmail()).isEqualTo("login@example.com");
    }

    @Test
    public void testUpdatePessoa() {

        Pessoa updatedPessoa = new Pessoa();
        updatedPessoa.setNome("Updated Name");
        updatedPessoa.setCpf("12345678901");
        updatedPessoa.setDataNascimento("1990-01-01");
        updatedPessoa.setEmail("login@example.com");
        updatedPessoa.setTelefone("1231231231");
        updatedPessoa.setSenha("newpassword");

        HttpEntity<Pessoa> request = new HttpEntity<>(updatedPessoa);
        ResponseEntity<Pessoa> response = restTemplate.exchange("/pessoas/2", HttpMethod.PUT, request, Pessoa.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getEmail()).isEqualTo("login@example.com");
    }
}