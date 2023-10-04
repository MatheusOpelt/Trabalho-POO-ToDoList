Documentação do Sistema

Nome do Sistema: Gerenciador de Tarefas (To-Do List)
Versão: 1.0
Data de Criação: 10/2023
Última Atualização: 03/10/2023
Autores: Matheus Eduardo Opelt; Giovanna Campos Cordeiro

Resumo
Um sistema simples de gerenciamento de tarefas que utiliza Java, JDBC e Vaadin.

1. Introdução
   O Sistema de Gerenciamento de Tarefas é uma aplicação que permite aos usuários registrar, editar, excluir e consultar tarefas de forma eficiente. Tornando possível o controle de tarefas diárias de maneira ágil.
   A funcionalidade de registro de tarefas possibilita aos usuários adicionar novas tarefas especificando por título, descrição e data de vencimento. Já a capacidade de editar tarefas permite aos usuários fazer alterações em tarefas existentes, como modificar o título, a descrição ou a data de vencimento.
   Com a exclusão de tarefas, os usuários podem remover tarefas da lista quando concluídas ou não necessárias. E a funcionalidade de consultar tarefas permite a busca e visualização de tarefas em status "Concluída" e/ou "Incompleta".
   Portanto, o sistema fornece uma solução simples, porém eficaz, para o gerenciamento de tarefas, ajudando os usuários a manterem-se organizados e produtivos em suas atividades diárias.

2. Requisitos do Sistema
   2.1. Requisitos Funcionais
   RF_001: Os usuários devem ser capazes de criar novas tarefas, especificando um título, descrição e data de vencimento.
   RF_002: Os usuários devem poder editar informações de tarefas existentes, incluindo título, descrição e data de vencimento.
   RF_003: Os usuários devem poder excluir tarefas da lista quando não forem mais necessárias.
   RF_004: Os usuários devem ser capazes de atualizar o status de uma tarefa para refletir seu progresso (“Concluída” ou “Incompleta”).
   RF_005: Deve haver uma lista de tarefas visível para os usuários, exibindo todas as tarefas cadastradas.
   RF_006: Os usuários devem poder ordenar a lista de tarefas por diferentes critérios, como data de vencimento ou status.
   RF_007: Os usuários devem poder consultar tarefas com base em palavras-chave ou outros critérios relevantes.
   RF_008: O sistema deve manter um histórico de atividades para cada tarefa, registrando edições, exclusões e atualizações de status.
   RF_009: O sistema deve permitir que vários usuários acessem e gerenciem suas próprias listas de tarefas.

2.2. Requisitos Não Funcionais
RNF_001: O sistema deve ser responsivo, com tempos de carregamento rápidos para garantir uma experiência do usuário fluida, mesmo em conexões de internet lentas.
RNF_002: O sistema deve ser capaz de lidar com um aumento gradual de usuários e tarefas sem degradação significativa de desempenho.
RNF_003: As informações de tarefas e dados de usuário devem ser armazenados de forma segura e protegidos contra acesso não autorizado.
RNF_004: O sistema deve estar disponível 24 horas por dia, 7 dias por semana, com um tempo de inatividade planejado mínimo para manutenção.
RNF_005: O sistema deve ser compatível com uma variedade de navegadores web modernos, como Chrome, Firefox, Safari e Edge.
RNF_006: O sistema deve ser acessível a pessoas com deficiências, seguindo as diretrizes de acessibilidade da WCAG (Web Content Accessibility Guidelines).
RNF_007: O sistema deve ser localizado e suportar múltiplos idiomas para atender a uma base de usuários diversificada.
RNF_008: Deve ser implementado um sistema de backup regular e eficaz para proteger os dados do usuário contra perdas acidentais ou corrupção.
RNF_009: O sistema deve aderir a regulamentações de privacidade, como o GDPR, e garantir a privacidade e a segurança dos dados do usuário.
RNF_010: O sistema deve ser projetado de forma a facilitar atualizações e manutenção contínua sem interromper a operação.
RNF_011: O sistema deve ter tempos de resposta aceitáveis para todas as interações do usuário, como adicionar, editar e excluir tarefas.
RNF_012: O sistema deve garantir a integridade dos dados, prevenindo erros de gravação e corrupção de dados.

3. Arquitetura do Sistema
   3.1. Diagrama UML

4. Configuração
   Clone este repositório.
   Configure seu banco de dados MySQL (veja `database.sql`).
   Configure as credenciais de banco de dados em `src/main/resources/application.properties`.
   Execute o aplicativo.

5. Estrutura do Projeto
   `src/main/java`: Código-fonte Java.
   `src/main/resources`: Recursos, incluindo arquivos de configuração.
   `database.sql`: Script SQL para criar o banco de dados.

6. Utilização
   6.1. Funcionalidades Principais
   Adicionar, editar, excluir e consultar tarefas.
   Marcar tarefas como concluídas ou não.
   Listar todas as tarefas.

6.2. Forma de Utilização
Acesse o aplicativo em [http://localhost:8080].
Use as telas para adicionar, editar e gerenciar suas tarefas.

6.3. Histórias de Usuário
Como usuário, eu quero poder adicionar tarefas à minha lista de afazeres.
Como usuário, eu quero poder marcar tarefas como concluídas.
Como usuário, eu quero poder editar o título e a descrição de uma tarefa.
Como usuário, eu quero poder excluir tarefas da minha lista.
Como usuário, eu quero poder consultar uma lista de todas as minhas tarefas.
Como usuário, eu quero poder filtrar tarefas por status (concluídas/incompletas).
