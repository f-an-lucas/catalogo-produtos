# Catálogo de Produtos (Spring Boot 3, Java 21)

API simples de loja com produtos e categorias, com CRUD completo, validação, paginação/sorting e Swagger UI customizado.

## Requisitos
- Java 21
- Maven 3.9+

## Como rodar
```bash
mvn spring-boot:run
# Acesse a doc:
http://localhost:8088/docs
# API docs (JSON):
http://localhost:8088/v3/api-docs
# H2 console:
http://localhost:8088/h2
```

## Endpoints principais
- `GET /api/v1/products` — lista paginada (`?page=0&size=10&sort=price,desc`)
- `POST /api/v1/products` — cria produto
- `GET /api/v1/products/{id}` — detalhe
- `PUT /api/v1/products/{id}` — atualiza
- `DELETE /api/v1/products/{id}` — remove

- `GET /api/v1/categories`
- `POST /api/v1/categories`
- `PUT /api/v1/categories/{id}`
- `DELETE /api/v1/categories/{id}`

## Exemplos cURL
```bash
curl -X POST http://localhost:8080/api/v1/categories -H "Content-Type: application/json" -d '{"name":"Games"}'

curl -X POST http://localhost:8080/api/v1/products -H "Content-Type: application/json" -d '{
  "name":"Console X",
  "description":"Último modelo",
  "price":4599.90,
  "stock":20,
  "categoryId":1
}'
```

## Build nativo de imagem (Buildpacks)
```bash
mvn -DskipTests spring-boot:build-image
docker run -p 8080:8080 catalogo-produtos:0.0.1-SNAPSHOT
```

## Observações
- Banco H2 em memória para desenvolvimento (com `data.sql`). Troque para Postgres facilmente mudando `spring.datasource`.
- Swagger UI customizado com CSS e favicon em `src/main/resources/static/swagger-ui/`.
