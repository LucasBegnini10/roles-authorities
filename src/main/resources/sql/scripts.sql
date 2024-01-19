INSERT INTO auth.roles
(id, name, description)
VALUES(1, 'ROLE_USER', 'Usuário com privilégios mínimos');

INSERT INTO auth.roles
(id, name, description)
VALUES(2, 'ROLE_MODERATOR', 'Usuário com alguns privilégios');

INSERT INTO auth.roles
(id, name, description)
VALUES(3, 'ROLE_ADMIN', 'Usuário com todos privilégios');

INSERT INTO auth.authorities
(id, name, description)
VALUES(1, 'VIEW_CONTENT', 'Pode visualizar publicações');

INSERT INTO auth.authorities
(id, name, description)
VALUES(2, 'CREATE_CONTENT', 'Pode criar publicações');

INSERT INTO auth.authorities
(id, name, description)
VALUES(3, 'UPDATE_CONTENT', 'Pode atualizar publicações');

INSERT INTO auth.authorities
(id, name, description)
VALUES(4, 'DELETE_CONTENT', 'Pode deletar publicações');