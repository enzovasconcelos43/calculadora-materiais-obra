# Calculadora de Materiais para Obra Residencial

Projeto acadêmico desenvolvido para a disciplina de **Desenvolvimento de Sistemas** — UniCEUB.

Sistema completo com back-end em Spring Boot e front-end em **Jakarta Faces**, capaz de calcular o consumo de materiais para obras residenciais e salvar os orçamentos gerados no banco de dados.

## Funcionalidades

- Cálculo do volume de concreto para vigas baldrame
- Cálculo da quantidade de tijolos (com desconto de portas e janelas)
- Persistência de orçamentos no banco de dados (H2)
- Busca de orçamentos por número ou nome do usuário
- Front-end completo em Jakarta Faces (JoinFaces + Spring Boot)
- API REST documentada via Swagger
- Testes automatizados com JUnit

## Tecnologias

- Java 21
- Spring Boot 3.2.5
- Jakarta Faces 4.0 (via JoinFaces 5.2.7)
- Spring Data JPA + Hibernate
- Banco de dados H2 (em memória)
- Maven
- JUnit + MockMvc

## Como rodar

Pré-requisitos: Java 17 ou superior e Maven 3.6 ou superior.

mvn spring-boot:run

Aguarde aparecer Started ObraApplication no terminal.

## Telas disponíveis

- Início: http://localhost:8080
- Cálculo de Concreto: http://localhost:8080/concreto.xhtml
- Cálculo de Tijolos: http://localhost:8080/tijolos.xhtml
- Consultar Orçamentos: http://localhost:8080/consulta.xhtml
- Console do Banco H2: http://localhost:8080/h2-console
- Documentação da API: http://localhost:8080/swagger-ui/index.html

Atenção: use ponto para decimais (ex.: 0.30 e nao 0,30)

## Endpoints da API REST

POST /api/materiais/concreto
POST /api/materiais/tijolos

Exemplos de requisição disponíveis na pasta /exemplos.

## Executar os testes

mvn test

O plano de teste completo está no arquivo PLANO-DE-TESTE.md.

Desenvolvido por Enzo Vasconcelos — Ciência da Computação, UniCEUB
