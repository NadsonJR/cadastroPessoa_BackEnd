package org.desafio.controller.RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import org.desafio.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaControllerRestAssuredTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        log.info("Setting up RestAssured with port: {}", port);
    }

    @Test
    public void testCreatePessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("test");
        pessoa.setCpf("12345678901");
        pessoa.setDataNascimento("2025-01-08");
        pessoa.setEmail("test@example.com");
        pessoa.setTelefone("1231231231");
        pessoa.setSenha("password");

        given()
                .contentType(ContentType.JSON)
                .body(pessoa)
                .when()
                .post("/pessoas/create")
                .then()
                .statusCode(HttpStatus.OK.value());
        log.info("Finished testCreatePessoa");
    }

    @Test
    public void testLogin() {
        Pessoa pessoa = new Pessoa();
        pessoa.setEmail("login@example.com");
        pessoa.setSenha("password");

        given()
                .contentType(ContentType.JSON)
                .body(pessoa)
                .when()
                .post("/pessoas/login")
                .then()
                .statusCode(HttpStatus.OK.value());
        log.info("Finished testLogin");
    }

    @Test
    public void testCheckEmail() {
        given()
                .param("email", "login@example.com")
                .when()
                .get("/pessoas/check-email")
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .body(equalTo("Email already exists in the database"));
        log.info("Finished testCheckEmail");
    }

    @Test
    public void testGetAllPessoas() {
        given()
                .when()
                .get("/pessoas/all")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$.size()", greaterThan(1));
        log.info("Finished testGetAllPessoas");
    }

    @Test
    public void testGetPessoaById() {
        given()
                .when()
                .get("/pessoas/2")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("email", equalTo("login@example.com"));
        log.info("Finished testGetPessoaById");
    }

    @Test
    public void testUpdatePessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Updated Name");
        pessoa.setCpf("12345678901");
        pessoa.setDataNascimento("1990-01-01");
        pessoa.setEmail("login@example.com");
        pessoa.setTelefone("1231231231");
        pessoa.setSenha("newpassword");

        given()
                .contentType(ContentType.JSON)
                .body(pessoa)
                .when()
                .put("/pessoas/2")
                .then()
                .statusCode(HttpStatus.OK.value());
        log.info("Finished testUpdatePessoa");
    }
}
