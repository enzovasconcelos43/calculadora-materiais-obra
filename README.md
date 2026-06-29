# 💊 MedLembre — Controle de Medicamentos

![CI](https://github.com/SEU_USUARIO/medlembre/actions/workflows/ci.yml/badge.svg)

> Aplicação CLI para controle de medicamentos e horários, desenvolvida para auxiliar idosos, cuidadores e pacientes crônicos a não esquecerem seus remédios.

---

## 📋 Sumário

- [O Problema](#-o-problema)
- [A Solução](#-a-solução)
- [Público-alvo](#-público-alvo)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias](#-tecnologias)
- [Instalação](#-instalação)
- [Como Executar](#-como-executar)
- [Como Rodar os Testes](#-como-rodar-os-testes)
- [Como Rodar o Lint](#-como-rodar-o-lint)
- [Versão](#-versão)
- [Autor](#-autor)

---

## 🔍 O Problema

O esquecimento de medicamentos é uma das principais causas de complicações de saúde em pacientes crônicos e idosos. Segundo estudos da área de saúde pública, cerca de 50% dos pacientes com doenças crônicas não aderem corretamente ao tratamento medicamentoso. Isso resulta em internações desnecessárias, piora do quadro clínico e sobrecarga do sistema de saúde.

## 💡 A Solução

O **MedLembre** é uma aplicação de linha de comando (CLI) que permite cadastrar medicamentos com seus respectivos horários e dosagens, acompanhar quais já foram tomados no dia e manter uma rotina organizada de tratamento. Os dados são salvos localmente em arquivo JSON, garantindo persistência entre sessões.

## 👥 Público-alvo

- Idosos que tomam múltiplos medicamentos diariamente
- Cuidadores responsáveis por administrar remédios a dependentes
- Pacientes crônicos que precisam organizar sua rotina de tratamento
- Familiares que auxiliam parentes em tratamento

## ✅ Funcionalidades

| Funcionalidade | Descrição |
|---|---|
| Cadastrar medicamento | Registra nome, horário (HH:MM) e dosagem |
| Listar medicamentos | Exibe todos os medicamentos com status do dia |
| Marcar como tomado | Indica que um medicamento foi administrado |
| Reiniciar status | Reseta todos os status para um novo dia |
| Remover medicamento | Exclui um medicamento do cadastro |
| Persistência | Dados salvos em JSON entre sessões |
| Validação | Impede horários inválidos e cadastros duplicados |

## 🛠️ Tecnologias

- **Linguagem:** Python 3.10+
- **Testes:** [pytest](https://pytest.org)
- **Linting:** [ruff](https://docs.astral.sh/ruff/)
- **CI:** GitHub Actions
- **Armazenamento:** JSON (arquivo local)
- **Versionamento:** Semântico (SemVer)

---

## 🚀 Instalação

### Pré-requisitos

- Python 3.10 ou superior instalado
- `pip` disponível no terminal

### Passo a passo

**1. Clone o repositório:**

```bash
git clone https://github.com/SEU_USUARIO/medlembre.git
cd medlembre
```

**2. (Recomendado) Crie um ambiente virtual:**

```bash
python -m venv .venv

# No Windows:
.venv\Scripts\activate

# No Linux/macOS:
source .venv/bin/activate
```

**3. Instale o projeto e suas dependências de desenvolvimento:**

```bash
pip install -e ".[dev]"
```

---

## ▶️ Como Executar

Com o ambiente virtual ativado, na pasta raiz do projeto:

```bash
python main.py
```

Você verá o menu principal:

```
==================================================
       💊  MedLembre — Controle de Medicamentos
==================================================

[1] Cadastrar medicamento
[2] Listar medicamentos
[3] Marcar como tomado
[4] Reiniciar status do dia
[5] Remover medicamento
[0] Sair
--------------------------------------------------
Escolha uma opção:
```

---

## 🧪 Como Rodar os Testes

```bash
pytest --tb=short
```

Saída esperada:

```
========================= test session starts ==========================
collected 15 items

tests/test_medication.py ..............                           [100%]

========================== 15 passed in 0.12s ==========================
```

---

## 🔍 Como Rodar o Lint

```bash
ruff check .
```

Se o código estiver sem problemas:

```
All checks passed!
```

---

## 🔖 Versão

**1.0.0** — Consulte o [CHANGELOG.md](CHANGELOG.md) para o histórico de mudanças.

---

## 👤 Autor

**[SEU NOME COMPLETO]**  
Repositório: [https://github.com/SEU_USUARIO/medlembre](https://github.com/SEU_USUARIO/medlembre)
