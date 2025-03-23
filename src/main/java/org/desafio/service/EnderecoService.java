package org.desafio.service;

import lombok.extern.log4j.Log4j2;
import org.desafio.model.Endereco;
import org.desafio.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class EnderecoService {

    private final RestTemplate restTemplate;

    public EnderecoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object fetchCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        try {
            // Faz a chamada para a API externa
            return restTemplate.getForEntity(url, Endereco.class).getBody();
        } catch (HttpClientErrorException e) {
            // Trata erros de requisição (ex.: 404)
            throw new RuntimeException("CEP não encontrado ou inválido: " + e.getStatusCode());
        } catch (Exception e) {
            // Trata outros erros genéricos
            throw new RuntimeException("Erro ao buscar CEP: " + e.getMessage());
        }
    }

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco createEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> getEnderecoById(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco updateEndereco(Long id, Endereco enderecoDetails) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereco not found"));
        endereco.setCep(enderecoDetails.getCep());
        endereco.setLogradouro(enderecoDetails.getLogradouro());
        endereco.setBairro(enderecoDetails.getBairro());
        endereco.setLocalidade(enderecoDetails.getLocalidade());
        endereco.setUf(enderecoDetails.getUf());
        endereco.setEstado(enderecoDetails.getEstado());
        return enderecoRepository.save(endereco);
    }

    public void deleteEndereco(Long id) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereco not found"));
        enderecoRepository.delete(endereco);
    }

}
