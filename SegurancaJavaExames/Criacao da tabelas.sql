create database segurancaJavaExames;
use segurancaJavaExames;

-- Criação da tabela paciente
CREATE TABLE paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL
);

-- Criação tabela medico
CREATE TABLE medico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    especialidade VARCHAR(100) NOT NULL
);

-- Criação da tabela LDL 
CREATE TABLE exame (
    id INT AUTO_INCREMENT PRIMARY KEY,
    resultado VARCHAR(100) NOT NULL, 
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id),
    FOREIGN KEY (id_medico) REFERENCES medico(id)

);

-- Criação tabela valorespadroes
CREATE TABLE valorespadroes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    limite_inferior DECIMAL(10, 2),
    limite_superior DECIMAL(10, 2),
    unidade VARCHAR(50) NOT NULL,
    valor_referencia VARCHAR(100)
);

-- Criação da tabela senha 
CREATE TABLE senha (
    id INT AUTO_INCREMENT PRIMARY KEY,
    chave_secreta VARCHAR(100) NOT NULL
);

-- Criação da tabela usuário 
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(64) NOT NULL
);

-- Inserindo alguns pacientes: 
INSERT INTO medico (nome, cpf, especialidade) VALUES
('Dr. Daniel Moreno', '123.456.789-00', 'Pediatria');

INSERT INTO medico (nome, cpf, especialidade) VALUES
('Dra. Luzinete Moreno', '999.665.123-00', 'Ortopedia');

INSERT INTO medico (nome, cpf, especialidade) VALUES
('Dr. Rafael Moreno', '241.875.524-00', 'Alergista');

-- Inserindo alguns pacientes
INSERT INTO paciente (nome, cpf) VALUES
('Daniele Moreno', '111.222.333-44');

INSERT INTO paciente (nome, cpf) VALUES
('Alberto Cpsta', '555.666.777-88');

INSERT INTO paciente (nome, cpf) VALUES
('Claudia Abreu', '999.888.777-66');

-- Inserindo os valores padroes
INSERT INTO valorespadroes(limite_inferior, limite_superior, unidade, valor_referencia) VALUES
(2.6, 6.0, 'mg/dL', 'Valores normais'); 

