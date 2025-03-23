package org.desafio.repository;

import org.desafio.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByEmailAndSenha(String email, String senha);
    Pessoa findByEmail(String email);
}
