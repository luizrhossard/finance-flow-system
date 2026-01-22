# 💰 FinanceFlow API

<div align="center">

**API de Controle Financeiro Inteligente com Integração Python/IA**

![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)
![License](https://img.shields.io/badge/license-MIT-green.svg)
![Java](https://img.shields.io/badge/java-17+-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)

[Ver Demo](#-como-usar) • [Documentação](#-documentação) • [Instalação](#-instalação) • [Stack](#-stack-tecnológico)

</div>

---

## 📖 O que é FinanceFlow?

FinanceFlow é uma **API REST inteligente** que gerencia suas finanças pessoais e faz **previsões de fluxo de caixa usando IA/Machine Learning**.

### ✨ Destaques:

- 💰 **Resumo Financeiro**: Visualize Receitas, Despesas e Lucro em tempo real
- 🤖 **Previsões com IA**: Modelos Python/scikit-learn preveem gastos futuros
- 📊 **Dashboard Pronto**: Endpoints preparados para alimentar painéis visuais
- 🔌 **Microserviços**: Arquitetura escalável Java ↔ Python
- 📚 **Swagger Integrado**: Documentação automática dos endpoints
- 🐳 **Docker Ready**: Deploy em qualquer ambiente com um comando

---

## 🚀 Quick Start (5 minutos)

### **Pré-requisitos:**

- Docker & Docker Compose instalados
- Java 17+ (se rodar sem Docker)
- Python 3.9+ (para o microserviço de IA)

### **Instalar e Rodar:**

```bash
# 1. Clone o repositório
git clone https://github.com/luizrhossard/financeflow-api.git
cd financeflow-api

# 2. Configure as variáveis de ambiente
cp .env.example .env

# 3. Inicie com Docker Compose
docker-compose up -d

# 4. Pronto! API rodando em http://localhost:8080
```

Verificar se tá funcionando:

```bash
curl http://localhost:8080/api/dashboard/summary
```

Resposta esperada:

```json
{
  "income": 1500.0,
  "expenses": 500.0,
  "netProfit": 1000.0
}
```

## 📊 Como Usar

### 1. Ver Resumo Financeiro

Retorna o total de Receitas, Despesas e Lucro do mês atual.

```bash
curl -X GET "http://localhost:8080/api/dashboard/summary" \
  -H "accept: */*"
```

Resposta:

```json
{
  "income": 1500.0,
  "expenses": 500.0,
  "netProfit": 1000.0
}
```

### 2. Obter Previsão com IA

O sistema analisa seu histórico e prevê os próximos meses.

```bash
curl -X GET "http://localhost:8080/api/dashboard/prediction" \
  -H "accept: */*"
```

Resposta (com IA treinada):

```json
{
  "message": "Previsão do mês: Receita esperada R$ 1500, Despesa R$ 550. Lucro: R$ 950"
}
```

## 📚 Documentação Completa

Acesse o Swagger interativo em:

```text
http://localhost:8080/swagger-ui/index.html
```

Lá você pode:

✅ Ver todos os endpoints

✅ Testar requisições em tempo real

✅ Ver documentação automática

✅ Copiar comandos curl

## 🏗️ Arquitetura

```text
┌─────────────────────────┐
│   Cliente/Frontend      │
│   (React/Angular/Vue)   │
└────────────┬────────────┘
             │ HTTP/REST
┌────────────▼────────────────┐
│   FinanceFlow API (Java)    │
│   - Spring Boot 3.x         │
│   - REST Endpoints          │
│   - Banco de Dados          │
└────────────┬────────────────┘
             │ HTTP
┌────────────▼──────────────────┐
│   Microserviço Python (IA)    │
│   - sklearn/scikit-learn      │
│   - Previsões ML              │
│   - Análise Financeira        │
└───────────────────────────────┘
```

## 🛠️ Stack Tecnológico

### Backend (Java)

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL / MySQL
- Lombok
- Springdoc OpenAPI (Swagger)

### Microserviço (Python)

- Python 3.9+
- scikit-learn (Machine Learning)
- pandas (análise de dados)
- Flask (HTTP API)

### DevOps

- Docker & Docker Compose
- GitHub Actions (CI/CD)

## 📦 Estrutura do Projeto

```text
financeflow-api/
├── src/
│   ├── main/
│   │   ├── java/com/financeflow/
│   │   │   ├── controller/      # Endpoints REST
│   │   │   ├── service/         # Lógica de negócio
│   │   │   ├── repository/      # Acesso a dados
│   │   │   └── model/           # Entidades JPA
│   │   └── resources/
│   │       └── application.yml  # Configurações
│   └── test/                    # Testes
├── python-service/
│   ├── app.py                   # Flask app
│   ├── predictor.py             # Modelo ML
│   └── requirements.txt          # Dependências
├── docker-compose.yml            # Orquestração
├── Dockerfile                    # Imagem Java
├── pom.xml                       # Maven
└── README.md                     # Este arquivo
```

## ⚙️ Configuração

### Variáveis de Ambiente (.env)

Copie .env.example para .env e configure:

```text
# Banco de Dados
DB_HOST=localhost
DB_PORT=5432
DB_NAME=financeflow
DB_USER=postgres
DB_PASSWORD=seu_password

# Flask Python
PYTHON_SERVICE_URL=http://python-service:5000

# Spring
SPRING_PROFILES_ACTIVE=dev
```

## 🧪 Testes

Rodar todos os testes:

```bash
mvn test
```

Teste específico:

```bash
mvn test -Dtest=DashboardControllerTest
```

Com cobertura:

```bash
mvn test jacoco:report
```

## 🐳 Docker

Build da imagem:

```bash
docker build -t financeflow:latest .
```

Rodar um container:

```bash
docker run -p 8080:8080 --env-file .env financeflow:latest
```

Com compose (recomendado):

```bash
docker-compose up -d
```

## 📈 Roadmap

- [x] API REST básica
- [x] Endpoints de Dashboard
- [x] Integração com microserviço Python
- [ ] Autenticação JWT
- [ ] Multi-usuário
- [ ] Frontend React
- [ ] Deploy na Cloud (AWS/Heroku)
- [ ] Alertas de gastos
- [ ] Categorias de transações
- [ ] Exportar relatórios (PDF/Excel)

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Faça um Fork do projeto
2. Crie uma branch: `git checkout -b feature/sua-feature`
3. Commit suas mudanças: `git commit -m 'feat: Descrição'`
4. Push: `git push origin feature/sua-feature`
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja LICENSE para detalhes.

## 👨💻 Autor

**Seu Nome**

GitHub: [@luizrhossard](https://github.com/luizrhossard)

LinkedIn: [luiz-henrique](https://linkedin.com/in/luiz-rhossard)


## 📞 Suporte

Encontrou um bug? Tem uma sugestão?

- Abra uma Issue
- Verifique se já existe issue similar
- Descreva detalhadamente o problema

## 🙏 Agradecimentos

- Comunidade Spring Boot
- scikit-learn
- Comunidade open source

<div align="center">
⭐ Se este projeto foi útil, deixe uma estrela! ⭐

</div>
