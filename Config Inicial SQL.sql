create database DesafioNTT;
use DesafioNTT;

drop database DesafioNTT;

CREATE TABLE Pessoa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    data_nascimento VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,	
    senha VARCHAR(255) NOT NULL
);
CREATE TABLE Endereco (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cep VARCHAR(10) NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    localidade VARCHAR(255) NOT NULL,
    uf VARCHAR(255) NOT NULL,
    estado VARCHAR(255),
    pessoa_id BIGINT ,
    FOREIGN KEY (pessoa_id) REFERENCES Pessoa(id)
);

SHOW TABLES;
select * from endereco;
select * from Pessoa;
