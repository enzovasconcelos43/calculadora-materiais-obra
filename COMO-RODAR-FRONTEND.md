# Como rodar o projeto (Front-end JSF + Back-end)

Este projeto agora tem **back-end (cálculos + API) e front-end (telas) juntos**,
rodando com **um único comando**. As telas são feitas em **Jakarta Faces (JSF)**,
integradas ao Spring Boot pela biblioteca **JoinFaces**.

## 1. O que você precisa ter instalado

- **Java 17** (ou 21). Para conferir, abra o terminal e digite:
  ```bash
  java -version
  ```
  Se aparecer "17" (ou maior), está certo.
- **Maven** (o comando `mvn`). Para conferir:
  ```bash
  mvn -version
  ```
  Se não tiver, você pode usar o Maven que vem embutido nas IDEs (IntelliJ,
  Eclipse, VS Code) ou instalar pelo site oficial do Maven.

## 2. Rodar a aplicação

No terminal, dentro da pasta do projeto (onde fica o arquivo `pom.xml`):

```bash
mvn spring-boot:run
```

Na primeira vez o Maven vai **baixar as dependências** (pode demorar alguns
minutos). Quando aparecer uma linha como `Started ObraApplication`, está pronto.

## 3. Abrir no navegador

| O que | Endereço |
|-------|----------|
| Tela inicial (front-end) | http://localhost:8080 |
| Calcular concreto | http://localhost:8080/concreto.xhtml |
| Calcular tijolos | http://localhost:8080/tijolos.xhtml |
| Consultar orçamentos | http://localhost:8080/consulta.xhtml |
| Banco de dados (H2) | http://localhost:8080/h2-console |
| API REST (back-end) | http://localhost:8080/swagger-ui/index.html |

No **H2 Console**, use:
- JDBC URL: `jdbc:h2:mem:obra_db`
- Usuário: `sa`
- Senha: `senha`

## 4. Como usar as telas

1. Em **Concreto** ou **Tijolos**, preencha o nome do cliente e os dados da obra.
2. Use **"+ Adicionar parede"** para incluir quantas paredes quiser.
3. Clique em **"Calcular orçamento"**. O resultado aparece e o orçamento é salvo
   com um número (ex.: `ORC-20260629-0001`).
4. Em **Consultar**, busque pelo número ou pelo nome do cliente.

> **Importante:** digite decimais com **ponto** (ex.: `0.30`, `2.8`), igual aos
> exemplos do back-end.

## 5. Parar a aplicação

No terminal, pressione **Ctrl + C**.

## 6. Observação sobre o banco

O banco H2 é "em memória": os orçamentos somem quando você para a aplicação.
Isso é normal e suficiente para a atividade. Se quiser que eles fiquem salvos
mesmo após reiniciar, dá para trocar para um H2 em arquivo (me avise que eu
te mostro como).
