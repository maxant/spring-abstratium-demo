# a project for exploring the integration of spring boot with abstratium.dev

## Setup

run mysql - see abstratium.dev manual

    docker run -it --network serverless --rm mysql mysql -h serverless-mysql --port 3306 -u root -p

    -- https://spring.io/guides/gs/accessing-data-mysql/
    create database db_example;
    create user 'springuser'@'%' identified by 'ThePassword';
    grant all on db_example.* to 'springuser'@'%';

## Links

- http://localhost:8080/

- https://docs.spring.io/spring-boot/docs/ -> all versions
  - https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
- https://start.spring.io/
- https://spring.io/guides

## useful

- other config: 
  - `java -jar app.jar --spring.config.location=config/*/` or with a specific file name
  - `java -jar app.jar --property="value"`
  - `java -Dproperty.name="value" -jar app.jar`
  - env vars:
    - `export name=value` and then `java -jar app.jar`

## To Explore

- debug info on startup about which beans are available
- test scopes and making tests run quick
- programatically set port, etc. on boot app?
- https://www.baeldung.com/spring-interview-questions
- bean lifecycles
- devtools and reloading - how does that work with prod?
- a dev profile, which shows sql
- a test profile which uses spring.jpa.hibernate.ddl-auto=create for ITs, or should we use flyway there too?
- rest exception mappers
- https://spring.io/blog/2021/08/22/structuring-your-code-for-spring-framework-and-spring-boot
- https://www.baeldung.com/spring-boot-annotations
- https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
- https://spring.io/guides/tutorials/spring-security-and-angular-js/
- https://www.baeldung.com/spring-security-method-security => using @Secured
- [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#using.devtools)
- [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#appendix.configuration-metadata.annotation-processor)
- [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#data.sql.jpa-and-spring-data)
- [Validation](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#io.validation)
  - [Validation](https://spring.io/guides/gs/validating-form-input/)
- [Quartz Scheduler](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#io.quartz)
- [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#actuator)
  - [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
