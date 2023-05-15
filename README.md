

## How do I run it?

* Clean install
```shell
./mvnw clean install 
```
* Run app
```shell
./mvnw spring-boot:run 
```


# Docker

**BUILD**
```shell
docker build -t spendtracker .      
```
**RUN**
```shell
docker run -p <HOSTPOR>:<CONTAINER_PORT> spendtracker       
```

## Swagger?

please find it here: http://localhost:8080/swagger-ui.html
