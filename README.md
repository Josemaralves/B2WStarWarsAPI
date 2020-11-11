# Planetas do Starwars API

##Menu

- [Introdução](#Introdução)
- [Instalação](#Instalação)
- [Swagger](#Swagger)
- [Endpoints](#Endpoints)
- [Modelo](#Modelo)
- [Exception](#Exception)
- [Tecnologias Utilizadas](#Tecnologias-utilizadas)

##Introdução

O "Planetas do Starwars API" é um serviço montado utilizando a arquitetura RestFul.
O Serviço tem como objetivo fornecer as informações sobre os planetas existentes no mundo do Star Wars, sendo possivel:

- Criar um Planeta
- Buscar por Planetas
- Buscar planeta por ID
- Excluir Planeta

> Para mais Informaçoes, consulte a area dos [Endpoints](#Endpoints)

 >A Informação de quais planetas aparecem nos filmes é provida pelo https://swapi.dev/.

##Instalação

####Requisitos:

- [Java 15](https://www.oracle.com/br/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)

Para iniciar o projeto, basta ter o [Maven](https://maven.apache.org/) configurado na [Variável de Sistema](https://stackoverflow.com/questions/45119595/how-to-add-maven-to-the-path-variable) e executar o comando abaixo na raiz do projeto.
```bash
mvn spring-boot:run
```

##Swagger

A Documentação da API também está disponível no [Swagger](https://swagger.io/) pelo Endpoint:
```bash
https://planetas-do-starwars-api.herokuapp.com/swagger-ui/
```

##Endpoints

### Busca de planetas

Método responsável pela busca dos planetas

- Endpoint

- Método HTTP
- GET
- **Query Parameters**

    Parâmetros |Tipo| Descrição | Obrigatório 
    ------------ | ------------- | ------------- |  ------------- 
    name |String| Irá efetuar a busca pelo nome do planeta|✖


- **Requisições**

    Busca por todos os Planetas
    
    `GET https://planetas-do-starwars-api.herokuapp.com/planet/search`

    Busca pelo nome do planeta Tatooine
    
    `GET https://planetas-do-starwars-api.herokuapp.com/planet/search?name=Tatooine`
     
- **Respostas**

    **HTTP 200**: Planeta(s) encontrados
    
```json

[
    {
        "id": "5fa9f701ea3ba263b317a2de",
        "name": "Teste",
        "clime": "Chuvoso",
        "terrain": "Arenoso",
        "films": []
    },
    {
        "id": "5fa9f80eea3ba263b317a2df",
        "name": "Teste",
        "clime": "Chuvoso",
        "terrain": "Arenoso",
        "films": []
    },
    {
        "id": "5fa9f6f3ea3ba263b317a2dd",
        "name": "Tatooine",
        "clime": "Chuvoso",
        "terrain": "Arenoso",
        "films": [
            {
                "title": "A New Hope"
            },
            {
                "title": "Return of the Jedi"
            },
            {
                "title": "The Phantom Menace"
            },
            {
                "title": "Attack of the Clones"
            },
            {
                "title": "Revenge of the Sith"
            }
        ]
    }
]

```

**HTTP 404**: Planeta não encontrado

```json
{
 "Erro": "Planeta não encontrado"
}
```

### Busca do planeta pelo id

Método responsável pela busca ao planeta pelo id

- **Endpoint**

    https://planetas-do-starwars-api.herokuapp.com/planet/{id}
- Método HTTP
- GET
- **Query Parameters**

    Parâmetros |Tipo| Descrição | Obrigatório 
    ------------ | ------------- | ------------- | ------------- 
    ID |String|ID do planeta|✓ 
    
- **Exemplos de Requisições**

    Busca pelo id do planeta
    
    `GET https://planetas-do-starwars-api.herokuapp.com/planet/5fa9f6f3ea3ba263b317a2dd`
    
- **Respostas**

**HTTP 200**: Planeta encontrados
    
```json
{
    "id": "5fa9f6f3ea3ba263b317a2dd",
    "name": "Tatooine",
    "clime": "Chuvoso",
    "terrain": "Arenoso",
    "films": [
        {
            "title": "A New Hope"
        },
        {
            "title": "Return of the Jedi"
        },
        {
            "title": "The Phantom Menace"
        },
        {
            "title": "Revenge of the Sith"
        },
        {
            "title": "Attack of the Clones"
        }
    ]
}
```

**HTTP 404**: Planeta não encontrado
    
```json
{
 "Erro": "Planeta não encontrado"
}
```

### Criação do planeta

Método responsável pela criação do planeta

- **Endpoint**

    https://planetas-do-starwars-api.herokuapp.com/planet
- Método HTTP
- **POST**

    `POST https://planetas-do-starwars-api.herokuapp.com/planet/`

- **Exemplos de Requisições**

    **Criação do planeta**
    
    Atributos do objeto |Tipo| Descrição | Obrigatório 
    ------------ | ------------ | ------------ | ------------ 
    Name |String|Nome do planeta|✓ 
    Clime |String|Clima do planeta|✓ 
    Terrain |String|Terreno do planeta|✓ 

```json
{
   "name":"Planet",
   "clime":"Clime",
   "terrain":"Terrain",
   "films": []
}
```
    
- **Respostas**

    HTTP 201: Planeta criado

    **HTTP 400**: Planeta não criado devido à validação dos atributos nulos
    
```json
[
  {
    "name": "O campo Name é obrigatorio",
    "clime": "O campo Clime é obrigatorio",
    "terrain": "O campo Terrain é obrigatorio"
  }
]
```

### Remoção do planeta

Método responsável pela exclusão do planeta

- **Endpoint**

    https://planetas-do-starwars-api.herokuapp.com/planets/{id}
- Método HTTP
- DELETE
- **Query Parameters**

    Parâmetros |Tipo| Descrição | Obrigatório 
    ------------ | ------------- | ------------- | ------------- 
    ID |String|ID do planeta|✓ 

- **Exemplos de Requisições**

    Exclusão do planeta
    `DELETE https://planetas-do-starwars-api.herokuapp.com/planet/{id}`
    
- **Respostas**

    HTTP 204: Planeta excluido com sucesso

    **HTTP 404**: Planeta não encontrado
    
```json
{
 "Erro": "Planeta não encontrado"
}
```

## Modelo

Segue abaixo o modelo dos objetos utilizados:

### PlanetVO

Modelo da representação do Planeta

Atributo | Tipo | Descrição
------------ | ------------- | -------------
Id | String | Identificador do planeta
Name | String | Nome do planeta
Clime | String | Clima do planeta
Terrain | String | Terrenos do planeta
Films | List< Film >| Array com os Filmes Participados

### Film

Modelo da representação do Filme

Atributo | Tipo | Descrição
------------ | ------------- | -------------
Title | String | Nome do Filme

## Exception

As seguintes Exceptions foram utilizadas:

### PlanetNotFoundException

A "PlanetNotFoundException" é disparada quando é feito uma busca e não é encontrado um planeta.


## Tecnologias utilizadas

Foram utilizados os seguintes frameworks e/ou ferramentas:

- Java 15
- Spring 5
- Spring Boot 2
- Spring WebFlux
- Project Reactor
- Spring Data Reactive MongoDB
- MongoDB(Hospedado na Atlas)
- JUnit
- Maven
- Lombok