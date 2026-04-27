# Calculadora de Materiais para Obra Residencial

Projeto em Java + Spring Boot para atender a atividade avaliativa de Desenvolvimento de Sistemas.

## Funcionalidades
- Cálculo do volume de concreto para vigas baldrame
- Cálculo da quantidade de tijolos
- API REST organizada em camadas

- Teste automatizado básico

## Tecnologias
- Java 17
- Spring Boot
- Spring Web
- Spring Validation
- Maven


A aplicação sobe em:
- API: `http://localhost:8080`
- swagger - http://localhost:8080/swagger-ui/index.html


## Endpoints
### 1) Calcular volume de concreto
`POST /api/materiais/concreto`

### 2) Calcular quantidade de tijolos
`POST /api/materiais/tijolos`

