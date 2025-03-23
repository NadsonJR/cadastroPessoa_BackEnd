package org.desafio.controller.RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import org.desafio.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnderecoControllerRestAssuredTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        log.info("Setting up RestAssured with port: {}", port);
    }

    @Test
    public void testGetCep() {
        given()
                .pathParam("cep", "06763040")
                .when()
                .get("/endereco/cep/{cep}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("cep", equalTo("06763-040"));
        log.info("Finished testGetCep");
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

        given()
                .contentType(ContentType.JSON)
                .body(endereco)
                .when()
                .post("/endereco/create")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("cep", equalTo("12345678"));
        log.info("Finished testCreateEndereco");
    }
}
