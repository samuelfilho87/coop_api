create database coop_bd;

use coop_bd;

CREATE TABLE usuario_ong (
	id_ong INT AUTO_INCREMENT PRIMARY KEY,
    nome_ong varchar(100) not null, 
    cnpj_ong varchar(18) unique,
    sobre_ong varchar(400) not null,
    email_ong varchar(100) not null,
    senha_ong varchar(100) not null,
    whatsapp_ong varchar(100) not null,
    facebook_ong varchar(200) not null,
    instagram_ong varchar(200),
    area_atuacao_ong varchar(100) not null,

	itens_doacao_requeridos varchar(400) not null, 
    
    nome_completo_responsavel varchar(100) not null,
    data_nascimento_responsavel DATE not null,
    email_responsavel varchar(100) not null,
	whatsapp_responsavel varchar(100) not null,
    trabalha_ong boolean not null,
    funcao_responsavel varchar(100),
    
    logradouro_local_ong varchar(100) not null, 
    numero_local_ong varchar(10) not null, 
    complemento_local_ong varchar(50), 
    cep_local_ong varchar(20) not null, 
    estado_local_ong char(2) not null, 
    cidade_local_ong varchar(60) not null
    latitude varchar(60) not null
    longitude varchar(60) not null
);

CREATE TABLE imagem_ong (
    id_imagem_ong INT AUTO_INCREMENT PRIMARY KEY,
    imagem_ong_1 varchar(100) not null,
    imagem_ong_2 varchar(100),
    imagem_ong_3 varchar(100),
    imagem_ong_4 varchar(100),
    imagem_ong_5 varchar(100),
    
    fk_id_ong int not null,
    FOREIGN KEY(fk_id_ong) REFERENCES usuario_ong(id_ong)
);

CREATE TABLE publicacao_ong (
    id_publicacao INT AUTO_INCREMENT PRIMARY KEY,
    visualizacoes INT,
    titulo_publicacao varchar(100) not null,
    imagem_publicacao varchar(100) not null,
    data_publicacao DATE not null,
    legenda_publicacao varchar(400) not null,
    
    /* status da publicacao? */
    
    fk_id_ong int not null,
    FOREIGN KEY(fk_id_ong) REFERENCES usuario_ong(id_ong)
);
 
CREATE TABLE doacao (
    id_doacao INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo_doador varchar(100) not null,
    data_entrega_doacao DATE not null,
    whatsapp_doador varchar(100) not null,
    status_doacao varchar(100) not null,
    itens_doacao varchar(100) not null, 
    
    fk_id_ong int not null,
    FOREIGN KEY(fk_id_ong) REFERENCES usuario_ong(id_ong)
);


INSERT INTO usuario_ong 
(nome_ong, cnpj_ong, sobre_ong, email_ong, senha_ong, whatsapp_ong, facebook_ong, area_atuacao_ong,
itens_doacao_requeridos, nome_completo_responsavel, data_nascimento_responsavel, email_responsavel, whatsapp_responsavel,
trabalha_ong, funcao_responsavel, logradouro_local_ong, numero_local_ong, cep_local_ong, estado_local_ong,
cidade_local_ong) VALUES('ONG Ação Vida', '00.806.666/0001-31', 'Ong destinada a ajuda de pessoas que m', 'ongacaovida@gmail.com',
'senha1234', '(81) 9 9999-9999', 'https://www.facebook.com/ongacaovida', 'Geral', 'Alimento, Roupa, Dinheiro', 'João Pedro Silva',
'1993-11-15', 'jps@gmail.com', '(80) 9 9999-9999', true, 'Administrativo',  'Rua tal', '123456', '36520000', 'MG', 'VRB');

INSERT INTO imagem_ong (imagem_ong_1, imagem_ong_2, fk_id_ong) VALUES('images/img_1.jpg', 'images/img_2.jpg', 1);

INSERT INTO publicacao_ong (titulo_publicacao, visualizacoes, legenda_publicacao, imagem_publicacao, data_publicacao, fk_id_ong)
VALUES ('Ação de Carnaval', 0, 'Ong é uma plataforma gratuíta que conecta pessoas à ONGs
dentro de um só lugar. Ajudando diversas ONGs a continuarem com seus trabalhos sociais, através da
doação dos nossos usuários.', 'images/ONGTAL/img_1.jpg', '2020-10-27', 1);

INSERT INTO doacao (nome_completo_doador, whatsapp_doador, itens_doacao, status_doacao, data_entrega_doacao, fk_id_ong)
VALUES ('Maria Eduarda Silva', '(22) 92222-2222', 'Dinheiro, Material higiene', 'Aguardando', '2020-10-27', 1);


SELECT * FROM usuario_ong;
SELECT * FROM imagem_ong;
SELECT * FROM publicacao_ong;
SELECT * FROM doacao;

SELECT * FROM usuario_ong INNER JOIN publicacao_ong WHERE id_ong = fk_id_ong;
