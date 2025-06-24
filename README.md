# ⚽ Sistema de Gerenciamento de Jogos e Campeonatos

Projeto desenvolvido como parte da **AV3 da disciplina Desenvolvimento de Aplicações Corporativas 2024.2**.  
Este sistema web permite o gerenciamento completo de **usuários**, **campeonatos** e **jogos**, com funcionalidades como **cadastro**, **login**, **edição**, **exclusão**, **filtros personalizados** e **resumos estatísticos**.

> 🔧 Tecnologias utilizadas: **JSF**, **Primefaces**, **Hibernate**, **Java EE**, **PostgreSQL**

---

## 🖼️ Telas do Sistema

### 🔐 Tela de Login
![Tela de Login](images/login.png)

### 👤 Cadastro de Usuário
![Cadastro de Usuário](images/cadastro_usuario.png)

### 🏠 Menu Principal
![Menu Home](images/opcoes.png)

### 🏆 Cadastro de Campeonato
![Cadastro de Campeonato](images/cadastro_campeonato.png)

### 🎮 Cadastro de Jogo
![Cadastro de Jogo](images/cadastro_jogo.png)

### 📋 Listagem de Jogos
![Listagem de Jogos](images/listagem.png)

### 🔎 Filtro de Jogos
![Filtro de Jogos](images/filtro.png)

### 👥 Listagem de Usuários
![Listagem de Usuários](images/listagem_usuarios.png)

---

## 🚀 Funcionalidades Implementadas

### 👤 Gestão de Usuários
- Classe `Usuario` com `id`, `login`, `nome`, `senha`
- Cadastro, login e listagem de usuários
- Página `login.xhtml` com autenticação no banco de dados
- Redirecionamento ao menu `opcoes.xhtml` após login

### 🏆 Gestão de Campeonatos e Jogos
- Classe `Campeonato`: `id`, `nome`
- Classe `Jogo`: `id`, `dataPartida`, `dataCadastro`, `time1`, `time2`, `campeonato`, `golsTime1`, `golsTime2`
- Relacionamento entre `Campeonato` e `Jogo`
- Validação: impedir cadastro de jogos com times iguais
- DAO com métodos para salvar, editar, excluir e listar

### 📥 Cadastro de Dados
- `cadastro_campeonato.xhtml`: Cadastro de campeonatos
- `cadastro_jogo.xhtml`: Cadastro de jogos com data atual automática

### 📊 Listagem e Resumo
- `listagem.xhtml`: Exibe jogos com dataTable
- Funções:
  - Editar
  - Excluir
  - Gerar resumo (vitórias, derrotas, empates, saldo de gols)

### 🔍 Filtro por Time
- `filtro.xhtml`: Pesquisa jogos por time
- Utiliza `NamedQuery` no banco de dados

### 🧠 Classe `JogoBean`
- Controla a lógica entre front-end e back-end
- Métodos: salvar, listar, buscar, editar, excluir

---

## 🌐 Rotas do Sistema

| Página                       | Rota                                                                 |
|-----------------------------|----------------------------------------------------------------------|
| 🔐 Login                    | [`/login.xhtml`](http://localhost:8080/DAC-AV3/login.xhtml)           |
| 👤 Cadastro de Usuário      | [`/cadastro_usuario.xhtml`](http://localhost:8080/DAC-AV3/cadastro_usuario.xhtml) |
| 🏠 Menu Principal           | [`/opcoes.xhtml`](http://localhost:8080/DAC-AV3/opcoes.xhtml)         |
| 🏆 Cadastro de Campeonato   | [`/cadastro_campeonato.xhtml`](http://localhost:8080/DAC-AV3/cadastro_campeonato.xhtml) |
| 🎮 Cadastro de Jogo         | [`/cadastro_jogo.xhtml`](http://localhost:8080/DAC-AV3/cadastro_jogo.xhtml) |
| 📋 Listagem de Jogos        | [`/listagem.xhtml`](http://localhost:8080/DAC-AV3/listagem.xhtml)     |
| 🔎 Buscar Jogos por Time    | [`/filtro.xhtml`](http://localhost:8080/DAC-AV3/filtro.xhtml)         |
| 👥 Listagem de Usuários     | [`/listagem_usuarios.xhtml`](http://localhost:8080/DAC-AV3/listagem_usuarios.xhtml) |

---

## ⚙️ Como Rodar o Projeto

### ✅ Requisitos

- ☕ JDK 11 ou superior  
- 🌐 Apache Tomcat 9+  
- 🐘 PostgreSQL (banco: `campeonato_db`)  
- 💡 IDE como Eclipse ou IntelliJ

### 📦 Passos para Execução

1. **Clone o repositório**
   ```bash
   git clone https://github.com/Lucas-Sousa-Rocha/DAC-AV3.git
   cd DAC-AV3

2. **Importe o projeto na IDE**

3. **Configure o persistence.xml**

4. **Crie o banco de dados no PostgreSQL**

CREATE DATABASE campeonato_db;

5. **Execute o projeto no servidor Tomcat**

## 🤝 Contribuindo

Sinta-se à vontade para abrir um Pull Request com melhorias, sugestões ou correções de bugs.
Toda contribuição é bem-vinda! 💡

## 👨‍💻 Autor

Lucas Sousa Rocha
Estudante de Desenvolvimento de Aplicações Corporativas – 2024.2
🔗 GitHub

## 📚 Licença

Este projeto é apenas para fins acadêmicos. Todos os direitos reservados à disciplina DSW 2024.2.