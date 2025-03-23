package org.desafio.service;

import lombok.extern.log4j.Log4j2;
import org.desafio.model.Pessoa;
import org.desafio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PessoaService {

    private final RestTemplate restTemplate;

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> getPessoaById(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoaDetails) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa not found"));
        pessoa.setNome(pessoaDetails.getNome());
        pessoa.setCpf(pessoaDetails.getCpf());
        pessoa.setDataNascimento(pessoaDetails.getDataNascimento());
        pessoa.setEmail(pessoaDetails.getEmail());
        pessoa.setTelefone(pessoaDetails.getTelefone());
        return pessoaRepository.save(pessoa);
    }
    public boolean emailExists(String email) {
        return pessoaRepository.findByEmail(email) != null;
    }
    public Pessoa findByEmailAndPassword(String email, String senha) {
        return pessoaRepository.findByEmailAndSenha(email, senha);
    }

    public void deletePessoa(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa not found"));
        pessoaRepository.delete(pessoa);
    }
}
