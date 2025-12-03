# Explicação dos Módulos com Graylog, MongoDB e ORM

## Módulo 1 — API de Pessoas
Neste módulo foi desenvolvida uma API REST para cadastro e gerenciamento de pessoas.
A aplicação utiliza MongoDB como banco de dados, aproveitando sua estrutura flexível para armazenar documentos
com ID, nome, data de nascimento e status ativo.

A persistência dos dados é feita através de um ORM compatível com MongoDB, garantindo uma camada de abstração
entre a API e o banco. Além disso, toda a aplicação envia logs para o Graylog, facilitando o monitoramento
centralizado e a visualização de eventos da API.

O módulo foi estruturado separando domínio e infraestrutura, evitando acoplamento com o framework e mantendo
as regras de negócio isoladas.

---

## Módulo 2 — API de Login + API Gateway
Este módulo contém dois componentes principais:

### API de Login
- Gerencia usuários e autenticação.
- Utiliza MongoDB + ORM para armazenar credenciais.
- Gera JWT para acesso autenticado às demais APIs.
- Implementa Basic Auth → Bearer Token.

### API Gateway
- Centraliza o acesso às APIs do projeto.
- Exponde rotas da API de Pessoas e da API de Login.
- Valida o JWT antes de liberar acesso aos endpoints protegidos.
- Organiza toda a entrada de tráfego no ecossistema das aplicações.

Esse módulo também concentra parte da orquestração no Docker, incluindo containers do MongoDB, do Graylog e das APIs.

---

## Módulo 3 — Kafka Producer + Lambda Listener
Neste módulo foi implementado:

### Kafka Producer (na API de Login)
Sempre que a rota de esquecimento de senha é chamada, o producer envia uma mensagem para o tópico 'forget',
contendo informações necessárias para o fluxo de recuperação.

### Lambda Function Listener
- Implementado como uma aplicação Java/Spring Boot empacotada para execução em formato serverless.
- Escuta o tópico 'forget'.
- Quando recebe uma mensagem, imprime no console qual usuário pediu redefinição de senha.

Esse módulo também tem sua imagem Docker gerada e publicada automaticamente no Docker Hub via GitHub Actions.

---

## Módulo 4 — Kafka Consumers com Resiliência
Neste módulo foi construído um consumer Kafka adicional, independente da lambda:

### Consumer Replicado
- Dois consumidores idênticos, rodando em contêineres separados.
- Compartilham o mesmo groupId.
- Quando uma mensagem chega no tópico 'forget', apenas um deles processa — garantindo escalabilidade.
- Caso um consumidor caia, o outro assume automaticamente.

Cada instância também registra logs no Graylog, mantendo observabilidade centralizada.

---

## Pós Desenvolvimento — CI/CD com Docker Hub
Cada módulo possui seu próprio workflow no GitHub Actions.
Os pipelines:

- constroem a imagem Docker
- fazem login no Docker Hub
- publicam a imagem automaticamente

Isso deixa o ambiente pronto para ser orquestrado via docker-compose ou Kubernetes no futuro.

---

## Outras Informações
- Java 25 + Spring Boot 4.0.0
- Testes realizados com Insomnia
- Toda a arquitetura preparada para rodar via Docker

---

## Como executar
1. Clone todas as branches.
2. Dentro de cada módulo, execute:
   docker build -t <nome_da_branch>:latest .
3. Dentro do módulo pmp-gateway, execute:
   docker-compose up -d
