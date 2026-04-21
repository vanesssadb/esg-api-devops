# ESG API — Spring Boot + Oracle + Docker

API RESTful (ESG) feita em **Java + Spring Boot**, com:
- Autenticação **JWT** (Spring Security)
- Banco **Oracle XE** (via Docker)
- Migrações **Flyway**
- Endpoints ESG: empresas, emissões, agendas de redução, compensações, indicadores, impacto de mitigação.

## 🧩 Requisitos
- Docker e Docker Compose
- (Opcional para desenvolvimento local) JDK 21 e Maven 3.9+

---

## 🚀 Subir tudo com Docker

```bash
docker compose build
docker compose up -d

Oracle: localhost:1521/XEPDB1
(user: APP / pass: app)


API: http://localhost:8080


O Oracle sobe primeiro e cria o usuário APP via
oracle-init/01-create-app-user.sql.
A API aplica as migrações Flyway automaticamente.

🧠 Rodar localmente (sem Docker, opcional)
1️⃣ Sobe apenas o Oracle via Docker:
docker compose up -d oracle

2️⃣ Ajuste variáveis de ambiente (ou no IntelliJ → Edit Configurations → Environment variables):
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//localhost:1521/XEPDB1
SPRING_DATASOURCE_USERNAME=APP
SPRING_DATASOURCE_PASSWORD=app
security.jwt.secret=uma-chave-secreta-bem-grande-para-jwt
security.jwt.expiration=86400000

3️⃣ Execute:
mvn clean package
mvn spring-boot:run


🔐 Segurança


Endpoints POST, PUT, DELETE exigem Bearer Token com role ADMIN


Endpoints GET públicos estão liberados


Login
POST /auth/login
{
  "username": "admin",
  "password": "admin"
}

Resposta:
{ "accessToken": "eyJhbGciOi..." }

Use nas demais requisições:
Authorization: Bearer <accessToken>


🌍 Endpoints principais
Companies


GET /companies — lista empresas (público)


POST /companies — cria empresa (ADMIN)


Emissions


GET /emissions?companyId={id} — lista emissões


POST /emissions — cria emissão (ADMIN)


Reduction Schedules


GET /reduction-schedules — lista agendas


GET /reduction-schedules/{id} — detalhe


POST /reduction-schedules — cria (ADMIN)


PUT /reduction-schedules/{id} — atualiza (ADMIN)


DELETE /reduction-schedules/{id} — remove (ADMIN)


Offsets


GET /offsets — lista offsets (público)


POST /offsets — cria offset (ADMIN)


Indicators


GET /indicators?companyId={id} — mostra totais e saldo líquido


GET /impacto-mitigacao/{id} — impacto estimado de uma agenda



🧪 Testes rápidos (IntelliJ ou Postman)
Use o arquivo requests.http (ou a Collection Postman incluída).
1️⃣ Envie o Login → salva o token
2️⃣ Teste os endpoints com/sem autenticação

📂 Estrutura do projeto
src/
 ├── main/
 │   ├── java/br/com/fiap/atv_cap_8/
 │   │   ├── domain/         → entidades JPA (BigDecimal)
 │   │   ├── repository/     → repositórios JPA
 │   │   ├── security/       → JWT, filtros, configuração
 │   │   └── web/            → controllers REST
 │   └── resources/
 │       ├── db/migration/   → scripts Flyway (V1__*.sql)
 │       └── application.properties
 ├── Dockerfile
 ├── docker-compose.yml
 ├── oracle-init/01-create-app-user.sql
 ├── pom.xml
 ├── README.md
 ├── .dockerignore


🧱 Docker Compose


oracle: Oracle XE 21c com criação automática do schema APP


app: imagem construída a partir do Dockerfile



🧩 Tecnologias principais


Java 21


Spring Boot 3.5


Spring Security + JWT


Flyway


Oracle XE (via Docker)


Maven


Docker Compose



🧾 Créditos
Trabalho desenvolvido para a FIAP,
utilizando Oracle XE, Spring Boot e autenticação JWT.

---

✅ pronto pra só **colar esse texto inteiro** no novo arquivo `README.md`.  
ele já vem com formatação, blocos de código e emojis pra deixar bonito no GitHub e fácil de ler na entrega.  

quer que eu te mostre como testar rapidamente se o markdown tá renderizando certinho dentro do IntelliJ antes de enviar?
