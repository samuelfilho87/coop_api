create database coop_ong;

use coop_ong;

create table usuario_ong(
	id_ong int auto_increment primary key,
    nome_ong varchar(100) not null, 
    nome_completo varchar(100) not null,
    cnpj varchar(100) not null unique,
    data_nascimento date not null,
    email varchar(100) not null,
    whatsapp varchar (20) not null, 
    sobre varchar(200) not null, 
    area_atuacao varchar(100) not null,
    funcao varchar(50) not null,
    senha varchar(20) not null,
    trabalha_ong char(3) not null,
    itens_doacao varchar(200) not null,
    facebook varchar(100) not null, 
    instagram varchar(100) not null
    
);

create table item_doacao(
	id_item int auto_increment primary key,
    nome_item_doacao varchar(100) not null,
    unidade_medida int not null, 
    url_imagem longblob not null
);

create table item_usuario_ong(
	fk_id_ong int not null, 
    fk_id_item int not null, 
	FOREIGN KEY(fk_id_item) REFERENCES item_doacao (id_item),
    FOREIGN KEY(fk_id_ong) REFERENCES usuario_ong (id_ong)
    
);

create table endereco(
	id_endereco int auto_increment primary key,
	logradouro varchar(100) not null, 
    numero varchar(10) not null, 
    complemento varchar(50) not null, 
    cep varchar(20) not null, 
    estado char(2) not null, 
    cidade varchar(60) not null, 
    fk_id_ong int not null,
    FOREIGN KEY(fk_id_ong) REFERENCES usuario_ong (id_ong)

);

create table imagem_ong(
	id_imagem_ong int auto_increment primary key,
    nome_imagem varchar(100) not null,
    url_imagem longblob not null,
    fk_id_ong int not null,
    FOREIGN KEY(fk_id_ong) REFERENCES usuario_ong (id_ong)
);

create table publicacao(
	id_publicacao int auto_increment primary key,
    nome_publicacao varchar (100) not null,
	legenda varchar(500) not null,
    data_publicacao date not null,
    fk_id_imagem_ong int not null,
	fk_id_ong int not null,
    FOREIGN KEY(fk_id_ong) REFERENCES usuario_ong (id_ong),
    FOREIGN KEY(fk_id_imagem_ong) REFERENCES imagem_ong (id_imagem_ong)
    
);

create table doacao(
	id_doacao int auto_increment primary key, 
    nome_completo varchar(100) not null, 
    whatsapp varchar(20) not null, 
    itens_doacao varchar(200) not null,
    status_doacao varchar(25) not null,
    data_entrega_doacao date,
	/*Tirar dúvidas sobre (Se precisa ou não ter chave estrangeira):
	O usuário que não tem cadastro no site precisa de chave estrangeira?
	Não fez sentido ter a chave.
	*/
    fk_id_ong int not null,
	FOREIGN KEY(fk_id_ong) REFERENCES usuario_ong (id_ong)
);

create table doacao_itens(
	fk_id_item int, 
    fk_id_doacao int,
    FOREIGN KEY(fk_id_item) REFERENCES item_doacao (id_item),
    FOREIGN KEY(fk_id_doacao) REFERENCES doacao (id_doacao)
    
);


INSERT INTO usuario_ong
(nome_ong, nome_completo, cnpj, data_nascimento, email, whatsapp, 
sobre, area_atuacao, funcao, senha, trabalha_ong, itens_doacao, facebook, instagram)
VALUES ('ONG Ação Vida', 'João Pedro', '00.806.666/0001-31',  '1993-11-15', 'ongacaovida@gmail.com', '(81) 9 9999-9999', 
'Ong destinada a ajuda de pessoas que m', 'Administrativo', 'Geral', '123456', 'SIM', 'Alimento, Roupa', 'https://www.facebook.com/ongacaovida',
'https://www.instagram.com/ongacaovida');

INSERT INTO item_doacao(nome_item_doacao, unidade_medida, url_imagem) VALUES('Alimento, Roupa', 10, 'Image_1.jpg');

INSERT INTO item_usuario_ong(fk_id_ong, fk_id_item)VALUES(1 , 1);


INSERT INTO endereco 
(logradouro, numero, complemento, cep, estado, cidade, fk_id_ong)
VALUES ('Rua São Pedro', '582', 'Próximo a loja de seu Zé', '49089-173', 'SE', 'Aracaju', 1);

INSERT INTO imagem_ong(nome_imagem, url_imagem, fk_id_ong) VALUES('ongs', 'img_1.jpg', 1);

/*Publicação feita usuário da ONG*/
INSERT INTO publicacao(nome_publicacao, legenda, data_publicacao, fk_id_imagem_ong, fk_id_ong)VALUES
('Ação de Carnaval', 'Ong é uma plataforma gratuíta que conecta pessoas à ONGs
dentro de um só lugar. Ajudando diversas ONGs a continuarem com seus trabalhos sociais, através da
doação dos nossos usuários.', '2020-10-27', 1, 1);

/*Cenário Recebidas*/
INSERT INTO doacao(nome_completo, whatsapp, itens_doacao, status_doacao, data_entrega_doacao, fk_id_ong) VALUES
('Maria Eduarda', '(22) 9 2222-2222', 'Dinheiro, Material higiene', 'Recebida', '2020-10-27', 1);

INSERT INTO doacao_itens(fk_id_item, fk_id_doacao)VALUES(1, 1);

/*
- Cenário Pendentes
INSERT INTO doacao(nome_completo, whatsapp, itens_doacao, status_doacao, data_entrega_doacao, fk_id_ong) VALUES
('Marcelo Souza', '(22) 9 2222-2222', 'Dinheiro, Material higiene', 'Pendentes', '2020-10-27', 1);

/*
- Cenário Canceladas
INSERT INTO doacao(nome_completo, whatsapp, itens_doacao, status_doacao, data_entrega_doacao, fk_id_ong) VALUES
('Jaqueline Silva', '(22) 9 2222-2222', 'Dinheiro, Material higiene', 'Canceladas', '2020-10-27');
*/










