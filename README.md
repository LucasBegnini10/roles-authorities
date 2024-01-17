# Spring Security - Auth and Authorization

Tópicos do projeto:

- Autenticação com JWT
- Autorização com Roles e Authorities

Para isso, precisaremos de classes de domínios:

## **User**

Representará o usuário do sistema. Ele terá um relacionamento com **role** e **authority** de muitos para muitos. Isso significa que termos nossas roles e authorities no banco, e realizaremos nossos relacionamentos

| Propriedade | Tipo |  |
| --- | --- | --- |
| id | UUID | PK |
| username | String | NOT NULL |
| email | String | UNIQUE |
| password | String (hash) | NOT NULL |
| roles | List<Role> |  |
| authorities | List<Authorities> |  |

## Role

Representará nosso cargo no sistema. Terá um relacionamento com user de muitos para muitos no sistema.

| Propriedade | Tipo |  |
| --- | --- | --- |
| id | UUID |  |
| name | String | UNIQUE |
| description | String |  |

## Authorities

Representará nossos privilégios no sistema. Terá um relacionamento com user de muitos para muitos no sistema.

| Propriedade | Tipo |  |
| --- | --- | --- |
| id | UUID |  |
| name | String | UNIQUE |
| description | String |  |

# Requisitos

- [ ]  O sistema deve possuir um CRUD de usuários;
- [ ]  O sistema deve possuir a alteração dos dados do usuário;
- [ ]  O sistema deve conseguir alterar cargos do usuário;
- [ ]  O sistema deve conseguir alterar permissões do usuário;
- [ ]  O sistema deve autenticar o usuário;
- [ ]  O sistema deve autorizar o acesso a determinados recursos a partir do cargo e das permissões
