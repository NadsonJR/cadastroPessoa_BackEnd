package org.desafio.controller;

import lombok.extern.log4j.Log4j2;
import org.desafio.model.Endereco;
import org.desafio.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }
    @GetMapping("/cep/{cep}")
    public ResponseEntity<Object> getCep(@PathVariable String cep) {
        log.info("Recebendo solicitacao para buscar CEP:"+ cep);
        try {
            // Chama o serviço para buscar informações do CEP
            Object response = enderecoService.fetchCep(cep);
            // Retorna a resposta para o cliente
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Retorna um erro genérico para o cliente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a solicitacao: " + e.getMessage());
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Endereco> createEndereco(@RequestBody Endereco endereco) {
        Endereco createdEndereco = enderecoService.createEndereco(endereco);
        log.info("Recebendo solicitação para criar um novo endereço: " + createdEndereco.getCep());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEndereco);
    }
}
