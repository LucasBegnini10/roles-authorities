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
| id | Long |
| name | String |
| description | String |

## Authorities

Representará nossos privilégios no sistema.

| Propriedade | Tipo |
| --- | --- |
| id | Long |
| name | String |
| description | String |

