# Tech Challenge - Fase 4 - Sistema de Gerenciamento de Pedidos

## Pós Tech - Arquitetura e Desenvolvimento Java - Fiap - 2024

**Microsserviço desenvolvido por:** Pedro Yuji Ogata Ono

- **Grupo 11:**
    - Pedro Ono - RM 354950
    - João Pedro Albuquerque dos Santos - RM 355211
    - Elton Xavier Souza - RM 354254
    - Welder Ressutti - RM 99070

## Microsserviço de Catálogo de Produtos

Este microsserviço foi desenvolvido seguindo os padrões de **API RESTful**, arquitetura **hexagonal** com foco em isolar
o **core** da aplicação dos elementos externos, e também respeitando os princípios **SOLID** de isolamento de
responsabilidades.

Foi utilizado banco de dados relacional **PostgreSQL** para persistência dos dados

Foram criadas exceções personalizas que são tratadas globalmente pelo **Rest Controller Advice** e **Exception
Handler**, que retorna uma classe de erro contendo **timestamp**, **code**, **message**.

## Funcionalidades e Endpoints

### Criação de Produtos (POST)

**Endpoint:** ``/product``

É realizado o upload do arquivo .csv e vai ser salvo na pasta src/main/resources e depois executará o incrementJob, que fará todo o processamento do arquivo .csv e salvar os dados no banco de dados PostgreSQL.

### Atualização de Produto (PUT)

**Endpoint:** ``/product/{idProduct}``

É processado o request body, e com o id, ele atualizará no banco de dados a Entidade salva.

### Leitura de(os) Produto(s) (GET)

**Endpoint:** ``/product/{idProduct}`` ``/product``

No primeiro endpoint, ele faz a busca do produto pelo clientId, e o segundo, o consumidor passará os parametros de brand (marca) e genero (gender) e retornará uma lista de produtos baseado nos valores desses parametros.

## Arquitetura

A arquitetura hexagonal consiste na ideia de utilizar portas e adaptadores para realizar a integração entre a camada
externa e interna, isolando o core da aplicação para que alterações externas não interfiram na regra de negócio e casos
de uso.

Essa aplicação foi arquitetada da seguinte forma:

![Aquitetura](images/Arquitetura.jpg)

## Executando a aplicação

### Pré-requisitos

- **Git** (caso queira clonar o repositório, mas você pode apenas baixá-lo):
    - [Git](https://git-scm.com/downloads)
- **Docker Desktop** (para executar a aplicação através de containers):
    - [Docker](https://www.docker.com/products/docker-desktop/)

### Passo 1: Clonar ou baixar o repositório

Para clonar o repositório, use o comando:

```bash
git clone https://github.com/pedr0no/tech-challenge4-ms-product-catalog.git
```

### Passo 2: Executar a aplicação com Docker

#### Ambiente de Produção (prd):

No terminal, dentro do diretório raíz do projeto (local onde se encontra o arquivo **pom.xml** da aplicação), execute o
comando abaixo para subir o container da aplicação e do banco de dados **PostgreSQL**:

```bash
docker compose up -d
```

O Docker tentará baixar a imagem no repositório do [Docker Hub](), caso ele não encontre a imagem, ele realizará a
**build** da aplicação **(.jar)**, criará a imagem e executará os containers automaticamente.

Antes do container da aplicação subir, ele aguardará o container do banco de dados **PostgreSQL** estar pronto
utilizando um **healthcheck**, quando pronto, o container da aplicação inicia:

```
http://localhost:8080/productCatalogManagement/v1/swagger-ui/index.html
```

## Documentação da API (Swagger UI)

A documentação da **API** pode ser acessada via **Swagger UI**. Após executar a aplicação, acesse:

```
http://localhost:8080/api/swagger-ui/index.html
```

Lá você encontrará detalhes sobre todos os endpoints disponíveis, parâmetros de requisição e resposta.

![Jacoco](images/swagger.png)