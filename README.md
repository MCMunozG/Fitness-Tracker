# 🏋️‍♂️ Fitness Tracker

Aplicación de consola en **Java 24** con **Spring Boot**, basada en
**DDD (Domain Driven Design)** y **Arquitectura Hexagonal**, para el
registro y consulta de rutinas de entrenamiento y logs de usuarios.

------------------------------------------------------------------------

## 🚀 Características

-   Arquitectura **Hexagonal (Ports & Adapters)**.
-   Modelado con **Value Objects** y **Records** en el dominio.
-   Separación clara en capas:
    -   **Domain**: Entidades, Value Objects, Interfaces (Ports).
    -   **Application**: Casos de uso y servicios.
    -   **Infrastructure**: Persistencia JPA y CLI (ConsoleAdapter).
-   Uso de **DTOs** y **Mappers** para separar dominio de
    infraestructura.
-   Persistencia en **MySQL** con **Spring Data JPA**.
-   Principios **SOLID** y patrones de diseño aplicados.
-   Dependencias mínimas.

------------------------------------------------------------------------

## 📦 Dependencias principales

``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```

------------------------------------------------------------------------

## ⚙️ Configuración

En el archivo `application.properties` se definen las credenciales de
conexión, aunque al momento de ejecutarlo debes especificar las variables de entorno en tu **.env**:

``` properties
spring.application.name=fitness-tracker
spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_DATABASE}?
useSSL=true&verifyServerCertificate=false&allowPublicKeyRetrieval=true
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

------------------------------------------------------------------------

## 🏗️ Estructura del Proyecto

    fitness-tracker/
     ├── domain/
     │    ├── model/            # Records, Entities
     │    ├── port/             # Interfaces (Ports)
     │    └── vo/               # Value Objects
     │  
     ├── application/   
     │    ├── dto/              # Data Transfer Objects
     │    ├── mapper/           # DTO ↔ Domain Mappers
     │    └── service/          # Casos de uso (Services)
     │
     ├── infrastructure/
     │    ├── persistence/
     │    │    ├── respository  # Repositorios
     │    │    ├── adapter/     # Implementaciones (Adapters)
     │    │    └── entity/      # JPA Entities
     │    └── cli/              # ConsoleAdapter
     │
     └── FitnessTrackerApplication.java

------------------------------------------------------------------------

## 📊 Diagrama de Clases

El diseño sigue el modelo DDD con registros, value objects, puertos,
servicios, adapters y entidades.\
El diagrama completo está definido en **PlantUML** dentro de este
repositorio (`fitness-tracker-class-diagram.png`).

------------------------------------------------------------------------

## ▶️ Ejecución

Clonar el proyecto y compilar:

``` bash
mvn clean install
```

Ejecutar aplicación de consola:

``` bash
mvn spring-boot:run
```

------------------------------------------------------------------------

## 🔑 Funcionalidades Principales

-   **Registro y login de usuarios** con email y password (hash).
-   **Gestión de ejercicios** y rutinas predefinidas.
-   **Registro de entrenamientos (WorkoutLogs)** con duración y
    calorías.
-   **Consultas de rutinas y logs** por usuario.

------------------------------------------------------------------------

## 🧑‍💻 Autores

-   Arquitectura y desarrollo: **\[Manuel Camilo Muñoz Grijalba\]**
-   Basado en principios de **DDD, SOLID y Hexagonal Architecture**
