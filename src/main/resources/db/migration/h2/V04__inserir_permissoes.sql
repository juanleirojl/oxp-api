INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url) 
                      VALUES(1,
                              'ROLE_ADMINISTRADOR', 
                              'Tem permissão para acessar todas as telas do sistema',
                              '*');      
                              
-- USUARIO                             
INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url)  
                      VALUES(2,
                              'ROLE_USUARIO_CADASTRO', 
                              'Tem permissão para acessar o cadastro de usuário',
                              '/usuarios/**');
                              
INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url)  
                      VALUES(3,
                              'ROLE_USUARIO_CONSULTA', 
                              'Tem permissão para acessar a consulta de usuário',
                              '/usuarios');
                              
--CONTATO          
INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url)  
                      VALUES(4,
                              'ROLE_CONTATO_CADASTRO', 
                              'Tem permissão para acessar o cadastro de contato',
                              '/contatos/**');
                              
INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url)  
                      VALUES(5,
                              'ROLE_CONTATO_CONSULTA', 
                              'Tem permissão para acessar a consulta de contato',
                              '/contatos');                              

-- INTEGRADOR
INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url)  
                      VALUES(6,
                              'ROLE_INTEGRADOR_CADASTRO', 
                              'Tem permissão para acessar o cadastro de integrador',
                              '/integradores/**');
                              
INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url)  
                      VALUES(7,
                              'ROLE_INTEGRADOR_CONSULTA', 
                              'Tem permissão para acessar a consulta de integrador',
                              '/integradores');  

                              
-- OPERADORA
INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url)  
                      VALUES(8,
                              'ROLE_OPERADORA_CADASTRO', 
                              'Tem permissão para acessar o cadastro de operadora',
                              '/operadoras/**');
                              
INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url)  
                      VALUES(9,
                              'ROLE_OPERADORA_CONSULTA', 
                              'Tem permissão para acessar a consulta de operadora',
                              '/operadoras');  
                              

-- PARCEIRO
INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url)  
                      VALUES(10,
                              'ROLE_PARCEIRO_CADASTRO', 
                              'Tem permissão para acessar o cadastro de parceiro',
                              '/parceiros/**');
                              
INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url)  
                      VALUES(11,
                              'ROLE_PARCEIRO_CONSULTA', 
                              'Tem permissão para acessar a consulta de parceiro',
                              '/parceiros');  

-- RELATORIO PARCEIRO -- PESQUISA
INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url)  
                      VALUES(12,
                              'ROLE_RELATORIO_PARCEIRO', 
                              'Tem permissão para acessar relatório de parceiro',
                              '/relatorios');

-- HISTORICOS
INSERT INTO PERMISSAO(id_permissao,
                             nome,
                             descricao,
                             url)  
                      VALUES(13,
                              'ROLE_HISTORICO', 
                              'Tem permissão para acessar os historicos',
                              '/historicos/**');
                              
                  
                              
  -- INSERIR RELACIONAMENTO Grupo_Permissao
  
--ADMINISTRADOR
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (1,1);


--DESENVOLVIMENTO
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (2,3);
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (2,4);
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (2,5);
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (2,6);
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (2,7);
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (2,8);
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (2,9);
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (2,10);
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (2,11);
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (2,12);
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (2,13);

                              
--COMERCIAL                     

INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (3,3); -- /USUARIOS_CONSULTA
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (3,4); -- /CONTATOS_CADASTRO
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (3,5); -- /CONTATOS_CONSULTA 
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (3,7); -- /INTEGRADORES_CONSULTA
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (3,9); -- /OPERADORAS_CONSULTA
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (3,11); -- /PARCEIROS_CONSULTA
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (3,12); -- /RELATORIOS
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (3,13); -- /HISTORICOS

-- SUPORTE
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (4,3); -- /USUARIOS_CONSULTA
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (4,5); -- /CONTATOS_CONSULTA 
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (4,7); -- /INTEGRADORES_CONSULTA
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (4,9); -- /OPERADORAS_CONSULTA
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (4,11); -- /PARCEIROS_CONSULTA
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (4,12); -- /RELATORIOS
INSERT INTO Grupo_Permissao(id_Grupo, id_Permissao) VALUES (4,13); -- /HISTORICOS
