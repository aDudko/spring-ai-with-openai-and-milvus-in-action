# Spring AI with OpenAI and Milvus in Action

**The AI-based service to make recommendations based on user documents**

## Overview

The application is designed as a service with no user interface.

Once the application is started, it will automatically load documents from the `classpath` (see `application.yml`) into
the `Milvus` vector database. Then you can make `POST` requests to `/ask/open-ai`.

[API documentation](http://localhost:8080/swagger-ui/index.html#/) will be available once the application is started

[MinIO](http://localhost:9001/login) (Multi-Cloud Object Store) will be available once the application is started.
`Login`: minioadmin, `Password`: minioadmin

## Technologies

- `Java` - version `21`
- `Maven` - for building the application
- `Spring Boot` - version `3.4.3`
- `Spring AI` - version `1.0.0-M6`, model `gpt-4-turbo`
- `Spring AI Tika` - for extract metadata and text from documents
- `Spring Boot Maven Plugin` - for create Docker-Image
- `Docker-Compose` - infrastructure
- `Milvus` - vector database

## Structure of the project

```
spring-ai-with-openai-and-milvus-in-action/
├── src/main/
|   ├── java/com/dudko/example/
|   |   ├── bootstrap/
|   |   ├── config/
|   |   ├── controller/             # controllers
|   |   ├── model/                  # service level of the domain, used in business logic and controllers
|   |   ├── service/                # business logic
|   ├── resources/
|   |   ├── templates/              # prompt and system message
|   |   ├── application.yml         # configs
├── compose.yml                     # docker-compose file
├── pom.xml                         # artifact of Maven
├── postman_collection.json         # collection of requests for Postman
```

## How to try this project?

❗Don't forget to set the `OPENAI_API_KEY` environment variable before running the application.

```sh
docker-compose -f compose.yml up
```

### Author:

Anatoly Dudko