# 🏍️ Cadastro de Motos

Um sistema completo de **CRUD de motos**, desenvolvido com foco em boas práticas, testes automatizados e uma interface amigável. A aplicação permite o cadastro, edição, exclusão e listagem de motos.

## 🚀 Tecnologias Utilizadas

### Backend
- ☕ **Java 21**  
- 🌱 **Spring Boot**  
- 🛢️ **PostgreSQL**  
- ✅ **SonarQube**

### Frontend neste link -> [crud-motos-front](https://github.com/gontww/crud-motos-front) 
- 🌐 **Vue.js 3**  
- 🎨 **Element UI** (framework de componentes)

### Testes
- 🧪 **Cypress**

---

## 🖥️ Como Rodar a Aplicação

A aplicação não requer nenhuma configuração especial para ser executada, basta seguir os passos abaixo:

### Pré-requisitos

- [Java 21+](https://adoptopenjdk.net/)
- [Node.js](https://nodejs.org/) (v18+)
- [PostgreSQL](https://www.postgresql.org/) (15)
- [Maven](https://maven.apache.org/)
- [Vue CLI](https://cli.vuejs.org/)

### 1. Banco de Dados

Crie um banco de dados no PostgreSQL:

```sql
CREATE DATABASE crud;
```
---

### 2. Backend

```bash
# Acesse a pasta do backend
cd backend

# Compile e execute
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

### 3. Frontend

```bash
# Acesse a pasta do frontend
cd frontend

# Instale as dependências
npm install

# Execute o servidor de desenvolvimento
npm run dev
```

O frontend estará disponível em: `http://localhost:5137`

---

### 4. Testes E2E com Cypress

```bash
# Na pasta do frontend
npm run test:e2e
```

---

### 5. Análise de Código com SonarQube

Garanta que o SonarQube esteja rodando localmente e execute:

```bash
./mvnw sonar:sonar \
  -Dsonar.projectKey=cadastro-motos \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=SEU_TOKEN
```

---

## 👨‍💻 Desenvolvido por

Augusto Antonio Costa Gontijo - Bruno de Carvalho Souza - João Marus Leite Silva
