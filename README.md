# Prueba técnica Desarrollador backend java
Servicio para capturar datos de posibles candidatos a clientes para Unicomer Jamaica. 

## Información general:
El servicio está hecho con:
- Spring Boot 
- Java 11
- Maven
- H2 como base de datos

## Heroku
El servicio ha sido expuesto en la nube de Heroku y puede probarse en el siguiente link: 

https://unicomer-micro-clients.herokuapp.com/swagger-ui/index.html#/Clientes 

## Features

- Permite agregar, modificar y obtener los posibles clientes registrados en la base de datos de UNICOMER Jamaica.
- Permite listar mediante páginas a todos los clientes almacenados. 
 
## Installation
Descargar el proyecto del repositorio https://github.com/walter5lemus/unicomer-micro-clients y abrir una terminal.

```sh
cd unicomer-micro-clients
mvn clean install
cd target
java -jar unicomer-micro-clients-1.0.0_0-SNAPSHOT.jar
```
  
## Docker

Este servicio es muy facil de implementar en un contenedor con Docker.

```sh
cd unicomer-micro-clients
docker build -t unicomer-micro-clients .
docker run -it -p 8080:8080 --name unicomer-micro-clients unicomer-micro-clients 
```

## Swagger
En entorno local la documentación con swagger se puede ver con el link :

http://localhost:8080/swagger-ui/index.html#/Clientes

NOTA: El puerto puede cambiar según el que se haya definido.
