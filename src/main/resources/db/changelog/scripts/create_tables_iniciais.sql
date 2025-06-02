-- Tabela Moto
CREATE TABLE moto (
                      id SERIAL PRIMARY KEY NOT NULL,
                      placa VARCHAR(10) NOT NULL,
                      modelo VARCHAR(50) NOT NULL,
                      marca VARCHAR(50) NOT NULL,
                      ano INT NOT NULL,
                      cor VARCHAR(30),
                      status VARCHAR(20)
);

-- Tabela Locatario
CREATE TABLE locatario (
                           id SERIAL PRIMARY KEY NOT NULL,
                           nome VARCHAR(100) NOT NULL,
                           cpf VARCHAR(14) NOT NULL UNIQUE,
                           telefone VARCHAR(15),
                           email VARCHAR(100)
);

-- Tabela Usuario
CREATE TABLE usuario (
                         id SERIAL PRIMARY KEY NOT NULL,
                         nome VARCHAR(100) NOT NULL,
                         login VARCHAR(50) NOT NULL UNIQUE,
                         senha VARCHAR(255) NOT NULL
);

-- Tabela Aluguel
CREATE TABLE aluguel (
                         id SERIAL PRIMARY KEY NOT NULL,
                         data_inicio DATE NOT NULL,
                         data_fim DATE,
                         moto_id INT NOT NULL,
                         locatario_id INT NOT NULL
);

-- Chaves estrangeiras
ALTER TABLE aluguel
    ADD CONSTRAINT fk_moto FOREIGN KEY (moto_id) REFERENCES moto(id);

ALTER TABLE aluguel
    ADD CONSTRAINT fk_locatario FOREIGN KEY (locatario_id) REFERENCES locatario(id);
