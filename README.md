# AgendaZap — Agendamento por WhatsApp para clínicas e salões

![Status](https://img.shields.io/badge/status-estudo%2Fdesafio-blue)

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4-6DB33F?logo=springboot&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=white) ![Flyway](https://img.shields.io/badge/Flyway-CC0200?logo=flyway&logoColor=white)

## 🚀 Evolução: de secretária MVP para agendamento por WhatsApp

**De → Para:** de assistente de agendamento para produto anti-no-show de clínicas e salões.

**Novas funcionalidades:** bot que agenda e reagenda pelo WhatsApp; confirmação e lembrete automático que reduz faltas; painel do estabelecimento com a agenda do dia.

**Mudanças na lógica:** a IA passa a orquestrar o fluxo de agendamento (slots, conflitos); estado de agendamento com confirmação e lembrete temporizado; suporte a múltiplos estabelecimentos.

Um sistema de secretariado virtual inteligente que integra WhatsApp (via Evolution API) com IA (Google Gemini) para automatizar o agendamento de reuniões e gerenciamento de agenda.

## 📋 Descrição

O **Secretariat** é uma aplicação Spring Boot que funciona como uma assistente virtual para a Devloop, permitindo que clientes agendem reuniões diretamente pelo WhatsApp. O sistema utiliza inteligência artificial para entender as mensagens dos usuários e automatizar o processo de agendamento.

## ✨ Funcionalidades

- **🤖 Assistente Virtual Inteligente**: Integração com Google Gemini AI para processamento de linguagem natural
- **📱 Integração WhatsApp**: Conexão via Evolution API para receber e enviar mensagens
- **📅 Agendamento Automático**: Criação automática de reuniões baseada em mensagens de texto
- **📋 Listagem de Reuniões**: Consulta de agendas por usuário
- **💾 Persistência de Dados**: Armazenamento em PostgreSQL com Flyway para migrações
- **🔧 Configuração Flexível**: Sistema de configuração via variáveis de ambiente

## 🛠️ Tecnologias Utilizadas

- **Java 21** - Linguagem principal
- **Spring Boot 3.4.5** - Framework de desenvolvimento
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados
- **Flyway** - Migração de banco de dados
- **Evolution API** - Integração com WhatsApp
- **Google Gemini AI** - Processamento de linguagem natural
- **Docker & Docker Compose** - Containerização
- **Maven** - Gerenciamento de dependências

## 🏗️ Arquitetura

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   WhatsApp      │    │  Evolution API  │    │   Secretariat   │
│   (Cliente)     │◄──►│   (Gateway)     │◄──►│   (Spring Boot) │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                                       │
                                                       ▼
                                              ┌─────────────────┐
                                              │   PostgreSQL    │
                                              │   (Database)    │
                                              └─────────────────┘
                                                       │
                                                       ▼
                                              ┌─────────────────┐
                                              │   Google Gemini │
                                              │   (AI Service)  │
                                              └─────────────────┘
```

## 🚀 Como Funciona

1. **Recebimento de Mensagem**: Usuário envia mensagem no WhatsApp
2. **Processamento IA**: Sistema envia mensagem para Google Gemini AI
3. **Análise de Intenção**: IA identifica se é solicitação de agendamento
4. **Execução de Ação**: Sistema agenda reunião ou lista reuniões existentes
5. **Resposta**: Retorna confirmação ou lista via WhatsApp

## 📦 Pré-requisitos

- Java 21
- Docker e Docker Compose
- Maven 3.6+
- PostgreSQL
- Conta Google Cloud (para Gemini AI)
- Evolution API configurada

## ⚙️ Configuração

### 1. Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto:

```env
# Database
SB_APP_DB_STRING=jdbc:postgresql://localhost:5432/secretariat
SB_APP_DB_USERNAME=postgres
SB_APP_DB_PASSWORD=postgres
SB_APP_SERVER_PORT=8081

# Google Gemini AI
GEMINI_REST_URL=https://generativelanguage.googleapis.com/v1beta/models
GEMINI_MODEL=gemini-1.5-flash
GEMINI_API_KEY=sua_chave_api_gemini

# Evolution API
EVOLUTION_API_URL=http://localhost:8080
MY_APP_EVOLUTION_AUTHENTICATION_API_KEY=sua_chave_evolution
MY_APP_DATABASE_CONNECTION_URI=postgresql://postgres:postgres@localhost:5432/evolution
MY_APP_CACHE_REDIS_URI=redis://localhost:6379
```

### 2. Configuração do Banco de Dados

O sistema utiliza Flyway para migrações automáticas. A tabela `tb_agenda` será criada automaticamente na primeira execução.

### 3. Configuração da Evolution API

Certifique-se de que a Evolution API está configurada e conectada ao WhatsApp.

## 🚀 Executando o Projeto

### Opção 1: Docker Compose (Recomendado)

```bash
# Clone o repositório
git clone <url-do-repositorio>
cd devloop-secretariat-main

# Configure as variáveis de ambiente
cp .env.example .env
# Edite o arquivo .env com suas configurações

# Execute com Docker Compose
docker-compose up -d
```

### Opção 2: Execução Local

```bash
# Clone o repositório
git clone <url-do-repositorio>
cd devloop-secretariat-main

# Configure as variáveis de ambiente
export SB_APP_DB_STRING=jdbc:postgresql://localhost:5432/secretariat
export SB_APP_DB_USERNAME=postgres
export SB_APP_DB_PASSWORD=postgres
export SB_APP_SERVER_PORT=8081
export GEMINI_REST_URL=https://generativelanguage.googleapis.com/v1beta/models
export GEMINI_MODEL=gemini-1.5-flash
export GEMINI_API_KEY=sua_chave_api_gemini
export EVOLUTION_API_URL=http://localhost:8080

# Execute a aplicação
./mvnw spring-boot:run
```

## 📱 Como Usar

### Agendando uma Reunião

Envie uma mensagem no WhatsApp no formato:

```
Agende uma reunião no dia 07/05/2025 às 14:00 com o assunto Discutir metas
```

### Listando Reuniões

Para ver suas reuniões agendadas, envie:

```
Liste minhas reuniões
```

## 🔧 Endpoints da API

### Webhook WhatsApp
- **POST** `/chat/wh` - Recebe mensagens do WhatsApp via Evolution API

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/devloop/secretariat/
│   │   ├── client/          # Clientes para APIs externas
│   │   ├── config/          # Configurações da aplicação
│   │   ├── controller/      # Controladores REST
│   │   ├── domain/          # Entidades do domínio
│   │   ├── dto/             # Objetos de transferência de dados
│   │   ├── repository/      # Repositórios de dados
│   │   ├── service/         # Lógica de negócio
│   │   └── SecretariatApplication.java
│   └── resources/
│       ├── application.yaml # Configuração da aplicação
│       ├── db/migration/    # Migrações do banco
│       └── gemini/          # Configurações do Gemini AI
└── test/                    # Testes unitários
```

## 🧪 Testes

```bash
# Executar testes
./mvnw test

# Executar testes com cobertura
./mvnw test jacoco:report
```

## 📊 Monitoramento

A aplicação expõe endpoints de health check em:
- **GET** `/actuator/health` - Status da aplicação
- **GET** `/actuator/info` - Informações da aplicação

## 🔒 Segurança

- Todas as chaves de API devem ser configuradas via variáveis de ambiente
- A Evolution API utiliza autenticação por API Key
- O banco de dados PostgreSQL deve ser configurado com credenciais seguras

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👥 Desenvolvedores

- **Zenkai** - Desenvolvimento inicial
- **Voodoo** - Desenvolvimento inicial

## 📞 Suporte

Para suporte, entre em contato com a equipe da Devloop ou abra uma issue no repositório.

---

**Devloop** - Transformando ideias em realidade digital 🚀 
