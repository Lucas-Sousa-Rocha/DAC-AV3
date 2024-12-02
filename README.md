README - AP3 Desenvolvimento de Aplicações Corporativas 2024.2
Descrição do Projeto
Este projeto é uma aplicação web desenvolvida para a disciplina de Desenvolvimento de Aplicações Corporativas 2024.2, com foco em JSF, Primefaces e Hibernate. O objetivo é construir um sistema de gerenciamento de jogos e campeonatos, implementando funcionalidades de cadastro, listagem, edição, exclusão, e resumos de jogos e campeonatos.

Funcionalidades Implementadas
1. Gestão de Usuários
Classe Usuario: Implementada com as variáveis id, login, nome e senha. Criadas as páginas e classes (Bean e Dao) para realizar o cadastro e listagem de usuários.
Página de Login (login.xhtml): Formulário de login que valida as credenciais do usuário com o banco de dados. Se a autenticação for bem-sucedida, o usuário é redirecionado para a página opcoes.xhtml.
Página de Opções (opcoes.xhtml): Menu de navegação com links para todas as páginas do sistema.
2. Gestão de Campeonatos e Jogos
Classe Campeonato: Criada com as variáveis id e nome, representando um campeonato.
Classe Jogo: Inclui as variáveis id, dataPartida, dataCadastro, time1, time2, campeonato, golsTime1, e golsTime2, com relacionamentos entre Campeonato e Jogo.
JogoDAO: Implementados métodos para salvar, editar, excluir e listar jogos utilizando EntityManager.
3. Cadastro de Campeonatos e Jogos
Página de Cadastro de Campeonato (cadastro_campeonato.xhtml): Formulário para cadastrar campeonatos no sistema, com Bean e Dao associados.
Página de Cadastro de Jogo (cadastro_jogo.xhtml): Formulário para cadastrar jogos. A data de cadastro é preenchida automaticamente com a data e hora atuais. Validação para evitar a inserção de jogos com time1 igual a time2.
Página de Listagem de Jogos (listagem.xhtml): Exibição de jogos cadastrados em uma dataTable, com links para editar ou excluir registros. Funcionalidade de resumo para exibir as estatísticas do time (pontuação, vitórias, derrotas, etc.).
Função de Edição e Exclusão: Implementação de funcionalidades de edição e exclusão de jogos diretamente na página de listagem.
4. Filtro e Resumo de Jogos
Página de Filtro (filtro.xhtml): Filtro de jogos por time, exibindo todos os jogos do time selecionado. A pesquisa é realizada utilizando uma NamedQuery no banco de dados.
Resumo de Jogos: Na página listagem.xhtml, foi implementado um botão para gerar o resumo dos times, exibindo informações como: pontuação, número de vitórias, derrotas, empates, gols marcados, gols sofridos e saldo de gols.
5. Classe JogoBean
Classe JogoBean: Responsável pela comunicação e execução das funcionalidades nas páginas criadas, como salvar jogos, listar jogos, realizar buscas e interações com o banco de dados.
Tecnologias Utilizadas
JSF (JavaServer Faces): Framework para a construção da interface de usuário.
Primefaces: Biblioteca de componentes UI para JSF, utilizada para estilização e componentes interativos.
Hibernate: Framework ORM (Object-Relational Mapping) para interação com o banco de dados.
Java EE: Plataforma para desenvolvimento de aplicações empresariais em Java.
PostgreSQL: Banco de dados utilizado para persistência de informações.

Como Rodar o Projeto
Requisitos
JDK 11 ou superior
Apache Tomcat 9 ou superior
PostgreSQL configurado localmente ou em um servidor
Passos para Execução
Clone o repositório:
git clone https://github.com/Lucas-Sousa-Rocha/DAC-AV3.git
Importe o projeto em uma IDE como Eclipse ou IntelliJ IDEA.
Configure o banco de dados PostgreSQL com o nome campeonato_db e as credenciais adequadas.
Realize a configuração do persistence.xml para conectar ao banco de dados.
Execute o servidor Apache Tomcat.
Contribuindo
Sinta-se à vontade para abrir um Pull Request para sugestões de melhorias ou correções de bugs. Qualquer contribuição será bem-vinda!

Autor
[Lucas Sousa] - Estudante de Desenvolvimento de Aplicações Corporativas 2024.2

Este README fornece uma descrição detalhada do projeto e como configurá-lo, além de descrever todas as funcionalidades que foram implementadas no sistema de gerenciamento de jogos e campeonatos.
 
