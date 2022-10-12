<h1 align="center">Spring Boot Basic thymeleaf app</h1>

<br>
<br>

## Table of Contents

- [Requirements](#requirements)
- [Docker](#docker-examples)
- [Local installation](#local-installation)
- [Database Migrations](#database-migrations)

## Requirements

- MariaDB: `^10.6.10`
- Java: `^17`
- Maven: `^3.8.4`

## Docker examples

> 🚨 create `environment` file and add permission to execute scripts
>
> ```shell
> cp .docker/.env.example .docker/.env && chmod -R +x .docker/scripts
> ```

- docker-compose for development
  - starting containers
  ```
  .docker/scripts/develop up -d --build
  ```
  
  - removing contaiers
  ```
  .docker/scripts/develop down
  ```

  - show backend logs
  ```
  .docker/scripts/develop logs -f app
  ```

- docker-compose for production
  ```
  .docker/scripts/production up -d --build
  ```

  ```
  .docker/scripts/production down
  ```

## Local Installation
> 🚨 check [requirements](#requirements) or if you are using docker check [docker development instructions](#docker-examples)

- clone the repository and access the directory.
  ```shell
  git clone git@github.com:Throyer/springboot-api-crud.git crud && cd crud
  ```
- download dependencies
  ```shell
  mvn -f api/pom.xml install -DskipTests
  ```
- run the application (available at: [localhost:8080](http://localhost:8080))
  ```shell
  mvn -f api/pom.xml spring-boot:run
  ```
- running the tests
  ```shell
  mvn -f api/pom.xml test
  ```
- to build for production
  ```shell
  mvn -f api/pom.xml clean package
  ```
- to generate the coverage report after testing `(available at: api/target/site/jacoco/index.html)`
  ```shell
  mvn -f api/pom.xml jacoco:report
  ```


## Database Migrations
Creating database migration files

> 🚨 check [requirements](#requirements)
>
> if you using docker-compose
> ```
> .docker/scripts/develop exec app mvn migration:generate -Dname=my-migration-name
> ```

- Java based migrations
  ```bash
  cd app && mvn migration:generate -Dname=my-migration-name && cd ..
  ```

- SQL based migrations
  ```bash
  cd app && mvn migration:generate -Dname=my-migration-name -Dsql && cd ..
  ```
