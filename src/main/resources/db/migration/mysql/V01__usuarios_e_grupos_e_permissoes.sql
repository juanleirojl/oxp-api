CREATE TABLE usuario (
    id INT(10) NOT NULL auto_increment,
    login NVARCHAR(255) NOT NULL,
    nome NVARCHAR(255) NOT NULL,
    status CHAR (1) NOT NULL,
    idt_usr_alt INT(10),
    dth_ult_alt DATETIME,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table usuario add constraint FK_Usuario_usuario foreign key (idt_usr_alt) references Usuario(id);

insert into usuario (login, nome, status,idt_usr_alt,dth_ult_alt) values ('admin','Administrador','A',1,NOW());


CREATE TABLE grupo (
    id            INT(10) NOT NULL auto_increment,
    descricao     NVARCHAR( 200 ) ,
    nome          NVARCHAR( 100 ) ,
    idt_usr_alt   INT,
    dth_ult_alt   DATETIME,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- alter table grupo add constraint PK_grupo primary key (id);
alter table grupo add constraint FK_Grupo_user foreign key (idt_usr_alt) references Usuario(id);

CREATE TABLE usuario_grupo (
    usuario_id INT(20) NOT NULL,
    grupo_id INT(20) NOT NULL
    -- ,PRIMARY KEY (usuario_id , grupo_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table usuario_grupo add constraint PK_usuario_grupo primary key (grupo_id,usuario_id);
alter table usuario_grupo add constraint FK_Grupo foreign key (grupo_id) references Grupo(id);
alter table usuario_grupo add constraint FK_Usuario foreign key (usuario_id) references Usuario(id);

-- GRUPOS
INSERT INTO grupo (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('Administrador','Tem permissão para acessar todas as telas do sistema.',1,NOW());
INSERT INTO grupo (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('Desenvolvimento','Tem permissão para acessar todas as telas do sistema, exceto o cadastro de usuário.',1,NOW());
INSERT INTO grupo (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('Comercial','Tem permissão para acessar a tela de relatório, históricos, acesso total a tela de cadastro de contatos e listagem dos demais cadastros.',1,NOW());
INSERT INTO grupo (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('Suporte','Tem permissão para acessar a tela de relatório, históricos, listagem dos demais cadastros.',1,NOW());

CREATE TABLE permissao (
    id            INT NOT NULL auto_increment,
    descricao     NVARCHAR( 255 ),
    nome          NVARCHAR( 100 ) ,
    idt_usr_alt   INT(11),
    dth_ult_alt   DATETIME,
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- alter table permissao add constraint PK_permissao primary key (id);
alter table permissao add constraint FK_Permissao_user foreign key (idt_usr_alt) references Usuario(id);

CREATE TABLE grupo_permissao (
    grupo_id       INT(20) NOT NULL,
    permissao_id   INT(20) NOT NULL
);

alter table grupo_permissao add constraint PK_grupo_permissao primary key (grupo_id,permissao_id);
alter table grupo_permissao add constraint FK_Grupo_Permissao foreign key (grupo_id) references Grupo(id);
alter table grupo_permissao add constraint FK_Permissao_Grupo foreign key (permissao_id) references Permissao(id);

-- PERMISSAO
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_ADMINISTRADOR','Administrador',1,NOW());     
                              
-- USUARIO                             
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_USUARIO_CADASTRO','Cadastro de Usuario',1,NOW());
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_USUARIO_CONSULTA','Consulta de Usuario',1,NOW());
                              
-- CONTATO          
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_CONTATO_CADASTRO','Cadastro de Contato',1,NOW());
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_CONTATO_CONSULTA','Consulta de Contato',1,NOW());                              

-- INTEGRADOR
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_INTEGRADOR_CADASTRO','Cadastro de Integrador',1,NOW());
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_INTEGRADOR_CONSULTA','Consulta de Integrador',1,NOW());  

                              
-- OPERADORA
INSERT INTO permissao ( nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_OPERADORA_CADASTRO','Cadastro de Operadora',1,NOW());
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_OPERADORA_CONSULTA','Consulta de Operadora',1,NOW());  

-- OPERADORA PARAMETRO
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_OPERADORA_PARAMETRO_CADASTRO','Cadastro de Parametros da Operadora',1,NOW());
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_OPERADORA_PARAMETRO_CONSULTA','Consulta de Parametros da Operadora',1,NOW());  
                              
-- PARCEIRO
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_PARCEIRO_CADASTRO','Cadastro de Parceiro',1,NOW());
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_PARCEIRO_CONSULTA','Consulta de Parceiros',1,NOW());  

-- RELATORIO PARCEIRO -- PESQUISA
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_RELATORIO_PARCEIRO','Relatorio Parceiro',1,NOW());

-- HISTORICOS ALTERAÇÕES
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_HISTORICO_ALTERACOES','Historico de Alteracoes',1,NOW());
                              
-- HISTORICO SOLICITACOES                             
INSERT INTO permissao (nome,descricao,idt_usr_alt,dth_ult_alt) VALUES ('ROLE_HISTORICO_SOLICITACOES','Historico de Solicitacoes',1,NOW());  

-- RELACIONAMENTO GRUPO - PERMISSAO

-- ADMINISTRADOR
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (1,1);


-- DESENVOLVIMENTO
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,3);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,4);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,5);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,6);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,7);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,8);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,9);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,10);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,11);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,12);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,13);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,14);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,15);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (2,16);
                            
-- COMERCIAL                     

INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (3,3); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (3,4); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (3,5); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (3,7); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (3,9); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (3,11);  
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (3,13); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (3,14); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (3,15); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (3,16); 

-- SUPORTE
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (4,3); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (4,5);  
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (4,7); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (4,9); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (4,11); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (4,13); 
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (4,14);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (4,15);
INSERT INTO grupo_permissao (grupo_id,permissao_id) VALUES (4,16);

INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (1,1);