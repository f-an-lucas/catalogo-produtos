# Catálogo de Produtos
## Java - EDUC360

API simples de loja com produtos e categorias, com CRUD completo, validação, paginação/sorting e Swagger UI.

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

### Produtos
- `GET /api/v1/products` — lista paginada de produtos (filtros disponíveis, `?page=0&size=10&sort=price,desc`)
- `GET /api/v1/products/{id}` — busca produto pelo ID
- `GET /api/v1/products/searchByName?name=valor` — busca produtos por parte ou nome completo (case-insensitive e ignorando acentos)
- `GET /api/v1/products/category/{id}` — lista produtos por categoria (ID)
- `GET /api/v1/products/category/searchByName?name=valor` — lista produtos por parte ou nome completo da categoria (case-insensitive e ignorando acentos)
- `POST /api/v1/products` — cria produto
- `PUT /api/v1/products/{id}` — atualiza produto
- `DELETE /api/v1/products/{id}` — remove produto
- `PUT /api/v1/products/addToStock/{id}` — adiciona produtos ao estoque
- `PUT /api/v1/products/removeFromStock/{id}` — remove produtos do estoque

### Categorias
- `GET /api/v1/categories` — lista paginada de categorias (filtros disponíveis, `?page=0&size=10&sort=price,desc`)
- `GET /api/v1/categories/{id}` — busca categoria pelo ID
- `GET /api/v1/categories/searchByName?name=valor` — busca categorias por parte ou nome completo (case-insensitive e ignorando acentos)
- `POST /api/v1/categories` — cria categoria
- `PUT /api/v1/categories/{id}` — atualiza categoria
- `DELETE /api/v1/categories/{id}` — remove categoria

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
- Banco H2 em memória para desenvolvimento (com `data.sql`).