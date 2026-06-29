# Plano de Teste — Calculadora de Materiais para Obra Residencial (Front-end JSF)

**Disciplina:** Desenvolvimento de Sistemas
**Módulo testado:** Front-end Jakarta Faces + persistência de orçamentos

---

## 1. Objetivo

Verificar se as telas em Jakarta Faces calculam corretamente o consumo de
materiais (concreto e tijolos), se cada cálculo gera e **salva um orçamento no
banco de dados**, e se a consulta encontra orçamentos por **número** e por
**nome do usuário**.

## 2. Ambiente de teste

| Item | Valor |
|------|-------|
| Sistema operacional | (preencher) |
| Versão do Java (`java -version`) | 17 (ou superior) |
| Navegador | (preencher) |
| URL base | http://localhost:8080 |

## 3. Casos de teste

> Observação: digite os números decimais com **ponto** (ex.: `0.30`).

### CT01 — Calcular volume de concreto (1 parede)
- **Entrada:** Nome = `Maria`; Altura da viga = `0.30`; Parede P1: comprimento `5.0`, espessura `0.15`.
- **Resultado esperado:** Volume total = **0.2250 m³**; Paredes processadas = **1**; um número de orçamento `ORC-...` é exibido.
- **Cálculo de conferência:** 0.15 × 0.30 × 5.0 = 0.225.

### CT02 — Calcular volume de concreto (2 paredes)
- **Entrada:** Nome = `Maria`; Altura da viga = `0.30`; P1: `5.0` / `0.15`; P2: `4.0` / `0.15`.
- **Resultado esperado:** Volume total = **0.4050 m³**; Paredes processadas = **2**.

### CT03 — Calcular quantidade de tijolos (com porta e janela)
- **Entrada:** Nome = `João`; Largura tijolo `0.30`; Altura tijolo `0.20`; Perda `10`;
  - P1: comprimento `5.0`, altura `2.8`, Janela `1.5` × `1.0`;
  - P2: comprimento `4.0`, altura `2.8`, Porta `0.8` × `2.1`.
- **Resultado esperado:** Área total = **25.2 m²**; Aberturas = **3.18 m²**; Área líquida = **22.02 m²**; Tijolos sem perda = **367**; Tijolos com perda = **404**.

### CT04 — Validação de campos obrigatórios
- **Entrada:** clicar em "Calcular orçamento" sem preencher o nome.
- **Resultado esperado:** mensagem de erro pedindo o nome; nenhum orçamento é salvo.

### CT05 — Adicionar e remover paredes
- **Ação:** adicionar 2 paredes, preencher, remover 1.
- **Resultado esperado:** a tabela atualiza sem recarregar a página e os valores digitados são mantidos.

### CT06 — Consultar orçamento por número
- **Pré-condição:** ter executado o CT01 (anote o número gerado).
- **Entrada:** colar o número na consulta e clicar "Buscar por número".
- **Resultado esperado:** o orçamento correspondente aparece na tabela.

### CT07 — Consultar orçamento por nome
- **Entrada:** digitar `Maria` e clicar "Buscar por nome".
- **Resultado esperado:** todos os orçamentos da Maria aparecem, do mais recente para o mais antigo.

### CT08 — Verificar persistência no banco (H2)
- **Ação:** abrir http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:obra_db`, usuário `sa`, senha `senha`) e executar `SELECT * FROM ORCAMENTO;`.
- **Resultado esperado:** os orçamentos gerados aparecem na tabela.

## 4. Execução e evidências

| Caso | Data | Resultado obtido | Passou? (S/N) | Evidência (print) |
|------|------|------------------|---------------|-------------------|
| CT01 | | | | |
| CT02 | | | | |
| CT03 | | | | |
| CT04 | | | | |
| CT05 | | | | |
| CT06 | | | | |
| CT07 | | | | |
| CT08 | | | | |

> Tire um print de cada tela com o resultado e cole na coluna de evidência (ou
> anexe as imagens junto deste documento na sala online).

## 5. Teste automatizado (back-end)

O projeto também possui um teste automatizado de back-end. Para executá-lo:

```bash
mvn test
```

Esperado: o teste `deveCalcularConcreto` passa (volume 0.2250 para uma parede de
5.0 m e espessura 0.15 m com viga de 0.30 m).
