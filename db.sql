create database Doceria;

use Doceria;

CREATE TABLE tb_funcionarios (
	 CPF BIGINT PRIMARY KEY,
         Nome VARCHAR(50),
         Funcao VARCHAR(30),
         Senha VARCHAR(15),
         Data_nasc INTEGER,
         Email VARCHAR(70),
         CEP VARCHAR(10),
         Logradouro VARCHAR(80),
         Numero INTEGER,
         Bairro VARCHAR(50) 
);
