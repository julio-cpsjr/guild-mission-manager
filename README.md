
# 🏰 Guild Mission Manager

Sistema backend para gerenciamento de **missões de uma guilda**, inspirado no universo de *Sword Art Online*, desenvolvido com **Java**, **JDBC** e **SQLite**, com foco em fundamentos sólidos de backend e regras de negócio.

> Apesar da inspiração em um universo fictício, este projeto modela **sistemas reais de ordens de serviço**, aplicando boas práticas de engenharia de software.

---

## 🎯 Objetivo do Projeto

O objetivo deste projeto é consolidar **conceitos essenciais de backend** utilizando Java, sem frameworks, com ênfase em:

* Arquitetura limpa
* Separação de responsabilidades
* Implementação de regras de negócio
* Persistência em banco de dados relacional
* Estrutura profissional de projeto

Este projeto representa uma **evolução de Nível 2**, migrando de dados em memória para persistência completa em banco de dados.

---

## 🧩 Conceito do Domínio

| Mundo Real       | Conceito do Projeto            |
| ---------------- | ------------------------------ |
| Ordem de Serviço | Missão da Guilda               |
| Técnico          | Membro da Guilda               |
| Cliente          | NPC / Contratante              |
| Status           | Estado da Missão               |
| SLA              | Dificuldade / Regras da Missão |

---

## 🏗️ Arquitetura do Sistema

O projeto segue uma arquitetura em camadas:

```
br.com.guilda
│
├── model        # Entidades de domínio e enums
├── repository   # Camada de acesso a dados (JDBC + SQL)
├── service      # Regras de negócio e validações
├── db           # Conexão e inicialização do banco de dados
└── ui           # Interface via menu no console
```

### Responsabilidade das Camadas

* **model** → Representação do domínio (entidades e enums)
* **repository** → Operações SQL (sem regras de negócio)
* **service** → Regras de negócio e orquestração
* **ui** → Interação com o usuário via console
* **db** → Conexão com o banco e criação do schema

---

## 🧱 Entidades do Domínio

### 🏰 Guilda

* id
* nome
* nivel
* reputacao

### 🧑‍🤝‍🧑 Membro da Guilda

* id
* nome
* classe (enum)
* nivel
* status (ATIVO / INATIVO)
* guilda

### 📜 Missão

* id
* titulo
* descricao
* dificuldade (enum: D, C, B, A, S)
* status (ABERTA, EM_ANDAMENTO, CONCLUIDA, FALHOU)
* data de abertura
* data de fechamento
* membro responsável
* guilda

---

## 🧠 Regras de Negócio

* Uma missão **não pode ser iniciada sem um membro da guilda atribuído**
* Apenas membros **ATIVOS** podem assumir missões
* Missões com status **CONCLUIDA** ou **FALHOU** não podem ser alteradas
* A reputação da guilda aumenta quando missões são concluídas
* Missões de alta dificuldade exigem nível mínimo do membro
* A data de fechamento só é definida ao concluir ou falhar uma missão

Todas as regras são aplicadas exclusivamente na **camada de service**.

---

## 🗄️ Persistência de Dados

* Banco de dados: **SQLite**
* Método de acesso: **JDBC**
* SQL isolado na camada de repository
* Schema do banco criado automaticamente na inicialização do sistema

---

## 🛠️ Tecnologias Utilizadas

* Java (JDK 17+)
* JDBC
* SQLite
* Maven ou Gradle
* Programação Orientada a Objetos
* Princípios de Clean Code

---

## ▶️ Como Executar

1. Clone o repositório
2. Abra o projeto em sua IDE
3. Execute a classe principal
4. Utilize o menu no console para interagir com o sistema

O arquivo do banco de dados será criado automaticamente na primeira execução.

---

## 🚀 Evoluções Futuras

* Histórico de status das missões
* Ranking de membros da guilda
* Regras de SLA e penalidades
* Testes unitários com JUnit
* API REST com Spring Boot
* Autenticação e autorização

---

## 📌 Observações

Este projeto faz parte de um **roadmap de aprendizado backend**, com foco em construir uma base sólida antes da utilização de frameworks.

---

## 👤 Autor

Desenvolvido por Julio Cesar
Desenvolvedor Backend | Java | Engenharia de Software

---

> *Regras claras, arquitetura sólida e código limpo — até em mundos de fantasia.* ⚔️
