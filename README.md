# 💬 Real-Time Chat API

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![MongoDB](https://img.shields.io/badge/MongoDB-6.0-green?logo=mongodb)
![Kafka](https://img.shields.io/badge/Apache%20Kafka-7.5-black?logo=apachekafka)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?logo=docker)


> **⚠️ STATUS:** Em desenvolvimento ativo (PoC). Focado em demonstrar arquitetura event-driven e integração híbrida REST/WebSocket.

## 📖 Sobre o Projeto

Uma API Backend de alta performance para sistemas de chat, desenhada para escalar. Diferente de chats tradicionais que apenas "repassam" mensagens, esta aplicação utiliza uma arquitetura **Event-Driven** com **Apache Kafka**.

Isso garante que o processamento de mensagens (persistência, notificações, análise) seja desacoplado da entrega em tempo real, evitando gargalos de I/O bloqueante no servidor principal.

### ✨ Principais Features
* **Comunicação Híbrida:** REST para gestão de recursos e WebSocket (STOMP) para tempo real.
* **Persistência NoSQL:** Armazenamento de histórico de conversas e usuários no MongoDB.
* **Processamento Assíncrono:** Mensagens enviadas são processadas por um cluster Kafka antes da entrega.
* **Testes de Integração Reais:** Uso de **Testcontainers** para garantir que os testes rodem em bancos de dados reais (Docker), não mocks.
* **CI/CD:** Pipeline de Build e Testes automatizado via GitHub Actions.

---

## 🛠 Tech Stack & Arquitetura

O projeto segue o padrão **Package-by-Feature** para alta coesão e modularidade.

* **Core:** Java 21, Spring Boot 3.
* **Messaging:** Apache Kafka, Zookeeper.
* **Database:** MongoDB.
* **Real-Time:** Spring WebSocket (STOMP Protocol).
* **Qualidade:** JUnit 5, Mockito, Testcontainers.
* **Docs:** SpringDoc OpenAPI (Swagger).

### 📂 Estrutura de Pastas

```text
src/main/java/com/example/demo
├── chat/                     # Domínio de Salas de Conversa
│   ├── controller/           # Endpoints REST (Gerenciamento de Chats)
│   ├── dto/                  # DTOs de Request/Response
│   ├── service/              # Regras de Negócio (Criação, Busca)
│   └── repository/           # Queries MongoDB para Chats
├── message/                  # Domínio de Mensagens
│   ├── controller/           # Recebimento de mensagens via API
│   ├── service/              # Producer Kafka e Persistência
│   └── mapper/               # Conversão DTO <-> Entity
├── user/                     # Domínio de Usuários
│   ├── controller/           # CRUD de Usuários
│   ├── dto/                  # Login, Criação e Atualização
│   └── service/              # Regras de validação de usuários
├── infrastructure/
│   └── messaging/            # Consumer Kafka -> WebSocket Dispatcher
└── config/
    ├── security/             # Configurações de Segurança (UserContext)
    ├── GlobalExeptionHandler # Tratamento centralizado de erros
    └── WebSocketConfig       # Configuração do Broker STOMP
⚡ Como Rodar (Ambiente Local)
Pré-requisitos
Docker & Docker Compose (Essencial).

Java 21 (Apenas se quiser rodar fora do Docker).

1. Subir Infraestrutura
O arquivo docker-compose.yml provisiona o MongoDB e o cluster Kafka/Zookeeper automaticamente.

docker-compose up -d

Aguarde alguns segundos até que os containers meu-mongo, meu-kafka e meu-zookeeper estejam saudáveis.

2. Rodar a Aplicação
Utilize o wrapper do Maven para iniciar o Spring Boot:

Linux / Mac:

Bash

./mvnw spring-boot:run

Windows:

Bash

./mvnw.cmd spring-boot:run

3. Acessar Documentação
Com a API rodando, acesse o Swagger UI para testar os endpoints: 👉 http://localhost:8080/swagger-ui.html

🔌 Referência da API (Endpoints)

WebSocket (STOMP)
Endpoint de Conexão: /ws

Tópicos de Assinatura: /topic/chat/{chatId}

Prefixo de Envio: /app

REST APIUsuários (/api/user)

POST    /  Criar novo usuário
GET   /  {id} Buscar detalhes do usuário
PUT   / {id} Atualizar dados
DELETE / {id} Remover usuário

Chats (/api/chat)

POST / Iniciar conversa privada
GET / search Buscar chat por email do destinatário
DELETE /{id} Apagar histórico de chat

Mensagens (/api/messages)

POST / Envia mensagem (Dispara evento Kafka)
GET /{id} Busca mensagem específica

🧪 Testes e Qualidade
 Utilizamos Testcontainers para subir um MongoDB descartável via Docker durante a execução dos testes.

Para rodar a bateria de testes:

 ./mvnw test

🚧 Roadmap
[ ] Segurança: Implementar Filtro JWT (Atualmente permitAll no SecurityConfig), ja esta em andamento.

[ ] Refactor: Mudanças e melhorias a serem feitas e funções a serem finalizadas. 

[ ] Feature: Mais testes Unitarios e de integração. Suporte a chat em grupo e envio de arquivos.
