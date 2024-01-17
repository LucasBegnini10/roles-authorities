# Spring Security - Auth and Authorization

Tópicos do projeto:

- Autenticação com JWT
- Autorização com Roles e Authorities

Para isso, precisaremos de classes de domínios:

## **User**

Representará o usuário do sistema. Ele terá um relacionamento com **role** e **authority** de muitos para muitos.

| Propriedade | Tipo |
| --- | --- |
| id | UUID |
| username | String |
| email | String |
| password | String (hash) |
| roles | `List<Role>` |
| authorities | `List<Authorities>` |

Para hash do campo `password`, será utilizado `BCrypt`

## Role

Representará nosso cargo no sistema. 

| Propriedade | Tipo |
| --- | --- |
| id | UUID |
| name | String |
| description | String |

## Authorities

Representará nossos privilégios no sistema.

| Propriedade | Tipo |
| --- | --- |
| id | UUID |
| name | String |
| description | String |

# Requisitos

- [ ]  O sistema deve possuir um CRUD de usuários;
- [ ]  O sistema deve possuir a alteração dos dados do usuário;
- [ ]  O sistema deve conseguir alterar cargos do usuário;
- [ ]  O sistema deve conseguir alterar permissões do usuário;
- [ ]  O sistema deve autenticar o usuário;
- [ ]  O sistema deve autorizar o acesso a determinados recursos a partir do cargo e das permissões
