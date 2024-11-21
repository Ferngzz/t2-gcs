# Trabalho 2 - Adaptando o T1 para utilizar com MySQL/Docker.

### Connector necessario para conectar no banco MySQL: 
### • <a href="https://dev.mysql.com/downloads/connector/j/"> MySQL Connector</a>


### Para criar o banco no CMD:
```
 docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=t2gcs -p 3306:3306 -d mysql:latest
```

### Se for usar o docker compose:
```
docker-compose up -d
```
