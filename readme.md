# Work in progress
Library to ease the access of asymetric RSA keys in jwt-service projects.

Utilizies decorates to inject rsa values into configuration beans.


## Purpose
Access RSA asymetric keys easily from resources/cert using following application properties

```properties
rsa.private-key=cert/rsa.private.pem
rsa.public-key=cert/rsa.public.pem
```

## Versions
- Spring boot 3.1.5
- Maven 3.9.5
- Java 17.0.2

## Dependencies
<details>
  <summary>Maven</summary>
  
```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
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
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
  </dependency>
  <dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
    <version>2.2.7</version>
  </dependency>
  <dependency>
    <groupId>com.sun.xml.bind</groupId>
    <artifactId>jaxb-impl</artifactId>
    <version>2.2.5-b10</version>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
  </dependency>
</dependencies>
```
</details>


## Future implementation
Intended to also be used as a jwt validation library. 
