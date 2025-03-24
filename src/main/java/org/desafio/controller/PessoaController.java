package org.desafio.controller;

import lombok.extern.log4j.Log4j2;
import org.desafio.model.Pessoa;
import org.desafio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }@CrossOrigin(origins = "https://cadastropessoabackend-6a32f0706386.herokuapp.com")
    @PostMapping("/create")
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa createdPessoa = pessoaService.createPessoa(pessoa);
        return ResponseEntity.ok(createdPessoa);
    }

    @CrossOrigin(origins = "https://cadastropessoabackend-6a32f0706386.herokuapp.com")
    @PostMapping("/login")
    public ResponseEntity<Pessoa> login(@RequestBody Pessoa loginRequest) {
        Pessoa pessoa = pessoaService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getSenha());
        if (pessoa != null) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @CrossOrigin(origins = "https://cadastropessoabackend-6a32f0706386.herokuapp.com")
    @GetMapping("/check-email")
    public ResponseEntity<String> checkEmail(@RequestParam String email) {
        if (pessoaService.emailExists(email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists in the database");
        } else {
            return ResponseEntity.ok("Email does not exist in the database");
        }
    }
    @CrossOrigin(origins = "https://cadastropessoabackend-6a32f0706386.herokuapp.com")
    @GetMapping("/all")
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        List<Pessoa> pessoas = pessoaService.getAllPessoas();
        return ResponseEntity.ok(pessoas);
    }
    @CrossOrigin(origins = "https://cadastropessoabackend-6a32f0706386.herokuapp.com")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.getPessoaById(id).orElseThrow(() -> new RuntimeException("Pessoa not found"));
        return ResponseEntity.ok(pessoa);
    }
    @CrossOrigin(origins = "https://cadastropessoabackend-6a32f0706386.herokuapp.com")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoaDetails) {
        Pessoa updatedPessoa = pessoaService.updatePessoa(id, pessoaDetails);
        return ResponseEntity.ok(updatedPessoa);
    }
}