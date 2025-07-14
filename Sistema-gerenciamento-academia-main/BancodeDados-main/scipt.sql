CREATE DATABASE IF NOT EXISTS academia;
USE academia;

create TABLE IF NOT EXISTS plano(
	id_plano INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	descricao VARCHAR(20),
    preco DECIMAL(10, 2)
);

create TABLE IF NOT EXISTS conta(
	id_conta INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome_completo VARCHAR(60) NOT NULL,
    cpf CHAR(11) UNIQUE,
    telefone  CHAR(11),
    email VARCHAR(30)
);

create TABLE IF NOT EXISTS endereco(
	id_endereco INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_conta INT NOT NULL,
	cep CHAR(8),
    logradouro VARCHAR(50),
    bairro VARCHAR(20),
    numero INT,
    complemento VARCHAR(40),
    FOREIGN KEY(id_conta) REFERENCES conta(id_conta)
);

create TABLE IF NOT EXISTS cliente(
	id_cliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_conta INT NOT NULL UNIQUE,
    data_cadastro DATE,
    observacao VARCHAR(200),
    FOREIGN KEY(id_conta) REFERENCES conta(id_conta)
);

create TABLE IF NOT EXISTS matricula(
	id_matricula INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_plano INT NOT NULL,
    data_inicio DATE,
    data_fim DATE,
    esta_ativo BOOLEAN,
    data_pagamento DATE,
    data_vencimento DATE,
	FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY(id_plano) REFERENCES plano(id_plano)
);

create TABLE IF NOT EXISTS funcionario(
	id_funcionario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_conta INT NOT NULL UNIQUE,
    funcao VARCHAR(20),
    salario DECIMAL(10,2),
    FOREIGN KEY(id_conta) REFERENCES conta(id_conta)
);

create TABLE IF NOT EXISTS registro_acesso(
	id_cliente INT NOT NULL,
    data_entrada DATETIME,
    data_saida DATETIME,
    PRIMARY KEY (id_cliente, data_entrada, data_saida)

);

create TABLE IF NOT EXISTS equipamento(
	id_equipamento INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100),
    marca VARCHAR(15),
    musculo_alvo VARCHAR(20)

);

