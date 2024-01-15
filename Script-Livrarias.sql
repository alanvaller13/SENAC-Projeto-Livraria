CREATE DATABASE db_livraria;
use db_livraria;

CREATE TABLE editoras(
id_editora INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(100),
cidade VARCHAR(50),
estado CHAR(2),
PRIMARY KEY (id_editora)
);

CREATE TABLE autores(
id_autor INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(100),
email VARCHAR(100),
PRIMARY KEY (id_autor)
);

CREATE TABLE livros(
id_livro INT NOT NULL AUTO_INCREMENT,
fk_editoras_id_editora INT,
fk_autores_id_autor INT,
titulo VARCHAR(100),
descricao VARCHAR(100),
ano INT,
PRIMARY KEY (id_livro),
FOREIGN KEY (fk_editoras_id_editora) REFERENCES editoras (id_editora),
FOREIGN KEY (fk_autores_id_autor) REFERENCES autores (id_autor)
);

CREATE TABLE emprestimo(
id_emprestimo INT NOT NULL AUTO_INCREMENT,
fk_livro_id_livros INT,
data_emprestimo DATE,
data_devolucao DATE,
PRIMARY KEY (id_emprestimo),
FOREIGN KEY (fk_livros_id_livro) REFERENCES livros (id_livro),
);
