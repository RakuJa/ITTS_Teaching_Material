# springboot-crud-demo

Spring Boot CRUD demo is demonstrating how to implement simple CRUD operations with a `Product` entity.

## What's inside 
This project is based on the [Spring Boot](http://projects.spring.io/spring-boot/) project and uses these packages :
- Maven
- Spring Core
- Spring Data (Hibernate & MySQL)
- Spring MVC (Tomcat)
- [Thymleaf](https://thymeleaf.org)

![demo](https://cl.ly/sEGH/Screen%20Recording%202018-06-11%20at%2010.34%20AM.gif)

## Installation 
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies

## Database configuration 
### Linux:
Run create_db.sh as root
```bash
./create_db.sh
```
### Windows:
Create a MySQL database with the name `springbootdb` and add the credentials to `/resources/application.properties`.  
The default ones are :

```
spring.datasource.url=jdbc:mysql://localhost:3306/springbootdb
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

## Usage
run this command in the command line and head out to [http://localhost:8080](http://localhost:8080):
```
mvn spring-boot:run
```
