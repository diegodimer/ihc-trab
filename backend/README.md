# PPGC BackEnd

## Ambiente de desenvolvimento
- O backend foi desenvolvido em JAVA com o uso de Spring boot (Spring MVC) usando mongoDB como a base de dados.
- Requerimentos:
  - maven (3.6.3)
  - java (openjdk version "1.8.0_292")
- Instância de mongoDB rodando. 


## Passos para rodar o backend
 - na pasta ``src/main/resources/`` crie um arquivo chamado ``application.properties``. Nele você deverá inserir a propriedade ``spring.data.mongodb.uri`` com a URI para conectar na sua instância do mongoDB 
 - Execute o comando do mvn para limpar e gerar arquivo jar executável:
     - ``mvn clean install``
 - Execute o arquivo gerado na pasta target 
     - ``java -jar target/\<arquivo executavel.jar\>``

## Uso do backend (em deploy no Heroku)
URL BASE: https://back287690.herokuapp.com/

Test endpoints:
- https://back287690.herokuapp.com/user/new
- https://back287690.herokuapp.com/presence/upload

API endpoints:

| Endpoint          | Request Parameters                       | Method | Expected Return                       |
|-------------------|------------------------------------------|--------|---------------------------------------|
| /user/new         | name, email, phone, password, advisor    | POST   | status, userId                        |
| /user/get         | userId                                   | POST   | status, name, email, advisor, phone   |
| /user/login       | user, password                           | POST   | status, userId                                |
| /presence/add     | day, month, year, userId, status, reason | POST   | status, presenceId                    |
| /presence/justify | presenceId, reason                       | POST   | status, message                       |
| /presence/get     | userId, month, year                      | POST   | status, presences                     |
