version: "3.3"
services:
  mysqlservice:
    image: mysql
    ports:
      - 3306:3306
    container_name: mysqlcontainer2
    network_mode: host
    hostname: localhost
    environment:
      MYSQL_ROOT_PASSWORD: root123
#      MYSQL_USERNAME: root
#      MYSQL_PASSWORD: password
      MYSQL_ALLOW_EMPTY_PASSWORD: "yes"
  mongodbservice:
    image: mongo:latest
    container_name: mongocontainer2
    network_mode: host
    hostname: localhost
    ports:
      - 27017:27017

  userauthenticationservice:
    container_name: user-authentication-service
    build: RegisterLoginApi
    hostname: localhost
    network_mode: host
    ports:
      - 8080:8080
    depends_on:
      - mysqlservice
    restart: "no"

  userregisterservice:
    container_name: kanban-register-service
    build: kanbanRegisterService
    hostname: localhost
    network_mode: host
    ports:
      - 8082:8082
    depends_on:
      - mongodbservice
    restart: "no"

  cloudapiservice:
    image: apigateway-service
    build: ApiGateway
    restart: "no"
    network_mode: host
    #hostname: localhost
    ports:
      - 9001:9001
    environment:
      API_GATEWAY_PORT: 9001

  kanbanservice:
    container_name: kanban-mongo-service
    build: KanbanMongoService
    hostname: localhost
    network_mode: host
    ports:
      - 8085:8085
    depends_on:
      - mongodbservice
    restart: "no"





